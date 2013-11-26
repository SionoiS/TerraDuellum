package sionois.terraduellum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import TFC.TFCItems;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import sionois.terraduellum.Items.ItemDoorSteel;
import sionois.terraduellum.Items.ItemDoorWroughtIron;
import net.minecraft.item.Item;

public class TDItems
{	
	public static final Set<Item> Maces = new HashSet<Item>(Arrays.asList(
			TFCItems.CopperMace, 
			TFCItems.BronzeMace, 
			TFCItems.BismuthBronzeMace, 
			TFCItems.BlackBronzeMace, 
			TFCItems.WroughtIronMace, 
			TFCItems.SteelMace, 
			TFCItems.BlackSteelMace, 
			TFCItems.RedSteelMace, 
			TFCItems.BlueSteelMace));
	
	public static final Set<Item> Knives = new HashSet<Item>(Arrays.asList(
			TFCItems.CopperKnife, 
			TFCItems.BronzeKnife, 
			TFCItems.BismuthBronzeKnife, 
			TFCItems.BlackBronzeKnife, 
			TFCItems.WroughtIronKnife, 
			TFCItems.SteelKnife, 
			TFCItems.BlackSteelKnife, 
			TFCItems.RedSteelKnife, 
			TFCItems.BlueSteelKnife));
	
	public static final Set<Integer> DoorsId = new HashSet<Integer>(Arrays.asList(
			2041, 2042, 2043, 2044, 2045, 2046, 2047, 2048,
			2049, 2050, 2051, 2052, 2053, 2054, 2055, 2056));
	
	/**Items*/
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
