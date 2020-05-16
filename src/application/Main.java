package application;

import RSA.Keys;
import RSA.Messages;

import java.math.BigInteger;
import java.util.Scanner;

import static RSA.Cryptography.decrypt;
import static RSA.Cryptography.encrypt;

public class Main {

    public static boolean showSteps= false;

    public static void main(String[] args){

        Keys keys= new Keys();

        if(showSteps){

            System.out.println("Public key e part: "+keys.PublicKey.e);
            System.out.println("Public key n part: "+keys.PublicKey.n);
        }


        Scanner sc=new Scanner(System.in);
        String mess;
        Messages.Message message;
        System.out.print("Message> ");
        mess=sc.nextLine();
        boolean notFirstRead =false;
        while(!mess.equals("exit")) {
            if (notFirstRead) {
                mess = sc.nextLine();
            }

            //18 character limit on encryption using RSA 1024
            message=new Messages.Message(mess,false);

            if(showSteps) {
                System.out.println("Message: \"" + message.getMessage() + "\" transformed to number: " + message.getChars());
            }

            try {
                System.out.println(encrypt(message.getChars(), keys));
                System.out.println(decrypt(encrypt(message.getChars(), keys), keys));
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println("");
            System.out.print("Message> ");
            notFirstRead=true;

        }

    }
}
