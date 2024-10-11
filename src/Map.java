public class Map {

    public Room createMap() {
        // Instansiere alle rummene
        Room room1 = new Room("The Clearing",
                """
                        You've just entered into the magical forest and has arrived at a clearing.\s
                        You're standing beneath an open canopy and can see the sky. By your estimation it's mid-day. The entrance from which you came\s
                        has been sealed shut by the very trees themselves as if they were alive and trying to prevent your escape. In the middle of the clearing\s
                        is a picnic cloth and a basket. Inside the basket is a single apple and a shiny brass oil lamp sits next to the basket.\s
                        You see 2 possible ways to go.""",
                "You're in a clearing with an open canopy above you.\nThere are 2 open ways to go.");
        room1.setHasBeenVisited(true);
        Room room2 = new Room("The Forest River",
                """
                        You've fumbled your way through brambles and thorns until you arrive at a small river.
                        The canopy above you is starting to get denser and less light is shining through. You can't see the beginning of the river for the trees
                        however it's clearly leading you towards a different opening. On this river is also an old half rotten bridge. You're convinced
                        about half of the boards are rotten and dare not walk on it. You can go back from where you came or follow the river.""",
                "You're by the river, you can go back from where you came or follow the river.");
        Room room3 = new Room("The skeletons",
                """
                        As you follow the river you arrive in an area where some sort of battle
                        has clearly taken place a long time ago. All over the ground and on the forest edge lay heaps of skeletons. Some with missing heads
                        others with the bottom or top half missing. Some of the bones even have bite marks, either from scavengers or from whatever killed them.
                        most of their items are rusty or destroyed, however you find a single sword and shield which might still be operational.
                        You can continue to follow to river or go back when where you came""",
                "There are skeletons everywhere, the river continues onwards.");
        Room room4 = new Room("The Musky Swamp", """
                As you come closer a musky scent reaches your nostrils. You finally break out from the corridor
                of trees you've been following, and before you lies a vast swamp. The water is murky and you have no idea about how deep it is, all over above"n
                the water hover light wisps who gives you a false sense of guidance. You've read about places like this, if you follow the light of the wisps
                you'll fall into the deep murky water and drown. There seems to be only one solid path to follow in the swamp.""",
                "A musky swamp with wisps that are trying to lure you. There is only one path to follow, or go back");
        Room room5 = new Room("Elven city", """
                As you move away from the frozen forest you start to thaw and feel warmer. You're following the
                elven song as it grows louder and louder. At this point you're enslaved to the elven song, even if you wanted to turn away you're not convinced you could.
                As you get closer you start to see the bottom of the pearly white spires that are towering into the canopy above. But even as the canopy is completely closed
                there's still light as if the city itself is glowing. You finally breach the woods but what you see is not what you expected. The singing stops,
                and before you stand a tall ugly smelly troll. "I am the mighty troll Thorax! I've been tasked with protecting this ugly city by the elves, but
                as a reward i'm allowed to eat any stupid adventurer who's trying to reach the elven king and queen. PREPARE TO GET EATEN!" he attacks you.""",
                "You can see the eleven city but the smelly troll Thorax is blocking your way, you must defeat him to continue, or flee back from where you came!");
        Room room6 = new Room("The Sphinx's Challenge", """
                As you step into this eerie chamber, the air feels heavy with an ancient, mystical presence.
                In the center of the room stands an imposing stone Sphinx, its eyes glowing faintly in the dim light. The creature's massive wings are folded
                neatly against its lion-like body, and its human face stares at you with unnerving intelligence. The walls are lined with intricate carvings,
                telling stories of travelers who have faced this very challenge before. Some carvings show victorious adventurers, while others depict grimmer
                fates. The Sphinx speaks with a booming voice:
                ‘To pass, you must answer my riddles. Fail, and I shall devour you where you stand.’
                The tension in the room is palpable.
                You can't continue until you've completed the sphinxes riddles.""",
                "A room dominated by a stone Sphinx who demands answers to its riddles. You cannot proceed onwards until you answer correctly.");
        Room room7 = new Room("The Hungry Garden", """
                You tread carefully as you enter this unsettling section of the forest.
                The faint melody of elven song echoes from beyond, but it feels far too distant to give comfort. Surrounding you are towering carnivorous
                plants, their vines writhing slowly as if sensing your presence. Massive, brightly colored flowers snap shut occasionally, as though testing
                their jaws for the next unfortunate soul to cross their path. The air is thick with the scent of decay and sweet nectar, and the ground beneath
                your feet feels alive, subtly shifting as if guiding you toward the plants. You know you must move carefully, navigating the path between the
                voracious flora, or risk being swallowed whole. You can forge ahead, but you must tread lightly. or turn back from whence you came""",
                "You are in a dangerous section of the forest filled with carnivorous plants. Their hungry vines block the way, but if you can avoid their grasp,\n" +
                        "you're one step closer to the elven city");
        Room room8 = new Room("The Frozen Sanctuary", """
                The once enchanting melody of the elves has become louder, almost overwhelming,
                as you press forward. However, the warmth of the elven song offers no solace in this part of the forest. You've entered an area entirely blanketed
                in frost and ice. The trees are frozen solid, their branches hanging heavy with icicles, and the ground beneath you cracks with each step. A biting wind
                chills you to the bone, and the very air feels hostile. The magic that permeates this place is palpable, a warning that the elves do not wish to be found.
                Time is of the essence, for if you linger too long in this icy realm, you will surely freeze to death. There are two possible paths forward, but the
                cold is closing in fast.""",
                "You've entered a magically frozen part of the forest. The cold is unbearable, and there are two ways out, but you must move quickly\n" +
                        "to avoid freezing.");
        Room room9 = new Room("The Fiery Abyss", """
                The heat strikes you like a hammer as soon as you step into this chamber. The air here
                is suffocatingly hot, and the elven song, once a distant hum, now feels like a cruel taunt. In front of you flows a river of molten lava,
                its fiery surface bubbling and cracking as the intense heat radiates through the area. The surrounding forest has transformed into a hellscape; the
                trees are scorched and smoldering, their blackened forms twisted and broken. The ground shakes occasionally, as though the very earth itself is unstable.
                Your body feels heavy with exhaustion from the oppressive heat, and each breath burns in your throat. If you don’t find a way through quickly, the heat
                alone may be your end.""",
                "A room filled with unbearable heat, with a river of lava flowing through the middle. You must hurry before the heat overwhelms you.");

        room1.setEastRoom(room2);
        room1.setSouthRoom(room4);

        room2.setEastRoom(room3);

        room3.setSouthRoom(room6);

        room4.setSouthRoom(room7);

        room5.setSouthRoom(room8);

        room6.setSouthRoom(room9);

        room7.setEastRoom(room8);

        room8.setEastRoom(room9);

        room1.addItem(new Item("Lamp", "A shiny brass lamp.", false));
        room1.addItem(new Food("Apple", "A shiny red apple", 10));
        room1.addItem(new Food("Cake", "A piece of Cake", -10, "A poisonous cake!"));
        room1.addItem(new RangedWeapon("Bow", "A wooden Bow and quiver with arrows", 10, 3));
        room1.addItem(new MeleeWeapon("Sword", "A sharp sword.", 5));

        room2.addEnemy(new Enemy("Goblin", 10, new MeleeWeapon("Dagger", "A Goblin Dagger", 2)));
        room2.addEnemy(new Enemy("Gnome", 10, new MeleeWeapon("Club", "A Gnome Sized Wooden Club", 1)));

        room3.addItem(new Food("Bacon", "Nice crispy bacon.", 15));

        room4.addItem(new MeleeWeapon("Axe", "A woodcutting axe.", 4));
        room4.addItem(new Food("Apple", "Ordinary looking green apple.", -8));

        room5.addEnemy(new Enemy("Thorax", 50, new MeleeWeapon("Mace",
                "A huge iron Mace", 15), "Big Boss Thorax!"));

        room9.addItem(new Item("Shield", "A wooden shield.", false));

        return room1;
    }
}
