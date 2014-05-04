package com.sionois.terraduellum;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.sionois.terraduellum.Items.ItemDoorSteel;
import com.sionois.terraduellum.Items.ItemDoorWroughtIron;

import net.minecraft.item.Item;
import TFC.TFCItems;
import cpw.mods.fml.common.registry.GameRegistry;

public class TDItems
{	
	public static final Set<Integer> Maces = new HashSet<Integer>(Arrays.asList(
			TFCItems.CopperMace.itemID, 
			TFCItems.BronzeMace.itemID, 
			TFCItems.BismuthBronzeMace.itemID, 
			TFCItems.BlackBronzeMace.itemID, 
			TFCItems.WroughtIronMace.itemID, 
			TFCItems.SteelMace.itemID, 
			TFCItems.BlackSteelMace.itemID, 
			TFCItems.RedSteelMace.itemID, 
			TFCItems.BlueSteelMace.itemID));
	
	public static final Set<Integer> Knives = new HashSet<Integer>(Arrays.asList(
			TFCItems.CopperKnife.itemID, 
			TFCItems.BronzeKnife.itemID, 
			TFCItems.BismuthBronzeKnife.itemID, 
			TFCItems.BlackBronzeKnife.itemID, 
			TFCItems.WroughtIronKnife.itemID, 
			TFCItems.SteelKnife.itemID, 
			TFCItems.BlackSteelKnife.itemID, 
			TFCItems.RedSteelKnife.itemID, 
			TFCItems.BlueSteelKnife.itemID));
	
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
