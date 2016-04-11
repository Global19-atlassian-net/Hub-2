package net.samagames.hub.games.types;

import net.samagames.hub.Hub;
import net.samagames.hub.games.AbstractGame;
import net.samagames.hub.games.shops.ShopCategory;
import net.samagames.hub.games.shops.ShopImprovableItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class DimensionsGame extends AbstractGame
{
    public DimensionsGame(Hub hub)
    {
        super(hub);
    }

    @Override
    public String getCodeName()
    {
        return "dimensions";
    }

    @Override
    public String getName()
    {
        return "Dimensions";
    }

    @Override
    public String getCategory()
    {
        return "PvP";
    }

    @Override
    public ItemStack getIcon()
    {
        return new ItemStack(Material.EYE_OF_ENDER, 1);
    }

    @Override
    public String[] getDescription()
    {
        return new String[] {
                "Téléportez-vous d'un monde parallèle",
                "à un autre et récupérez le maximum de",
                "coffres. Ensuite, le combat pourra",
                "débuter !"
        };
    }

    @Override
    public String[] getDeveloppers()
    {
        return new String[] {
                "Silvanosky",
                "zyuiop"
        };
    }

    @Override
    public int getSlotInMainMenu()
    {
        return 23;
    }

    @Override
    public ShopCategory getShopConfiguration()
    {
        ShopCategory parentCategory = new ShopCategory(this.hub, this, null, null, null, -1, null);

        ShopImprovableItem strenghtOnKill = new ShopImprovableItem(this.hub, this, "strengthAtKill", "Force au meurtre", new Potion(PotionType.STRENGTH).toItemStack(1), 11, new String[] {
                "Quand vous causerez un meurtre,",
                "vous aurez un bonus de force pendant un",
                "certain temps suivant le niveau de cette",
                "amélioration."
        });

        strenghtOnKill.addLevel(900, "3 secondes", "3");
        strenghtOnKill.addLevel(2800, "5 secondes", "5");
        strenghtOnKill.addLevel(6500, "7 secondes", "7");
        strenghtOnKill.addLevel(14700, "9 secondes", "9");
        strenghtOnKill.addLevel(26100, "10 secondes", "10");
        strenghtOnKill.addLevel(40000, "11 secondes", "11");

        parentCategory.addContent(strenghtOnKill);

        ShopImprovableItem healOnStrikeItem = new ShopImprovableItem(this.hub, this, "healAtStrike", "Soin au combat", new Potion(PotionType.INSTANT_HEAL).toItemStack(1), 15, new String[] {
                "Pendant un combat, vous avez un certain",
                "pourcentage de chance de regagner de la vie",
                "suivant le niveau de cette amélioration."
        });

        healOnStrikeItem.addLevel(200, "2% de chance", "2");
        healOnStrikeItem.addLevel(800, "4% de chance", "4");
        healOnStrikeItem.addLevel(2300, "6% de chance", "6");
        healOnStrikeItem.addLevel(5500, "7% de chance", "7");
        healOnStrikeItem.addLevel(11200, "8% de chance", "8");
        healOnStrikeItem.addLevel(26600, "9% de chance", "9");
        healOnStrikeItem.addLevel(40000, "10% de chance", "10");

        parentCategory.addContent(healOnStrikeItem);

        ShopImprovableItem healOnKillItem = new ShopImprovableItem(this.hub, this, "healAtKill", "Soin au meurtre", new ItemStack(Material.SPECKLED_MELON, 1), 30, new String[] {
                "Quand vous causerez un meurtre,",
                "vous serez plus ou moins soigné",
                "suivant le niveau de cette amélioration."
        });

        healOnKillItem.addLevel(400, "1 coeur", "2");
        healOnKillItem.addLevel(3200, "2 coeurs", "4");
        healOnKillItem.addLevel(9100, "3 coeurs", "6");
        healOnKillItem.addLevel(24700, "4 coeurs", "8");
        healOnKillItem.addLevel(60000, "5 coeurs", "10");

        parentCategory.addContent(healOnKillItem);

        ShopImprovableItem swapCooldownItem = new ShopImprovableItem(this.hub, this, "tpTime", "Espacement de Swap", new ItemStack(Material.EYE_OF_ENDER, 1), 32, new String[] {
                "Le délai entre deux téléportations diminue",
                "suivant le niveau de cette amélioration."
        });

        swapCooldownItem.addDefault("17 secondes");
        swapCooldownItem.addLevel(1200, "14 secondes", "14");
        swapCooldownItem.addLevel(4600, "12 secondes", "12");
        swapCooldownItem.addLevel(9800, "10 secondes", "10");
        swapCooldownItem.addLevel(17400, "8 secondes", "8");
        swapCooldownItem.addLevel(35000, "7 secondes", "7");

        parentCategory.addContent(swapCooldownItem);

        return parentCategory;
    }

    @Override
    public Location getLobbySpawn()
    {
        return new Location(this.hub.getWorld(), 171.5D, 78.0D, -31.5D, -90.0F, 0.0F);
    }

    @Override
    public boolean isLocked()
    {
        return false;
    }

    @Override
    public boolean isNew()
    {
        return false;
    }
}