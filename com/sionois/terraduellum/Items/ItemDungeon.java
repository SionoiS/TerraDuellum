package com.sionois.terraduellum.Items;

import com.sionois.terraduellum.Blocks.BlockDungeon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
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
	public EnumSize getSize(ItemStack is)
	{
		return EnumSize.HUGE;
	}
	@Override
	public EnumWeight getWeight(ItemStack is)
	{
		return EnumWeight.HEAVY;
	}
}
