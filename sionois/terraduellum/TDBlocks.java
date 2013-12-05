package sionois.terraduellum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import sionois.terraduellum.Blocks.BlockDungeon;
import sionois.terraduellum.Blocks.BlockIgExBlock;
import sionois.terraduellum.Blocks.BlockIgInBlock;
import sionois.terraduellum.Blocks.BlockMMBlock;
import sionois.terraduellum.Blocks.BlockSedBlock;
import sionois.terraduellum.Blocks.BlockSteelDoor;
import sionois.terraduellum.Blocks.BlockWroughtIronDoor;
import cpw.mods.fml.common.registry.GameRegistry;

public class TDBlocks
{
	/**Blocks*/
	public static Block stoneIgInBlock;
	public static Block stoneIgExBlock;
	public static Block stoneSedBlock;
	public static Block stoneMMBlock;
	
	public static Block dungeonBlock; 
	
    public static Block wroughtIronDoorBlock;
    public static Block steelDoorBlock;
    /**IDs*/
	public static int stoneIgInBlockID;
	public static int stoneIgExBlockID;
	public static int stoneSedBlockID;
	public static int stoneMMBlockID;
    
    public static int dungeonBlockID;
    
    public static int wroughtIronDoorBlockID;
    public static int steelDoorBlockID;
    /**Names*/
    public static String dungeonBlockName = "Dungeon Block";
    
    public static String stoneIgInBlockName = "StoneIgInBlock";
    public static String stoneIgExBlockName = "StoneIgExBlock";
    public static String stoneSedBlockName = "StoneSedBlock";
    public static String stoneMMBlockName = "StoneMMBlock";
    
	public static void LoadBlocks()
	{
		stoneIgInBlock = new BlockIgInBlock(stoneIgInBlockID).setHardness(585F).setResistance(675F).setUnlocalizedName(stoneIgInBlockName);
		stoneIgExBlock = new BlockIgExBlock(stoneIgExBlockID).setHardness(585F).setResistance(675F).setUnlocalizedName(stoneIgExBlockName);
		stoneSedBlock = new BlockSedBlock(stoneSedBlockID).setHardness(450F).setResistance(675F).setUnlocalizedName(stoneSedBlockName);
		stoneMMBlock = new BlockMMBlock(stoneMMBlockID).setHardness(450F).setResistance(675F).setUnlocalizedName(stoneMMBlockName);		
				
		dungeonBlock = new BlockDungeon(dungeonBlockID).setHardness(15F).setResistance(20F).setUnlocalizedName(dungeonBlockName);
    	wroughtIronDoorBlock = new BlockWroughtIronDoor(wroughtIronDoorBlockID, Material.iron).setUnlocalizedName(TDItems.wroughtIronDoorName).setTextureName(ModRef.ModID + ":" + TDItems.wroughtIronDoorName);
    	steelDoorBlock = new BlockSteelDoor(steelDoorBlockID, Material.iron).setUnlocalizedName(TDItems.steelDoorName).setTextureName(ModRef.ModID + ":" + TDItems.steelDoorName);
    	
    	/**Minimum Tool Required*/
    	MinecraftForge.setBlockHarvestLevel(stoneIgInBlock, "pickaxe", 1);
    	MinecraftForge.setBlockHarvestLevel(stoneIgExBlock, "pickaxe", 1);
    	MinecraftForge.setBlockHarvestLevel(stoneSedBlock, "pickaxe", 1);
    	MinecraftForge.setBlockHarvestLevel(stoneMMBlock, "pickaxe", 1);
    	
    	MinecraftForge.setBlockHarvestLevel(dungeonBlock, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(wroughtIronDoorBlock, "pickaxe", 2);
    	MinecraftForge.setBlockHarvestLevel(steelDoorBlock, "pickaxe", 3);
	}
	public static void RegisterBlocks()
	{
		GameRegistry.registerBlock(stoneIgInBlock, TFC.Items.ItemBlocks.ItemIgIn.class, stoneIgInBlockName);
		GameRegistry.registerBlock(stoneIgExBlock, TFC.Items.ItemBlocks.ItemIgEx.class, stoneIgExBlockName);
		GameRegistry.registerBlock(stoneSedBlock, TFC.Items.ItemBlocks.ItemSed.class, stoneSedBlockName);
		GameRegistry.registerBlock(stoneMMBlock, TFC.Items.ItemBlocks.ItemMM.class, stoneMMBlockName);
		
		GameRegistry.registerBlock(dungeonBlock, sionois.terraduellum.Items.ItemDungeon.class, dungeonBlockName);
        GameRegistry.registerBlock(wroughtIronDoorBlock,"WroughtIronDoor");
        GameRegistry.registerBlock(steelDoorBlock, "SteelDoor");
	}
	
}
