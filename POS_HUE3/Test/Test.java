import net.htlgkr.FriedwagnerS22040.POS3.CombatType;
import net.htlgkr.FriedwagnerS22040.POS3.DamageType;
import net.htlgkr.FriedwagnerS22040.POS3.WeaponManager;
import net.htlgkr.FriedwagnerS22040.POS3.Weapon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Test {

    @org.junit.jupiter.api.Test
    void sortByDamage()
    {
        List<Weapon> list = Arrays.asList(
                new Weapon("Crom Faeyr", CombatType.MELEE, DamageType.BLUNT, 16, 1, 25, 15500),
                new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250),
                new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500)
        );

        WeaponManager weaponManager = new WeaponManager();
        List<Weapon> expectedList = weaponManager.readFile().subList(0,3).stream().collect(Collectors.toList());
        weaponManager.sortByDamage(expectedList);

        assertArrayEquals(expectedList.toArray(), list.toArray());
    }

    @org.junit.jupiter.api.Test
    void sortAlphabetic()
    {
        List<Weapon> list = Arrays.asList(
                new Weapon("Crom Faeyr", CombatType.MELEE, DamageType.BLUNT, 16, 1, 25, 15500),
                new Weapon("Carsomyr", CombatType.MELEE, DamageType.SLASHING, 17, 5, 14, 20000),
                new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250),
                new Weapon("Broken Weapon", CombatType.NONE, DamageType.NONE, 0, 0, 0, 0),
                new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500)
        );

        WeaponManager weaponManager = new WeaponManager();
        List<Weapon> expectedList = weaponManager.readFile().subList(0,5).stream().collect(Collectors.toList());
        weaponManager.sortAlphabetic(expectedList);

        assertArrayEquals(expectedList.toArray(), list.toArray());
    }


}