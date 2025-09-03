import java.util.Scanner;

public class Engine{
    private static final String world = "Aethros";
    private static final String villain = "The Ash King";

    public static void start(){
        boolean blGameOver = false;
        Scanner scan = new Scanner(System.in);

        gameIntro();

        //Character creation
        Character player = createCharacter(scan);

        postCharacterCreation(player);
        //Game loop
        while(!blGameOver){



            blGameOver = isGameOver(player, scan);
        }
    }

    private static boolean isGameOver(Character player, Scanner scan) {
        if(player.isDead()){
            System.out.println(player.getName() + "'s light has faded away...");
            System.out.println("");
            menu(new String[]{"Try again?"},true);
            if(scan.nextInt() == 0) {
                System.out.println("GAME OVER");
                return true;
            }
        }
        return false;
    }

    public static Character createCharacter(Scanner scan){
        int response = -1;
        Character player = null;

        while(player == null) {
            menu(new String[]{"Barbarian", "Ranger", "Mage"},false);
            response = scan.nextInt();
            scan.nextLine();
            switch (response) {
                case 1:
                    player = new Character(namePlayerCharacter(scan,"Barbarian"),new Barbarian(110.0, chooseSword(scan),80,
                            new Charge()),false);
                    break;
                case 2:
                    player =  new Character(namePlayerCharacter(scan,"Ranger"),new Ranger(100.0,chooseBow(scan),100,
                            new Evade()),false);
                    break;
                case 3:
                    player =  new Character(namePlayerCharacter(scan,"Mage"),new Mage(90.0,chooseStaff(scan),110,
                            new Restore()),false);
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

    public static Sword chooseSword(Scanner scan) {
        String weapon = "swords";
        String role = "Barbarian";
        typeOut(String.format("""
                Ah, I see, well then you will be needing a weapon. Which of these %s catches your eye, %s?
                """,weapon,role), 25);

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

    public static Bow chooseBow(Scanner scan) {
        String weapon = "bows";
        String role = "Ranger";
        typeOut(String.format("""
                Ah, I see, well then you will be needing a weapon. Which of these %s catches your eye, %s?
                """,weapon,role), 25);

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
                    optInvalid("bow");
                    break;
            }
        }
        return bow;
    }

    public static Staff chooseStaff(Scanner scan) {
        String weapon = "staves";
        String role = "Mage";
        typeOut(String.format("""
                Ah, I see, well then you will be needing a weapon. Which of these %s catches your eye, %s?
                """,weapon,role), 25);

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
        typeOut("Please choose a valid " + option + ".",25);
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

            Will you walk the path of blade, bow, or spell?

            Will you carve your legend in steel and fire, or vanish like so many nameless wanderers before you?
            """, 25);

        typeOut("""
            Tell me, traveler…
            Who are you?
            """, 25);
    }

    public static void postCharacterCreation(Character player){
        typeOut(String.format("""
                        These are worrying times, %s. The ash has been a blight upon this world.
                        
                        Some whisper the ash has a master...
                        
                        The one they call %s...
                        """, player.getName(),villain), 25);

        typeOut(String.format("""
                        You know...legend tells of a %s that will save %s from the clutches of %s.
                        
                        I pray that you, %s, are that hero...
                        """,
                player.getRole().type(), world, villain, player.getName()), 25);
        typeOut(String.format("""
                        The roads of %s darken by the day. Ash rides the wind; even the rivers carry cinders.

                        To stand against %s, you'll need more than steel and magic. You'll need a spark the ash cannot smother.
                
                        Your first steps lead to the Cinder Gate. Beyond it, the path divides—north into the Whispering Fields,
                        
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

}

