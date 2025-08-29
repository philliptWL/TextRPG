import java.util.Scanner;

public class Engine{
    public static boolean startRPG(){
        boolean blGameOver = false;
        Scanner scan = new Scanner(System.in);

        while(!blGameOver){
            Character player = createCharacter(scan);
        }

        return blGameOver;
    }


    public static Character createCharacter(Scanner scan){
        int response = -1;

        while(response != 0) {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    return new Character(nameCharacter(scan),new Barbarian(110.0, chooseSword(scan)),
                            false);
                case 2:
                    break;
                case 3:
                    break;
                default:
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
        Sword sword = null;
        while(response != 0) {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    sword = new Sword("Shadowfang",25);
                    break;
                case 2:
                    sword = new Sword("Embercleaver",20);
                    break;
                case 3:
                    sword = new Sword("Frostbane",23);
                    break;
                default:
                    break;
            }
        }
        return sword;
    }
}
