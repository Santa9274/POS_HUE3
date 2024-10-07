package net.htlgkr.FriedwagnerS22040.POS3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WeaponManager
{
    private List<Weapon> arrayList = new ArrayList<>();

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
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return arrayList;
    }

    public void sortByDamage(List<Weapon> arrayList)
    {
        arrayList.sort((o1, o2) -> o2.getDamage() - o1.getDamage());
    }

    public void sortAlphabetic(List<Weapon> arrayList)
    {
        arrayList.sort((Comparator<Weapon>) (o1, o2) ->
        {
            int difference = o1.getCombatType().toString().compareTo(o2.getCombatType().toString());

            if (difference != 0) return difference;

            difference = o1.getDamageType().toString().compareTo(o2.getDamageType().toString());

            if (difference != 0) return difference;
            else return o1.getName().compareTo(o2.getName());
        });
    }
}
