package sionois.terraduellum;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import sionois.terraduellum.Items.ItemDoorSteel;
import sionois.terraduellum.Items.ItemDoorWroughtIron;
import net.minecraft.item.Item;

public class TDItems
{	/**Items*/
	public static Item doorWroughtIron;
	public static Item doorSteel;
	
	/**IDs*/
	public static int wroughtIronItemID;
    public static int steelDoorItemID;
    
    /**Names*/
    public static String wroughtIronDoorName = "Wrought Iron Door";
    public static String steelDoorName = "Steel Door";
    
	public static void LoadItems()
	{
		doorWroughtIron = new ItemDoorWroughtIron(wroughtIronItemID).setUnlocalizedName(wroughtIronDoorName).setTextureName(ModRef.ModID + ":" + wroughtIronDoorName);
		doorSteel = new ItemDoorSteel(steelDoorItemID).setUnlocalizedName(steelDoorName).setTextureName(ModRef.ModID + ":" + steelDoorName);
	}
	public static void RegisterItems()
	{
		GameRegistry.registerItem(doorWroughtIron, "WroughtIronMetalDoor");
		GameRegistry.registerItem(doorSteel, "SteelMetalDoor");
	}
}
