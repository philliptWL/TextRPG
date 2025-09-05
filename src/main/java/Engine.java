import java.util.ArrayList;
import java.util.Scanner;

public class Engine{
    private static final String world = "Aethros";
    private static final String villain = "The Ash King";

    public static void start(){
        boolean blGameOver = false;
        ArrayList<String> path = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        //Intro
        gameIntro();

        //RPGCharacter creation
        RPGCharacter player = createCharacter(scan);
        postCharacterCreation(player);

        menu(new String[]{"Continue through the Cinder Gate."},true);
        while (!blGameOver) {
            if (scan.nextInt() == 1) {
                //Game loop
                path = startJourney(player, path, scan);
                firstEncounter(player, path, scan);



                blGameOver = isGameOver(player, scan, false);
            } else {
                blGameOver = isGameOver(player, scan, true);
            }
        }
    }

    private static boolean isGameOver(RPGCharacter player, Scanner scan, boolean override) {
        if(player.isDead()){
            typeOut(String.format("""
                    %s's light has faded away...
                    """, player.getName()),25);
            menu(new String[]{"Try again?"},true);
            if(scan.nextInt() == 0) {
                System.out.println("GAME OVER");
                return true;
            }
        } else if (override) {
            typeOut("""
                    Your journey has ended...
                    """,25);
            menu(new String[]{"Restart?"},true);
            if(scan.nextInt() == 0) {
                System.out.println("GAME OVER");
                return true;
            } else if (scan.nextInt() == 1) {
                start();
                return true;
            }
        }
        return false;
    }

    public static RPGCharacter createCharacter(Scanner scan){
        RPGCharacter player = null;
        String name;
        int startLevel = 1;

        while(player == null) {
            menu(new String[]{"Barbarian", "Ranger", "Mage"},false);
            int response = scan.nextInt();
            scan.nextLine();
            switch (response) {
                case 1: //Barbarian
                    name = namePlayerCharacter(scan,"Barbarian");
                    player = new RPGCharacter(name ,new Barbarian(110, chooseSword(name,scan),80,
                            new Charge()),startLevel,false);
                    break;
                case 2: //Ranger
                    name = namePlayerCharacter(scan,"Ranger");
                    player =  new RPGCharacter(name,new Ranger(100.0,chooseBow(name,scan),100,
                            new Evade()),startLevel,false);
                    break;
                case 3: //Mage
                    name = namePlayerCharacter(scan,"Mage");
                    player =  new RPGCharacter(name,new Mage(90.0,chooseStaff(name,scan),110,
                            new Restore()),startLevel,false);
                    break;
                default:
                    optInvalid("character class");
            }
        }
        return player;
    }

    public static String namePlayerCharacter(Scanner scan, String role){
        typeOut("What do they call you " + role + "?",25);
        return scan.nextLine();
    }

    public static Sword chooseSword(String name,Scanner scan) {
        String weapon = "swords";
        typeOut(String.format("""
                Ah, I see, well then you will be needing a weapon. Which of these %s catches your eye, %s?
                """,weapon,name), 25);

        String[] names = {"Shadowfang", "Embercleaver", "Frostbane"};
        Sword sword = null;

        while (sword == null) {
            menu(names, false);
            int response = scan.nextInt();
            switch (response) {
                case 1:
                    sword = new Sword(names[0], 25);
                    break;
                case 2:
                    sword = new Sword(names[1], 20);
                    break;
                case 3:
                    sword = new Sword(names[2], 23);
                    break;
                default:
                    optInvalid("sword");
                    break;
            }
        }
        return sword;
    }

    public static Bow chooseBow(String name, Scanner scan) {
        String weapons = "bows";
        typeOut(String.format("""
                Ah, I see, well then you will be needing a weapon. Which of these %s catches your eye, %s?
                """, weapons,name), 25);

        String[] names = {"Quicksilver", "Moonshot", "Heartseeker"};
        Bow bow = null;

        while (bow == null) {
            menu(names, false);
            int response = scan.nextInt();
            switch (response) {
                case 1:
                    bow = new Bow(names[0], 10);
                    break;
                case 2:
                    bow = new Bow(names[1], 13);
                    break;
                case 3:
                    bow = new Bow(names[2], 12);
                    break;
                default:
                    optInvalid("weapons");
                    break;
            }
        }
        return bow;
    }

    public static Staff chooseStaff(String name,Scanner scan) {
        String weapon = "staves";
        typeOut(String.format("""
                Ah, I see, well then you will be needing a weapon. Which of these %s catches your eye, %s?
                """,weapon,name), 25);

        String[] names = {"Voidlight Scepter", "Stormoak Rod", "Dawnfire Aegis"};
        Staff staff = null;

        while (staff == null) {
            menu(names, false);
            int response = scan.nextInt();
            switch (response) {
                case 1:
                    staff = new Staff(names[0], 17);
                    break;
                case 2:
                    staff = new Staff(names[1], 15);
                    break;
                case 3:
                    staff = new Staff(names[2], 14);
                    break;
                default:
                    optInvalid("staff");
                    break;
            }
        }
        return staff;
    }

    public static void optInvalid(String option){
        typeOut(String.format("""
                Please choose a valid %s.
                """,option),25);
    }

    public static void typeOut(String s, long baseMs) {
        long next = System.nanoTime();
        for (char c : s.toCharArray()) {
            System.out.print(c);
            System.out.flush();

            long extra = (c == '.' || c == '!' || c == '?') ? baseMs * 6
                    : (c == ',' || c == ';' || c == ':') ? baseMs * 3 : 0;

            next += (baseMs + extra) * 1_000_000L;
            long waitMs = Math.max(0, (next - System.nanoTime()) / 1_000_000L);
            try { Thread.sleep(waitMs); } catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }

            while (System.nanoTime() < next) Thread.onSpinWait();
        }
        System.out.println();
    }

    public static void gameIntro() {
        typeOut(String.format("""
            The world of %s has always teetered between light and shadow.

            Kingdoms rise, only to fall into dust. Old gods whisper from forgotten temples.

            Strange stars burn above, heralding change.
            """, world), 25);

        typeOut(String.format("""
            The lantern you see is mine. They call me the Dawnwatcher.

            I stand where the road meets the dark, tending one flame for travelers of %s.
            """, world), 25);

        typeOut("""
            Step into the light, that I may see your eyes.

            Fate turns to you now.

            Will you walk the path of blade, weapon, or spell?

            Will you carve your legend in steel and fire, or vanish like so many nameless wanderers before you?
            """, 25);

        typeOut("""
            Tell me, traveler…
            Who are you?
            """, 25);
    }

    public static void postCharacterCreation(RPGCharacter player){
        typeOut(String.format("""
                        Ah, %s, excellent choice.
                        
                        These are worrying times, %s. The ash has been a blight upon this world.
                        
                        Some whisper the ash has a master...
                        
                        The one they call %s...
                        """, player.getRole().getWeapon().name(),player.getName(),villain), 25);

        typeOut(String.format("""
                        You know...legend tells of a %s that will save %s from the clutches of %s.
                        
                        I pray that you, %s, are that hero...
                        """,
                player.getRole().type(), world, villain, player.getName()), 25);
        typeOut(String.format("""
                        The roads of %s darken by the day. Ash rides the wind; even the rivers carry cinders.

                        To stand against %s, you'll need more than steel and magic. You'll need a spark the ash cannot smother.
                
                        Your first steps lead to the Cinder Gate. Beyond it, the path divides north into the Whispering Fields,
                        
                        east along the Old Road.
                
                        Walk steady, %s. All of %s is watching.
                        """, world, villain, player.getName(),world), 25);
    }

    public static void menu(String[] items, boolean end){
        for (int i = 0; i < items.length; i++){
            System.out.printf("""
                    [%d] %s
                    """,(i+1), items[i]);
        }
        if(end) {
            System.out.println("""
                    
                    [0] End Your Journey
                    """);
        }
    }

    public static ArrayList<String> selectPath(ArrayList<String> path, String route1, String route2, Scanner scan){
        int response = -1;

        while(response == -1) {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    path.add(route1);
                    break;
                case 2:
                    path.add(route2);
                    break;
                default:
                    response = -1;
                    optInvalid("path");
            }
        }
        return path;
    }

    public static ArrayList<String> startJourney(RPGCharacter player,ArrayList<String> path, Scanner scan) {
        typeOut(String.format("""
            The Cinder Gate creaks as it parts, and the road spills into the wilds of %s.

            Ash drifts like pale snow. Far off, a bell tolls once.
            
            "Remember," says the Dawnwatcher, lifting the lantern, "the shadow of %s is long...but not endless."
            """, world, villain), 25);

        typeOut("""
            You tighten your straps and step beyond the light.

            To the North, the Whispering Fields breathe in waves of silver grass.
            
            To the East, the Old Road runs toward Embercross and its watch-fires.
            """, 25);

        typeOut(String.format("""
            Your first choice will set the rhythm of your legend, %s.

            Where do your feet carry you?
            """, player.getName()), 25);

        menu(new String[]{"Head North into the Whispering Fields","Follow the Old Road East to Embercross"}, false);

        return selectPath(path,"Whispering Fields","Embercross", scan);
    }

    public static void firstEncounter(RPGCharacter player, ArrayList<String> path, Scanner scan) {
        RPGCharacter enemy;
        RPGCharacter victorious = null;
        int fight = 0;

        switch (path.get(0)) {
            case "Whispering Fields":
                enemy = new RPGCharacter("Giant Ash-Wolf",new Enemy(70.0,new Fangs(
                        "Razor-Sharp Fangs", 15.0),50, new Evade()),1,false);

                typeOut("""
                        Silver grass brushes your knees as you step into the Whispering Fields.
                        
                        The wind carries voices of long forgotten souls.
            
                        Ahead, the stalks part. A Giant Ash-Wolf pads into view, ribs like blackened lattice,
                        
                        eyes ember-bright; standing at the height of two men.
                        
                        It hunches down, bears its teeth and growls at you with hunger in its eyes.
                        """, 25);
                typeOut(String.format("""
                        The Fields watch, and the lantern-light is far behind.
            
                        You draw %s and steel yourself, ready to battle the ferocious beast!
                        """, player.getRole().getWeapon().name()), 25);

                victorious = Combat(player,enemy,scan);
                fight = 1;

                break;

            case "Embercross":
                enemy = new RPGCharacter("Ember Knight",new Enemy(75.0,new Sword(
                        "Rusted Ember Sword",17),50, new Charge()),1,false);
                typeOut(String.format("""
                        The Old Road unwinds like a scar. Milestones lean; cart tracks vanish into ash.
                        
                        A toppled wagon smolders ahead, its wheels half-buried.
            
                        A lone Ember-Knight stands guard, armor soot-dulled, visor glowing like a coal.
                        
                        The sigil of %s is scorched into his shield.
                        """, villain), 25);
                typeOut(String.format("""
                        Your foot steps alert the Knight! He draws his sword and bellows a hellish shriek of the ember-cursed.
                        
                        You draw %s and ready for combat for this Knight intends to take your head.
                        """,player.getRole().getWeapon().name()),25);

                victorious = Combat(player,enemy,scan);
                fight = 2;

                break;

        }

        assert victorious != null;
        if(victorious.equals(player)){
            switch(fight){
                case 1:
                    ashWolfVictory(player);
                    break;
                case 2:
                    emberKnightVictory(player);
                    break;
            }
        }else {
            isGameOver(player,scan,false);
        }
    }

    public static void ashWolfVictory(RPGCharacter player) {
        typeOut("""
            With a final snarl, the Giant Ash-Wolf comes apart. No blood, only drifting cinders and a curl of smoke.
            
            The Whispering Fields fall quiet.
            """, 25);

        typeOut("""
            You steady your breath and lower your weapon. Ember-bright eyes gutter out, leaving only gray.
            
            A brittle fang of cooled ash rests where the beast fell.
            """, 25);

        typeOut(String.format("""
            "First step taken," the wind seems to say.
            
            Walk on, %s, the Fields will remember.
            """, player.getName()), 25);
    }

    public static void emberKnightVictory(RPGCharacter player) {
        typeOut("""
            With a hollow clang, the Ember-Knight drops to one knee. Cracks glow, then dim;
            the heat sighs out and the coal within gutters to gray.

            The Old Road falls still.
            """, 25);

        typeOut("""
            You steady your breath and lower your weapon. Soot drifts like snow around the ruined helm.
            Among the ash lies a shard of emberplate, its sigil of the Ash King scorched but unbroken.
            """, 25);

        typeOut(String.format("""
            "One lantern brighter," the Dawnwatcher would say.

            Walk on, %s, the Old Road will remember.
            """, player.getName()), 25);
    }

    public static RPGCharacter Combat(RPGCharacter player, RPGCharacter enemy, Scanner scan) {
        Role rEnemy = enemy.getRole();
        Role rPlayer = player.getRole();
        String weapon = rPlayer.getWeapon().name();
        boolean dodge;

        while(!enemy.isDead() && !player.isDead()) {
            if(!player.isDead()) {
                dodge = false;
                //Status
                typeOut(String.format("""
                        %s: %.2f HEALTH | LEVEL: %d
                        """, enemy.getName(), rEnemy.getHealth(), enemy.getLevel()), 25);
                typeOut(String.format("""
                        %s: %.2f HEALTH | LEVEL: %d | RESOURCE: %d
                        """,player.getName(),rPlayer.getHealth(),player.getLevel(),rPlayer.getResource()),25);

                //Menu
                menu(new String[]{rPlayer.useWeapon() + " | DMG: " + rPlayer.getWeapon().damage(),
                        rPlayer.getAbility().getName() + " | RESOURCE COST: " + rPlayer.getAbility().cost()}, false);

                int response = scan.nextInt();
                boolean moveEnemy = moveEnemyRandom();

                //Action Choice
                switch (response) {
                    case 1: //Use Weapon
                        if (moveEnemy && rEnemy.getAbility().type().equals("evasion")){
                            typeOut(String.format("""
                                    %s evades your attack!
                                    """,enemy.getName()),25);
                        }else {
                            rEnemy.setHealth(rEnemy.getHealth() - rPlayer.getWeapon().damage());
                            typeOut(String.format("""
                                    You %s!
                                    
                                    You strike %s with %s, for %.2f damage!
                                    """,rPlayer.getWeapon().attackType(),enemy.getName(), weapon, rPlayer.getWeapon().damage()), 25);
                        }
                        break;

                    case 2: //Use Ability
                        if(rPlayer.getResource() >= rPlayer.getAbility().cost()) {
                            switch (rPlayer.getAbility().type()) {
                                case "damage":
                                    rPlayer.setResource(rPlayer.useAbility());
                                    if (moveEnemy && rEnemy.getAbility().type().equals("evasion")){
                                        typeOut(String.format("""
                                                %s evades your %s!
                                                
                                                Your rage builds...
                                                """,enemy.getName(),rPlayer.getAbility().getName())
                                                ,25);
                                    }else {
                                        rEnemy.setHealth(rPlayer.getWeapon().useWeapon(rEnemy.getHealth()));
                                        typeOut(String.format("""
                                                You charge %s head first and strike them in the chest knocking them back!
                                                
                                                Your rage is sated, for now...
                                                """,enemy.getName()),25);
                                    }
                                    break;
                                case "evasion":
                                    rPlayer.setResource(rPlayer.useAbility());
                                    if (moveEnemy && rEnemy.getAbility().type().equals("evasion")){
                                        typeOut("""
                                                Your foe matches your attempt to evade the other's attack.
                                                
                                                They seem cunning...
                                                """,25);
                                    }else{
                                        typeOut(String.format("""
                                                You evade %s's attack!
                                                
                                                Your speed and cunning have no equal...
                                                """,enemy.getName()),25);
                                        dodge =  true;
                                    }
                                    break;
                                case "heal":
                                    rPlayer.setResource(rPlayer.useAbility());
                                    if (rPlayer.getHealth() >= 90.0) {
                                        typeOut("""
                                                Your attempt to heal beyond your capacity fails, a foolish endeavor no doubt...
                                                """, 25);
                                    } else {
                                        rPlayer.setHealth(rPlayer.getHealth() + rPlayer.getAbility().getHeal());
                                        typeOut("""
                                                You use the aether to restore your life-force and mend your wounds!
                                                
                                                You feel the energy flow within your veins...
                                                """,25);
                                    }
                                    break;
                                default:
                                    optInvalid("combat option");
                            }
                        }else {
                            typeOut("""
                                    You have exhausted yourself! Your vision swims and your reserves run dry.
                                    
                                    You cannot use that ability right now...
                                    """,25);
                        }
                }
                if(rEnemy.getHealth() <= 0) {
                    enemy.setDead(true);
                    break;
                }

                if (!moveEnemy && !dodge) {
                    rPlayer.setHealth(rEnemy.getWeapon().useWeapon(rPlayer.getHealth()));
                    typeOut(String.format("""
                            %s strikes with %s!
                            
                            The attack hits! You lose %.2f of your life-force. Be wary of the next attack, hero...
                            """, enemy.getName(), rEnemy.getWeapon().name(), rEnemy.getWeapon().damage()), 25);
                }

                typeOut(String.format("""
                            You reset your stance and ready %s...
                            """,rPlayer.getWeapon().name()),25);
            } else {
                typeOut(String.format("""
                        %s strikes a fatal blow!
                        
                        You fall to your knees and drop %s at your side as the world turns dark...
                        """,enemy.getName(),rPlayer.getWeapon().name()),25);
                return enemy;
            }
        }
        return player;
    }

    public static boolean moveEnemyRandom(){
        double random = Math.random();

        return random < 0.3;
    }

}

