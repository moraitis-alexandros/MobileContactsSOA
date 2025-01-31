package mobilecontacts;

import mobilecontacts.controller.MobileContactController;
import mobilecontacts.dto.MobileContactInsertDTO;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final MobileContactController controller = new MobileContactController();


    public static void main(String[] args) {
        String choice;

        while (true) {

            printMenu();
            choice = getToken();

            if (choice.equals("q") || choice.equals("Q")) {
                break;
            }

            handleChoice(choice);

        }

        System.out.println("Thank you for using the app");
    }

public static void printMenu() {
    System.out.println("Choose one option");
    System.out.println("1.Insert Contact");
    System.out.println("2.Update Contact");
    System.out.println("3.Delete Contact");
    System.out.println("4.Search Contact");
    System.out.println("5.View Contacts");
    System.out.println("Exit");
}

public static String getToken() {
        return in.nextLine().trim();
}


public static void handleChoice(String choice) {

    String firstname;
    String lastname;
    String phoneNumber;
    String response;
        switch (choice) {
            case "1":
                System.out.println("PLease insert firstname, lastname, phone number");
                firstname = in.nextLine();
                lastname = in.nextLine();
                phoneNumber = in.nextLine();
                MobileContactInsertDTO mobileContactInsertDTO = new MobileContactInsertDTO(firstname, lastname, phoneNumber);
                response = controller.insertContact(mobileContactInsertDTO);

                if(response.startsWith("OK")) {
                    System.out.println("Successful Insert");
                    System.out.println(response.substring(3));
                } else {
                    System.out.println("Unsuccessful Insert" + response.substring(7));
                }



                break;
            case  "2":
                //
                break;
            case "3":
                //
                break;
            case "4":
                //
                break;
            case "5":
                //
                break;
            default:
                //
                break;

        }


}


}//class
