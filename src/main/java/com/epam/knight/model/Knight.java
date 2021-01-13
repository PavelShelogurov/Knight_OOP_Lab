package com.epam.knight.model;

import com.epam.knight.model.ammunition.Ammunition;
import com.epam.knight.model.ammunition.armors.Armor;
import com.epam.knight.model.ammunition.weapons.Weapon;

import java.util.Arrays;
import java.util.Comparator;

public class Knight {
    private static final SortByCost SORT_BY_COST = new SortByCost();
    private static final SortByWeight SORT_BY_WEIGHT = new SortByWeight();

    private Ammunition[] ammunition;

    public Ammunition[] getAmmunition() {
        return ammunition;
    }

    /**
     * Add new ammunition element to knight
     *
     * @param element that should be equipped to the knight
     */

    public Knight() {
        ammunition = new Ammunition[0];
    }

    public void equip(Ammunition element) {
        ammunition = Arrays.copyOf(ammunition, ammunition.length + 1);
        ammunition[ammunition.length - 1] = element;
    }

    public int calculateAmmunitionWeight() {
        int sumAmmunitionWeight = 0;
        for (Ammunition elementOfAmmunition : ammunition) {
            sumAmmunitionWeight += elementOfAmmunition.getWeight();
        }
        return sumAmmunitionWeight;
    }

    public int calculateAmmunitionCost() {
        int sumAmmunitionCost = 0;
        for (Ammunition elementOfAmmunition : ammunition) {
            sumAmmunitionCost += elementOfAmmunition.getCost();
        }
        return sumAmmunitionCost;
    }

    public int calculateAmmunitionDamage() {
        int sumDamage = 0;
        for (int i = 0; i < ammunition.length; i++) {
            if (ammunition[i] instanceof Weapon) {
                sumDamage += ((Weapon) ammunition[i]).getDamage();
            }
        }
        return sumDamage;
    }

    public int calculateAmmunitionProtection() {
        int sumProtection = 0;
        for (int i = 0; i < ammunition.length; i++) {
            if (ammunition[i] instanceof Armor) {
                sumProtection += ((Armor) ammunition[i]).getProtection();
            }
        }
        return sumProtection;
    }

    public Ammunition[] sortByCost() {
        Ammunition[] sortObjects = ammunition.clone();
        Arrays.sort(sortObjects, SORT_BY_COST);
        return sortObjects;
    }

    private static class SortByCost implements Comparator<Ammunition> {
        @Override
        public int compare(Ammunition object1, Ammunition object2) {
            if (object1.getCost() > object2.getCost()) {
                return 1;
            } else if (object1.getCost() < object2.getCost()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public Ammunition[] sortByWeight() {
        Ammunition[] sortObject = ammunition.clone();
        Arrays.sort(sortObject, SORT_BY_WEIGHT);
        return sortObject;
    }

    private static class SortByWeight implements Comparator<Ammunition> {
        @Override
        public int compare(Ammunition object1, Ammunition object2) {
            if (object1.getWeight() > object2.getWeight()) {
                return 1;
            } else if (object1.getWeight() < object2.getWeight()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public Ammunition[] searchByCost(int minCost, int maxCost) {
        Ammunition[] ammunitionSuitableForThePrice = new Ammunition[0];
        for (Ammunition ammunition : ammunition) {
            if (ammunition.getCost() >= minCost && ammunition.getCost() <= maxCost) {
                ammunitionSuitableForThePrice = Arrays.copyOf(ammunitionSuitableForThePrice, ammunitionSuitableForThePrice.length + 1);
                ammunitionSuitableForThePrice[ammunitionSuitableForThePrice.length - 1] = ammunition;
            }
        }
        return ammunitionSuitableForThePrice;
    }

    public Ammunition[] searchByWeight(int minWeight, int maxWeight) {
        Ammunition[] ammunitionSuitableForTheWeight = new Ammunition[0];
        for (Ammunition ammunition : ammunition) {
            if (ammunition.getWeight() >= minWeight && ammunition.getWeight() <= maxWeight) {
                ammunitionSuitableForTheWeight = Arrays.copyOf(ammunitionSuitableForTheWeight, ammunitionSuitableForTheWeight.length + 1);
                ammunitionSuitableForTheWeight[ammunitionSuitableForTheWeight.length - 1] = ammunition;
            }
        }
        return ammunitionSuitableForTheWeight;
    }


}