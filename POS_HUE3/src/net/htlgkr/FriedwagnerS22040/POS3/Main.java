package net.htlgkr.FriedwagnerS22040.POS3;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Main
{
    public static void main(String[] args)
    {
        //1.5
        WeaponManager weaponManager = new WeaponManager();
        List<Weapon> weaponList = weaponManager.readFile();
        weaponManager.sortByDamage(weaponList);

        Printable printable = System.out::println;
        printable.print(weaponList);


        //1.6
        printable = weapons -> {
            int[] maxLengthArray = new int[7];

            weapons.stream().max(Comparator.comparingInt(o -> o.getName().length())).ifPresent(weapon -> maxLengthArray[0] = weapon.getName().length());
            weapons.stream().max(Comparator.comparingInt(o -> o.getCombatType().toString().length())).ifPresent(weapon -> maxLengthArray[1] = weapon.getCombatType().toString().length());
            weapons.stream().max(Comparator.comparingInt(o -> o.getDamageType().toString().length())).ifPresent(weapon -> maxLengthArray[2] = weapon.getDamageType().toString().length());
            weapons.stream().max(Comparator.comparingInt(o -> String.valueOf(o.getDamage()).length())).ifPresent(weapon -> maxLengthArray[3] = String.valueOf(weapon.getDamage()).length());
            weapons.stream().max(Comparator.comparingInt(o -> String.valueOf(o.getSpeed()).length())).ifPresent(weapon -> maxLengthArray[4] = String.valueOf(weapon.getSpeed()).length());
            weapons.stream().max(Comparator.comparingInt(o -> String.valueOf(o.getStrength()).length())).ifPresent(weapon -> maxLengthArray[5] = String.valueOf(weapon.getStrength()).length());
            weapons.stream().max(Comparator.comparingInt(o -> String.valueOf(o.getValue()).length())).ifPresent(weapon -> maxLengthArray[6] = String.valueOf(weapon.getValue()).length());

            int counter = 0;

            for (Field declaredField : Weapon.class.getDeclaredFields())
            {
                maxLengthArray[counter] = Integer.max(maxLengthArray[counter], declaredField.getName().length() +2);
                counter++;
            }

            for (int i = 0; i < maxLengthArray.length; i++) {
                System.out.print("+");
                for (int j = 0; j < maxLengthArray[i]; j++) {
                    System.out.print("-");
                }
            }

            System.out.println("+");

            counter = 0;
            for (Field field : Weapon.class.getDeclaredFields())
            {
                int emptySpace = maxLengthArray[counter] - field.getName().length();

                int spaceLeft = emptySpace/2;
                int spaceRight = emptySpace%2 != 0 ? spaceLeft+1 : spaceLeft;

                System.out.print("|" + " ".repeat(spaceLeft) + field.getName() + " ".repeat(spaceRight));
                counter++;
            }
            System.out.println("|");

            for (int i = 0; i < maxLengthArray.length; i++) {
                System.out.print("+");
                for (int j = 0; j < maxLengthArray[i]; j++) {
                    System.out.print("-");
                }
            }
            System.out.println("+");

            for (Weapon weapon : weapons)
            {
                counter = 0;
                for (Field field : Weapon.class.getDeclaredFields())
                {
                    field.setAccessible(true);
                    int emptySpace = 0;
                    try {
                        emptySpace = maxLengthArray[counter] - field.get(weapon).toString().length();
                        int spaceLeft = emptySpace/2;
                        int spaceRight = emptySpace%2 != 0 ? spaceLeft+1 : spaceLeft;

                        System.out.print("|" + " ".repeat(spaceLeft) + field.get(weapon) + " ".repeat(spaceRight));
                    } catch (IllegalAccessException e) {
                        e.getMessage();
                    }
                    counter++;
                }
                System.out.println("|");
            }
        };
        printable.print(weaponList);
    }
}
