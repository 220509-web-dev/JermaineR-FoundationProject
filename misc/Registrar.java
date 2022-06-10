public class Registrar {
    public static void main(String[] args) {
        System.out.print("Welcome to our app!");
        System.out.print("Please make a seledtion from the optiond below");
        System.out.print("1)Register\n\t2) Login\n\t3) Exit");

        Scanner consoleReader = new Scanner(System.in);
        String userSelection = consoleReader.nextLine();

        switch (userSelection) {
            case "1":
                System.out.println("You selected register");
                break;
            case "2":
                System.out.println("You selected login");
                break;
            case "3":
                System.out.println("You selected exit");
                break;
            default:
                system.out.println("You have an invalid selection.");
                
        }

    }
}