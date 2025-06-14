package com.cyber.FiftyPerksMod.block.entity;

import com.cyber.FiftyPerksMod.item.ModItems;
import com.cyber.FiftyPerksMod.item.custom.BasicPerkHolderItem;
import com.cyber.FiftyPerksMod.recipe.ModRecipes;
import com.cyber.FiftyPerksMod.recipe.UpgradeStationRecipe;
import com.cyber.FiftyPerksMod.recipe.UpgradeStationRecipeInput;
import com.cyber.FiftyPerksMod.screen.custom.UpgradeStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class UpgradeStationBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public UpgradeStationBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.UPGRADE_STATION_BE.get(), pos, blockState);

        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> UpgradeStationBlockEntity.this.progress;
                    case 1 -> UpgradeStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int value) {
                switch (i) {
                    case 0: UpgradeStationBlockEntity.this.progress = value;
                    case 1: UpgradeStationBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.fiftyperksmod.upgrade_station");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new UpgradeStationMenu(i, inventory, this, this.data);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("upgrade_station.progress", progress);
        pTag.putInt("upgrade_station.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("upgrade_station.progress");
        maxProgress = pTag.getInt("upgrade_station.max_progress");
    }

    public void tick(Level level, BlockPos blockPos, BlockState blockState) {
        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, blockPos, blockState);

            if(hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void craftItem() {
        Optional<RecipeHolder<UpgradeStationRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        ItemStack input1 = itemHandler.getStackInSlot(INPUT_SLOT_1); // Tier 1 Perk Holder
        ItemStack input2 = itemHandler.getStackInSlot(INPUT_SLOT_2); // Upgrade Ingredient
        ItemStack result = new ItemStack(recipe.get().value().output().getItem()); // Tier 2 Perk Holder

        // Copy perks from old holder to new one
        if (input1.getItem() instanceof BasicPerkHolderItem oldItem &&
                result.getItem() instanceof BasicPerkHolderItem newItem) {

            // Load old handler from input
            ItemStackHandler oldHandler = oldItem.getHandler(input1, level.registryAccess());

            // Load new handler (usually has more slots)
            ItemStackHandler newHandler = newItem.getHandler(result, level.registryAccess());

            // Copy perks
            for (int i = 0; i < oldHandler.getSlots(); i++) {
                ItemStack perk = oldHandler.getStackInSlot(i);
                if (!perk.isEmpty()) {
                    newHandler.setStackInSlot(i, perk.copy()); // Copy for safety
                }
            }

            // Save new handler data to the result stack
            newItem.saveHandler(result, newHandler, level.registryAccess());
        }

        // Place result into the output slot
        itemHandler.setStackInSlot(OUTPUT_SLOT, result);

        // Consume the inputs
        input1.shrink(1);
        input2.shrink(1);
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<UpgradeStationRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().output();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<UpgradeStationRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.UPGRADE_STATION_TYPE.get(), new UpgradeStationRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT_1), itemHandler.getStackInSlot(INPUT_SLOT_2)), level);
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
