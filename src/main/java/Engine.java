import java.util.Scanner;

public class Engine{
    public static void startRPG(){
        boolean blGameOver = false;
        Scanner scan = new Scanner(System.in);

        while(!blGameOver){

            gameIntro();

            menu(new String[]{"Barbarian", "Ranger", "Mage"},false);
            Character player = createCharacter(scan);

            if(player.isDead()){
                blGameOver = true;
            }else{

            }
        }
    }


    public static Character createCharacter(Scanner scan){
        int response = -1;

        while(response != 0) {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    return new Character(nameCharacter(scan),new Barbarian(110.0, chooseSword(scan),80,
                            new Charge()),false);
                case 2:
                    return new Character(nameCharacter(scan),new Ranger(100.0,chooseBow(scan),100,
                            new Evade()),false);
                case 3:
                    return new Character(nameCharacter(scan),new Mage(90.0,chooseStaff(scan),110,
                            new Restore()),false);
                default:
                    //TODO invalid choice notification
                    optInvalid("character class");
                    break;
            }
        }
        return null;
    }

    public static String nameCharacter(Scanner scan){
        return scan.nextLine();
    }

    public static Sword chooseSword(Scanner scan){
        int response = -1;
        while(response != 0) {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    return new Sword("Shadowfang",25);
                case 2:
                    return  new Sword("Embercleaver",20);
                case 3:
                    return new Sword("Frostbane",23);
                default:
                    optInvalid("sword");
                    break;
            }
        }
        return null;
    }

    public static Bow chooseBow(Scanner scan){
        int response = -1;
        while(response != 0) {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    return new Bow("Quicksilver",10);
                case 2:
                    return new Bow("Moonshot",13);
                case 3:
                    return new Bow("Heartseeker",12);
                default:
                    optInvalid("bow");
                    break;
            }
        }
        return null;
    }

    public static Staff chooseStaff(Scanner scan){
        int response = -1;
        while(response != 0) {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    return  new Staff("Voidlight Scepter",17);
                case 2:
                    return  new Staff("Stormoak Rod",15);
                case 3:
                    return  new Staff("Dawnfire Aegis",14);
                default:
                    optInvalid("staff");
                    break;
            }
        }
        return null;
    }

    public static void optInvalid(String option){
        System.out.printf("Please choose a valid %s.",option);
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

    public static void gameIntro(){
        typeOut("""
                    The world of Aethros has always teetered between light and shadow.
                    
                    Kingdoms rise, only to fall into dust. Old gods whisper from forgotten temples.
                    
                    Strange stars burn above, heralding change.
                    """,25);
        typeOut("""
                    Now, fate turns to you.
                    
                    Will you walk the path of blade, bow, or spell?
                    
                    Will you carve your legend in steel and fire, or vanish like so many nameless wanderers before you?
                    """,25);
        typeOut("""
                   Tell me, traveler…
                   Who are you?
                   """,25);
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

