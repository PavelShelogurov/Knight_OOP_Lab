package com.epam.knight.view;

import com.epam.knight.controller.Controller;
import com.epam.knight.model.Knight;
import com.epam.knight.model.ammunition.Ammunition;
import com.epam.knight.model.ammunition.armors.Helmet;
import com.epam.knight.model.ammunition.weapons.Sword;
import com.epam.knight.view.enums.TypeOfAction;
import com.epam.knight.view.enums.TypeOfAmmunition;
import com.epam.knight.view.enums.TypeOfSort;

public class ConsoleView {
    private static final String MAIN_MENU = "Main menu:";
    private static final String MENU_ITEM_1 = "1. Print knight status";
    private static final String MENU_ITEM_2 = "2. Show ammunition";
    private static final String MENU_ITEM_3 = "3. Equip ammunition";
    private static final String MENU_ITEM_4 = "4. Sort ammunition";
    private static final String MENU_ITEM_5 = "5. Search ammunition";
    private static final String MENU_ITEM_6 = "6. Exit";
    private static final String CHOSE_OPTION = "Choose option:";
    private static final String WHAT_KNIGHT_OF_AMMUNITION = "What kind of ammunition do you want to equip? \n 1. Sword \n 2. Helmet";
    private static final String SORT_AMMUNITION = "Choose sort type:\n 1. Cost\n 2. Weight";
    private static final String SEARCH_AMMUNITION = "Choose search field:\n 1. Cost\n 2. Weight";
    private static final String AMMUNITION_NOT_FOUND = "Ammunition not found!";
    private static final String FIREWALL = "Bue!";
    private static final String INPUT_SWORD = "Input sword ";
    private static final String INPUT_HELMET = "Input helmet ";
    private static final String INPUT_MINIMUM = "Input minimum ";
    private static final String INPUT_MAXIMUM = "Input maximum ";
    private static final String COST = "cost:";
    private static final String DAMAGE = "damage:";
    private static final String WEIGHT = "weight:";
    private static final String PROTECTION = "protection:";
    private static final String AMMUNITION = "Ammunition ";

    public void showMainMenu(Knight knight, Controller controller) {
        TypeOfAction typeOfAction;
        do {
            System.out.println(MAIN_MENU);
            System.out.println(MENU_ITEM_1);
            System.out.println(MENU_ITEM_2);
            System.out.println(MENU_ITEM_3);
            System.out.println(MENU_ITEM_4);
            System.out.println(MENU_ITEM_5);
            System.out.println(MENU_ITEM_6);
            System.out.println(CHOSE_OPTION);
            int numberOfTask = controller.getNumberFromUser(1, 6);
            typeOfAction = TypeOfAction.values()[numberOfTask - 1];
            switch (typeOfAction) {
                case PRINT_KNIGHT_STATUS:
                    printKnightStatus(knight);
                    break;
                case SHOW_AMMUNITION:
                    showAmmunitionToScreen(knight.getAmmunition());
                    break;
                case EQUIP_AMMUNITION:
                    equipAmmunition(knight, controller);
                    break;
                case SORT_AMMUNITION:
                    sortAmmunition(knight, controller);
                    break;
                case SEARCH_AMMUNITION:
                    searchAmmunition(knight, controller);
                    break;
                case EXIT:
                    System.out.println(FIREWALL);
                    break;
            }
        } while (typeOfAction != TypeOfAction.EXIT);


    }

    private void printKnightStatus(Knight knight) {
        System.out.println(AMMUNITION + COST + knight.calculateAmmunitionCost());
        System.out.println(AMMUNITION + WEIGHT + knight.calculateAmmunitionWeight());
        System.out.println(AMMUNITION + DAMAGE + knight.calculateAmmunitionDamage());
        System.out.println(AMMUNITION + PROTECTION + knight.calculateAmmunitionProtection());
    }

    private void equipAmmunition(Knight knight, Controller controller) {
        System.out.println(WHAT_KNIGHT_OF_AMMUNITION);
        System.out.println(CHOSE_OPTION);
        int numberOfAmmunition = controller.getNumberFromUser(1, 2);
        TypeOfAmmunition typeOfAmmunition = TypeOfAmmunition.values()[numberOfAmmunition - 1];
        switch (typeOfAmmunition) {
            case SWORD:
                knight.equip(addSword(controller));
                break;
            case HELMET:
                knight.equip(addHelmet(controller));
                break;
        }
    }

    private void sortAmmunition(Knight knight, Controller controller) {
        System.out.println(SORT_AMMUNITION);
        System.out.println(CHOSE_OPTION);
        int numberOfSort = controller.getNumberFromUser(1, 2);
        TypeOfSort typeOfSort = TypeOfSort.values()[numberOfSort - 1];
        switch (typeOfSort) {
            case COST:
                showAmmunitionToScreen(knight.sortByCost());
                break;
            case WEIGHT:
                showAmmunitionToScreen(knight.sortByWeight());
                break;
        }
    }

    private void searchAmmunition(Knight knight, Controller controller) {
        System.out.println(SEARCH_AMMUNITION);
        System.out.println(CHOSE_OPTION);
        int numberOfSearch = controller.getNumberFromUser(1, 2);
        TypeOfSort typeOfSearch = TypeOfSort.values()[numberOfSearch - 1];
        switch (typeOfSearch) {
            case COST:
                System.out.println(INPUT_MINIMUM + COST);
                int minCost = controller.getNumberFromUser(0, Integer.MAX_VALUE);
                System.out.println(INPUT_MAXIMUM + COST);
                int maxCost = controller.getNumberFromUser(0, Integer.MAX_VALUE);
                showAmmunitionToScreen(knight.searchByCost(minCost, maxCost));
                break;
            case WEIGHT:
                System.out.println(INPUT_MINIMUM + WEIGHT);
                int minWeight = controller.getNumberFromUser(0, Integer.MAX_VALUE);
                System.out.println(INPUT_MAXIMUM + WEIGHT);
                int maxWeight = controller.getNumberFromUser(0, Integer.MAX_VALUE);
                showAmmunitionToScreen(knight.searchByWeight(minWeight, maxWeight));
                break;
        }
    }

    private Sword addSword(Controller controller) {
        System.out.println(INPUT_SWORD + COST);
        int cost = controller.getNumberFromUser(0, Integer.MAX_VALUE);
        System.out.println(INPUT_SWORD + WEIGHT);
        int weight = controller.getNumberFromUser(0, Integer.MAX_VALUE);
        System.out.println(INPUT_SWORD + DAMAGE);
        int damage = controller.getNumberFromUser(0, Integer.MAX_VALUE);
        return new Sword(damage, weight, cost);
    }

    private Helmet addHelmet(Controller controller) {
        System.out.println(INPUT_HELMET + COST);
        int cost = controller.getNumberFromUser(0, Integer.MAX_VALUE);
        System.out.println(INPUT_HELMET + WEIGHT);
        int weight = controller.getNumberFromUser(0, Integer.MAX_VALUE);
        System.out.println(INPUT_HELMET + PROTECTION);
        int protection = controller.getNumberFromUser(0, Integer.MAX_VALUE);
        return new Helmet(protection, weight, cost);
    }

    private void showAmmunitionToScreen(Ammunition[] ammunition) {
        if (ammunition == null || ammunition.length == 0) {
            System.out.println(AMMUNITION_NOT_FOUND);
        } else {
            for (Ammunition oneAmmunition : ammunition) {
                System.out.println(oneAmmunition);
            }
        }
    }

}
