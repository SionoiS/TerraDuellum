package com.sionois.terraduellum;

import net.minecraft.item.ItemStack;
import TFC.TFCBlocks;
import TFC.TFCItems;
import TFC.API.Constant.Global;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes 
{
    public static void addRecipes()
    {
    	/**Wall Blocks*/
		for(int j = 0; j < Global.STONE_IGIN.length; j++)
		{
			GameRegistry.addRecipe(new ItemStack(TDBlocks.stoneIgInBlock,5,j), new Object[] {"PXP","XPX","PXP",Character.valueOf('P'),new ItemStack(TFCBlocks.StoneIgIn,1,j),Character.valueOf('X'),new ItemStack(TFCItems.Mortar,1)});
		}
		for(int j = 0; j < Global.STONE_IGEX.length; j++)
		{
			GameRegistry.addRecipe(new ItemStack(TDBlocks.stoneIgExBlock,5,j), new Object[] {"PXP","XPX","PXP",Character.valueOf('P'),new ItemStack(TFCBlocks.StoneIgEx,1,j),Character.valueOf('X'),new ItemStack(TFCItems.Mortar,1)});
		}
		for(int j = 0; j < Global.STONE_SED.length; j++)
		{
			GameRegistry.addRecipe(new ItemStack(TDBlocks.stoneSedBlock,5,j), new Object[] {"PXP","XPX","PXP",Character.valueOf('P'),new ItemStack(TFCBlocks.StoneSed,1,j),Character.valueOf('X'),new ItemStack(TFCItems.Mortar,1)});
		}
		for(int j = 0; j < Global.STONE_MM.length; j++)
		{
			GameRegistry.addRecipe(new ItemStack(TDBlocks.stoneMMBlock,5,j), new Object[] {"PXP","XPX","PXP",Character.valueOf('P'),new ItemStack(TFCBlocks.StoneMM,1,j),Character.valueOf('X'),new ItemStack(TFCItems.Mortar,1)});
		}
		/**Brass*/
		GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.BrassIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.BrassIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.BrassIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.BrassIngot});
    	/**Sterling Silver*/
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.SterlingSilverIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.SterlingSilverIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.SterlingSilverIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.SterlingSilverIngot});
    	/**Rose Gold*/
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.RoseGoldIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.RoseGoldIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.RoseGoldIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.RoseGoldIngot});
    	/**Platinum*/
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.PlatinumIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.PlatinumIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.PlatinumIngot});
    	GameRegistry.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.PlatinumIngot});
    	/**Metal Doors*/
    	GameRegistry.addRecipe(new ItemStack(TDItems.doorWroughtIron), new Object[]{"II","II","II", 'I', TFCItems.WroughtIronIngot});
    	GameRegistry.addRecipe(new ItemStack(TDItems.doorSteel), new Object[]{"II","II","II", 'I', TFCItems.SteelIngot});
    }
}
