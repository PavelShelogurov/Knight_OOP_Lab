package com.epam.knight.controller;

import java.util.Scanner;

public class Controller {
    private static final String ERROR_COMMAND_NOT_FOUND = "This command does not exist";
    private static final String INVALID_NUMBER = "Invalid number! Try again";

    //методля для принятия значения от пользователя в диапозоне от from до to
    public int getNumberFromUser(int from, int to) {
        int numberFromUser = 0;
        boolean userEnterInteger = false;

        do {
            Scanner scannerNumber = new Scanner(System.in);
            if (scannerNumber.hasNextInt()) {
                do {
                    numberFromUser = scannerNumber.nextInt();
                    if (numberFromUser < from || numberFromUser > to) {
                        System.out.println(ERROR_COMMAND_NOT_FOUND);
                    }
                } while (numberFromUser < from || numberFromUser > to);
                userEnterInteger = true;
            } else {
                System.out.println(INVALID_NUMBER);
            }
        } while (!userEnterInteger);
        return numberFromUser;
    }

}
