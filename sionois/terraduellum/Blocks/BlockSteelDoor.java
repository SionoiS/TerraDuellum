package sionois.terraduellum.Blocks;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import sionois.terraduellum.TDItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSteelDoor extends BlockDoor
{
	public BlockSteelDoor(int par1, Material par2Material)
	{
		super(par1, par2Material);
	    this.setHardness(60.0F);
	}
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return (par1 & 8) != 0 ? 0 : TDItems.doorSteel.itemID;
    }
    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return TDItems.doorSteel.itemID;
    }
}
