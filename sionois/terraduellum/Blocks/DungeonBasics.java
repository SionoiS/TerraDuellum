package sionois.terraduellum.Blocks;

import sionois.terraduellum.TDBlocks;
import sionois.terraduellum.TerraDuellum;
import sionois.terraduellum.TileEntities.TileEntityDungeon;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import TFC.Blocks.BlockTerra;

public class DungeonBasics extends BlockTerra implements ITileEntityProvider
{
	private int dungeonCounter = 0;
	
	protected DungeonBasics(int par1)
	{
		super(par1);
        this.setHardness(6.0F);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {  		
        	int dungeonRange = (TerraDuellum.baseDungeonRange * 8);
        	
        	for (int i = -(dungeonRange); i < (dungeonRange); i++)
        	{
        		for (int j = -(dungeonRange); j < (dungeonRange); j++)
        		{
        			for (int k = -(dungeonRange); k < (dungeonRange); k++)
        			{
        				int blockid = par1World.getBlockId(par2+i, par3+k, par4+j);
                    
        				if(blockid == TDBlocks.brassDungeonBlockID || blockid == TDBlocks.sterlingSilverDungeonBlockID || blockid == TDBlocks.roseGoldDungeonBlockID || blockid == TDBlocks.platinumDungeonBlockID)
        				{
        					dungeonCounter++;
        				}
        			}
        		}
        	}
    }
	public TileEntity createNewTileEntity(World par1World)
    { 		
        if (dungeonCounter >= 2)
        {  
        	 return null;
        }       
        else return new TileEntityDungeon();      	
    }
}
