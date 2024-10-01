package net.htlgkr.FriedwagnerS22040.POS3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeaponManager
{
    List<Weapon> arrayList;

    public List<Weapon> readFile()
    {
        try {
            arrayList = Files.lines(new File("weapons.csv").toPath())
                    .skip(1)
                    .map(s -> s.split(";"))
                    .map(s -> new Weapon(
                            s[0],
                            CombatType.valueOf(s[1]),
                            DamageType.valueOf(s[2]),
                            Integer.parseInt(s[3]),
                            Integer.parseInt(s[4]),
                            Integer.parseInt(s[5]),
                            Integer.parseInt(s[6])
                    ))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return arrayList;
    }

    public void sortByDamage()
    {
        arrayList.stream().sorted(Comparator.comparingInt(Weapon::getDamage));
    }

    public void sortAlphapetic()
    {
        arrayList.sort(
                Comparator.comparing((Weapon w) -> w.getCombatType().toString())
                        .thenComparing(w -> w.getDamageType().toString())
                        .thenComparing(Weapon::getName)
        );
    }
}
