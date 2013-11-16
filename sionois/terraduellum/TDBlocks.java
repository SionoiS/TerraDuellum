package sionois.terraduellum;

import TFC.TFCBlocks;
import sionois.terraduellum.Blocks.BlockSteelDoor;
import sionois.terraduellum.Blocks.BlockBrassDungeon;
import sionois.terraduellum.Blocks.BlockSterlingSilverDungeon;
import sionois.terraduellum.Blocks.BlockRoseGoldDungeon;
import sionois.terraduellum.Blocks.BlockPlatinumDungeon;
import sionois.terraduellum.Blocks.BlockWroughtIronDoor;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;

public class TDBlocks
{
    public static Block brassDungeonBlock;
    public static Block sterlingSilverDungeonBlock;
    public static Block roseGoldDungeonBlock;
    public static Block platinumDungeonBlock;
    
    public static Block wroughtIronDoorBlock;
    public static Block steelDoorBlock;
    
    public static int brassDungeonBlockID;
    public static int sterlingSilverDungeonBlockID;
    public static int roseGoldDungeonBlockID;
    public static int platinumDungeonBlockID;
    
    public static int wroughtIronDoorBlockID;
    public static int steelDoorBlockID;
    
    public static String brassDungeonBlockName = "Brass Dungeon";
    public static String sterlingSilverDungeonBlockName = "Sterling Silver Dungeon";
    public static String roseGoldDungeonBlockName = "Rose Gold Dungeon";
    public static String platinumDungeonBlockName = "Platinum Dungeon";
    
	public static void LoadBlocks()
	{
		brassDungeonBlock = new BlockBrassDungeon(brassDungeonBlockID).setUnlocalizedName(brassDungeonBlockName).setTextureName(ModRef.ModID + ":" + brassDungeonBlockName);
		sterlingSilverDungeonBlock = new BlockSterlingSilverDungeon(sterlingSilverDungeonBlockID).setUnlocalizedName(sterlingSilverDungeonBlockName).setTextureName(ModRef.ModID + ":" + sterlingSilverDungeonBlockName);
		roseGoldDungeonBlock = new BlockRoseGoldDungeon(roseGoldDungeonBlockID).setUnlocalizedName(roseGoldDungeonBlockName).setTextureName(ModRef.ModID + ":" + roseGoldDungeonBlockName);
		platinumDungeonBlock = new BlockPlatinumDungeon(platinumDungeonBlockID).setUnlocalizedName(platinumDungeonBlockName).setTextureName(ModRef.ModID + ":" + platinumDungeonBlockName);

    	wroughtIronDoorBlock = new BlockWroughtIronDoor(wroughtIronDoorBlockID, Material.iron).setUnlocalizedName(TDItems.wroughtIronDoorName).setTextureName(ModRef.ModID + ":" + TDItems.wroughtIronDoorName);
    	steelDoorBlock = new BlockSteelDoor(steelDoorBlockID, Material.iron).setUnlocalizedName(TDItems.steelDoorName).setTextureName(ModRef.ModID + ":" + TDItems.steelDoorName);
    	
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.brassDungeonBlock, "pickaxe", 1);
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.sterlingSilverDungeonBlock, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.roseGoldDungeonBlock, "pickaxe", 3);
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.platinumDungeonBlock, "pickaxe", 4);
    	
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.wroughtIronDoorBlock, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(TDBlocks.steelDoorBlock, "pickaxe", 3);
	}
	public static void RegisterBlocks()
	{
        GameRegistry.registerBlock(brassDungeonBlock,"BrassDungeonBlock");
        GameRegistry.registerBlock(sterlingSilverDungeonBlock,"SterlingSilverDungeonBlock");
        GameRegistry.registerBlock(roseGoldDungeonBlock,"RoseGoldDungeonBlock");
        GameRegistry.registerBlock(platinumDungeonBlock,"PlatinumDungeonBlock");

        GameRegistry.registerBlock(wroughtIronDoorBlock,"WroughtIronDoor");
        GameRegistry.registerBlock(steelDoorBlock,"SteelDoor");
	}
	public static void NameBlocks()
	{
        LanguageRegistry.addName(brassDungeonBlock, brassDungeonBlockName);
        LanguageRegistry.addName(sterlingSilverDungeonBlock, sterlingSilverDungeonBlockName);
        LanguageRegistry.addName(roseGoldDungeonBlock, roseGoldDungeonBlockName);
        LanguageRegistry.addName(platinumDungeonBlock, platinumDungeonBlockName);
        
        LanguageRegistry.addName(wroughtIronDoorBlock, TDItems.wroughtIronDoorName);
        LanguageRegistry.addName(steelDoorBlock, TDItems.steelDoorName);
	}	
}
