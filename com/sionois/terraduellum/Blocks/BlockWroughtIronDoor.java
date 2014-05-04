package com.sionois.terraduellum.Blocks;

import java.util.Random;

import com.sionois.terraduellum.TDItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockWroughtIronDoor extends BlockDoor
{
	public BlockWroughtIronDoor(int par1, Material par2Material)
	{
		super(par1, par2Material);
	    this.setHardness(24.0F);
	}
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return (par1 & 8) != 0 ? 0 : TDItems.doorWroughtIron.itemID;
    }
    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return TDItems.doorWroughtIron.itemID;
    }
}
