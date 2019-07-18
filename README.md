#**Simple Server**

##What is it?

its a plugin that for spigot 1.14 that allows for simple, subtle mods to add to an SMP to make it more interesting

##What features?

there are currently 2 main features in the plugin; Shop generator and a sign post trading system

###Shop generator
This works by placing a fence post with the name 'shop builder' in yellow on the ground. This will generate a structure that can be used a a shop


###Sign post trading system
This system is a databaseless, chestless, sign trading system that just kind of.. works. If you set it up properly (which can be found by typing /helpme 3 in game with the plugin installed) you can trade with a sign post or wall sign without the need for both players to be there at the same time. They don't even have to be online at the same time.

##Installation

You can install this simply by heading to the release page, grabbing the latest release .jar file  and sticking it into the plugins/ directory of your spigot server. This may also work with paper but I have not tried yet.
Currently this has only been tested on 1.14 and will not work for versions before 1.9. However if there is enough demand I could create one for the legacy versions of minecraft too.

##Can I contribute?

yes! Of course. There are a couple todos that you can work on. Varying in sizes. These are the ones we currently are working on from easiest to largest. Each TODO is elaborated at the line reference if given.

- signs.java (67,11)
- shopBuild.java (61,15)
- add shop builder buy sign
- add checks for validity of sign syntax (signs.java)
- join.java (54,15) and shopBuild.java (290,15) both are the same issue. 
- Make sign posts indestructable for everyone but the owner (signs.java)


