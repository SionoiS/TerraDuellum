package sionois.terraduellum;

import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import TFC.TFCBlocks;
import TFC.TFCItems;

public class Recipes 
{
    public static void addRecipes()
    {
    	/**Brass*/
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.BrassIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.BrassIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.BrassIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 0), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.BrassIngot});
    	/**Sterling Silver*/
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.SterlingSilverIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.SterlingSilverIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.SterlingSilverIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 1), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.SterlingSilverIngot});
    	/**Rose Gold*/
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.RoseGoldIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.RoseGoldIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.RoseGoldIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 2), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.RoseGoldIngot});
    	/**Platinum*/
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneMMSmooth, 'I', TFCItems.PlatinumIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneSedSmooth, 'I', TFCItems.PlatinumIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgInSmooth, 'I', TFCItems.PlatinumIngot});
    	ModLoader.addRecipe(new ItemStack(TDBlocks.dungeonBlock, 1, 3), new Object[]{"SIS","ISI","SIS",'S', TFCBlocks.StoneIgExSmooth, 'I', TFCItems.PlatinumIngot});
    	/**Metal Doors*/
    	ModLoader.addRecipe(new ItemStack(TDItems.doorWroughtIron), new Object[]{"II","II","II", 'I', TFCItems.WroughtIronIngot});
    	ModLoader.addRecipe(new ItemStack(TDItems.doorSteel), new Object[]{"II","II","II", 'I', TFCItems.SteelIngot});
    }
}
