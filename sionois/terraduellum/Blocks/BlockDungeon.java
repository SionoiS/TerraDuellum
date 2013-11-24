package sionois.terraduellum.Blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sionois.terraduellum.ModRef;
import sionois.terraduellum.TDBlocks;
import sionois.terraduellum.TerraDuellum;
import sionois.terraduellum.TileEntities.TileEntityDungeon;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import TFC.Reference;
import TFC.Blocks.BlockTerra;

public class BlockDungeon extends BlockTerra implements ITileEntityProvider
{
	private boolean dungeonOverlap;
	public static final String[] dungeonNames = {"Brass","Sterling Silver", "Rose Gold", "Platinum"};
	protected Icon[] icons = new Icon[dungeonNames.length];
	
	public BlockDungeon(int i)
	{
		super(i);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
	@SideOnly(Side.CLIENT)
    @Override
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List list)
	{
		for(int i = 0; i < dungeonNames.length; i++)
		{
			list.add(new ItemStack(this,1,i));
		}		
	}
	@Override
	public int damageDropped(int i)
	{
		return i;
	}
	@Override
	public Icon getIcon(int i, int j) 
	{
		return icons[j];
	}
	@Override
	public void registerIcons(IconRegister iconRegisterer)
    {
		for(int i = 0; i < dungeonNames.length; i++)
		{
			icons[i] = iconRegisterer.registerIcon(ModRef.ModID + ":" + dungeonNames[i]+" Dungeon");
		}
    }
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {  	
    	int dungeonCounter = 0;
        int dungeonRange = (TerraDuellum.baseDungeonRange * 8);
        	
        for (int i = -(dungeonRange); i < (dungeonRange); i++)
        {
        	for (int j = -(dungeonRange); j < (dungeonRange); j++)
        	{
        		for (int k = -(dungeonRange); k < (dungeonRange); k++)
        		{
        			int blockid = par1World.getBlockId(par2+i, par3+k, par4+j);
                    
        			if(blockid == TDBlocks.dungeonBlockID)
        			{
        					dungeonCounter++;
        			}
        		}
        	}
        }
        if(dungeonCounter >= 2)
        {
        	this.dungeonOverlap = true;
        }
        else this.dungeonOverlap = false;
    }
	public TileEntity createNewTileEntity(World par1World)
    { 			
        if (this.dungeonOverlap)
        {  
        	return null;
        }       
        else return new TileEntityDungeon();      	
    }
}
