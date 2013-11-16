package sionois.terraduellum;

import TFC.TFCBlocks;
import sionois.terraduellum.Blocks.BlockDungeon;
import sionois.terraduellum.Blocks.BlockSteelDoor;
import sionois.terraduellum.Blocks.BlockWroughtIronDoor;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;

public class TDBlocks
{
	public static Block dungeonBlock;    
    public static Block wroughtIronDoorBlock;
    public static Block steelDoorBlock;
    
    public static int dungeonBlockID;
    public static int wroughtIronDoorBlockID;
    public static int steelDoorBlockID;
    
    public static String dungeonBlockName = "Dungeon Block";
    
	public static void LoadBlocks()
	{
		dungeonBlock = new BlockDungeon(dungeonBlockID).setHardness(13F).setResistance(15F).setUnlocalizedName(dungeonBlockName);
    	wroughtIronDoorBlock = new BlockWroughtIronDoor(wroughtIronDoorBlockID, Material.iron).setUnlocalizedName(TDItems.wroughtIronDoorName).setTextureName(ModRef.ModID + ":" + TDItems.wroughtIronDoorName);
    	steelDoorBlock = new BlockSteelDoor(steelDoorBlockID, Material.iron).setUnlocalizedName(TDItems.steelDoorName).setTextureName(ModRef.ModID + ":" + TDItems.steelDoorName);
    	
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.dungeonBlock, "pickaxe", 2); 	
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.wroughtIronDoorBlock, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.steelDoorBlock, "pickaxe", 3);
	}
	public static void RegisterBlocks()
	{
		GameRegistry.registerBlock(dungeonBlock, sionois.terraduellum.Items.ItemDungeon.class, dungeonBlockName);
        GameRegistry.registerBlock(wroughtIronDoorBlock,"WroughtIronDoor");
        GameRegistry.registerBlock(steelDoorBlock, "SteelDoor");
	}
	
}
