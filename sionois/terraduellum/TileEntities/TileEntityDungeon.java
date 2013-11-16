package sionois.terraduellum.TileEntities;

import java.util.Iterator;
import java.util.List;

import sionois.terraduellum.TDBlocks;
import sionois.terraduellum.TerraDuellum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;

public class TileEntityDungeon extends TileEntity
{
    private int xBed;
    private int yBed;
    private int zBed;
    private ChunkCoordinates xyzBed;
    private int tierModifier = this.blockMetadata + 1;
       
    public void updateEntity()
    {      
        if (this.worldObj.getTotalWorldTime() % 20L == 0L)
        {       	
            this.addEffectsToPlayers();
        }
    }
    private void addEffectsToPlayers()
    {    	
        if (!this.worldObj.isRemote)
        {   
        	System.out.println("this tier ? "+ (this.tierModifier));
        	
        	int prisonRange = (TerraDuellum.baseDungeonRange * tierModifier);
        	int bedcheckRange = (-((TerraDuellum.baseDungeonRange - TerraDuellum.baseBedCheckRange) * tierModifier));
        	//System.out.println("Dungeon Range ? "+ prisonRange);
        	//System.out.println("Bed Range ? "+ bedcheckRange);
        	
            AxisAlignedBB prisonrange = AxisAlignedBB.getAABBPool().getAABB(this.xCoord, this.yCoord, this.zCoord, (this.xCoord + 1), (this.yCoord + 1), (this.zCoord + 1)).expand(prisonRange, prisonRange, prisonRange);
            prisonrange.maxY = (double)this.worldObj.getHeight();
            AxisAlignedBB bedrange = prisonrange.expand(bedcheckRange, bedcheckRange, bedcheckRange);
            bedrange.maxY = (double)this.worldObj.getHeight();
            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, prisonrange);
            Iterator iterator = list.iterator();
            EntityPlayer entityplayer;

            while (iterator.hasNext())
            {
                entityplayer = (EntityPlayer)iterator.next();
                //System.out.println("EntityPlayer is alive ? = "+ entityplayer.isEntityAlive());
                
                if(!entityplayer.isEntityAlive())
                {
                	if (entityplayer.getBedLocation(0) != null)
                	{
                		this.xyzBed = entityplayer.getBedLocation(0);
                		AxisAlignedBB bed = AxisAlignedBB.getAABBPool().getAABB(xyzBed.posX, xyzBed.posY, xyzBed.posZ, (xyzBed.posX + 1), (xyzBed.posY + 1), (xyzBed.posZ + 1));
                		bed.maxY = (double)this.worldObj.getHeight();

                		if (!bedrange.intersectsWith(bed))
                		{
                			//System.out.println("Spawn Point Set");
                			entityplayer.setSpawnChunk(new ChunkCoordinates(this.xCoord, this.yCoord + 2, this.zCoord), true, 0);
                		}
                	}
                	else
                	{
                		//System.out.println("Spawn Point Set");
                		entityplayer.setSpawnChunk(new ChunkCoordinates(this.xCoord, this.yCoord + 2, this.zCoord), true, 0);
                	}
                }
            }
        }
    }
}
