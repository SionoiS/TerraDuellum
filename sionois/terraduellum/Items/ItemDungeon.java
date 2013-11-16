package sionois.terraduellum.Items;

import sionois.terraduellum.Blocks.BlockDungeon;
import net.minecraft.creativetab.CreativeTabs;
import TFC.API.Enums.EnumSize;
import TFC.API.Enums.EnumWeight;
import TFC.Items.ItemBlocks.ItemTerraBlock;

public class ItemDungeon extends ItemTerraBlock
{

	public ItemDungeon(int par1)
	{
		super(par1);
		MetaNames = BlockDungeon.dungeonNames;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	@Override
	public EnumSize getSize()
	{
		return EnumSize.HUGE;
	}
	@Override
	public EnumWeight getWeight()
	{
		return EnumWeight.HEAVY;
	}
}
