TO DO LIST:
=======
- Redesign textures
- Make all attribute perks have a custom effect (Ex. Stronghold)

How To Add A Perk
=======
- Create a texture for the crystal and perk. Add it into the resources/assets/fiftyperksmod/textures/items directory
    - Ensure the texture is 16x16
- In the ModItems Class: Duplicate the line for a new perk and crystal, rename it using the naming convention of the rest of the items.
- In the ModCreativeModeTabs class: Duplicate the line to add a new perk and crystal to the creative mode tab
- In the ModItemTagProvider class: Duplicate an "add" line for your new perk and crystal in the correct tag
- In The ModItemModelProvider class: Duplicate an "add" line for your new perk and crystal for the correct item
- In the BasicPerkHolderItem class: Duplicate the following code block:
   `case "fiftyperksmod:deadshot_perk" -> {
                    MobEffectInstance currentEffect = player.getEffect(MobEffects.DAMAGE_BOOST);
                    if (currentEffect == null || currentEffect.getDuration() <= 15) {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 210, 0, true, false));
                    }
                }`
  Adjust the name and effect to match your new perk

- Run Data to generate all of your new item files. Then Run the client to test!

New Developer Setup
=======
- Download the following JDK: https://adoptium.net/temurin/releases/?package=jdk
- Download IntelliJ IDEA Community Edition: https://www.jetbrains.com/idea/download/?section=windows
    - Scroll down to find community edition
- Click "Get from VCS" When opening intelliJ, Give it the link of this repo to clone it.
- Make sure to switch to the Dev branch (You can do this in Github Desktop)
- Make sure your intellij project is using the JDK you downloaded

If your having issues use this as a guide: https://www.youtube.com/watch?v=yG-oJPR_40w&list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF&index=1&t=640s
You will not do ALL of the steps since this github repo is already created, but the intellij setup is the same.

Installation information
=======

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
