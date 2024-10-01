import net.htlgkr.FriedwagnerS22040.POS3.CombatType;
import net.htlgkr.FriedwagnerS22040.POS3.DamageType;
import net.htlgkr.FriedwagnerS22040.POS3.WeaponManager;
import net.htlgkr.FriedwagnerS22040.POS3.Weapon;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Test {

    @org.junit.jupiter.api.Test
    void readFile()
    {
        List<Weapon> list = Arrays.asList(
                new Weapon("Crom Faeyr", CombatType.MELEE, DamageType.BLUNT, 16, 1, 25, 15500),
                new Weapon("Carsomyr", CombatType.MELEE, DamageType.SLASHING, 17, 5, 14, 20000),
                new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250),
                new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500)
        );

        WeaponManager weaponManager = new WeaponManager();
        List<Weapon> expectedList = weaponManager.readFile().subList(0,2);
        weaponManager.sortAlphapetic();

        assertArrayEquals(expectedList.toArray(), list.toArray());
    }


}