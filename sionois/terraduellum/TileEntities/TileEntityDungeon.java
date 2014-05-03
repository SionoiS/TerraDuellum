package sionois.terraduellum.TileEntities;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import sionois.terraduellum.TerraDuellum;

public class TileEntityDungeon extends TileEntity
{
    private int xBed;
    private int yBed;
    private int zBed;
    private ChunkCoordinates xyzBed;
    private int tierModifier = 1;

    public void updateEntity()
    {      
    	if (this.worldObj.getTotalWorldTime() % 600L == 0L)
        {    
            this.addEffectsToPlayers(true);
        }
    	else if (this.worldObj.getTotalWorldTime() % 20L == 0L)
        {    
            this.addEffectsToPlayers(false);
        }
    }
    private void addEffectsToPlayers(boolean addMessage)
    {
        if (!this.worldObj.isRemote)
        {   
        	this.tierModifier = this.getBlockMetadata() + 1;
        	int prisonRange = (TerraDuellum.baseDungeonRange * this.tierModifier);
        	int bedcheckRange = (-((TerraDuellum.baseDungeonRange - TerraDuellum.baseBedCheckRange) * this.tierModifier));

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
                
                if(!entityplayer.isEntityAlive())
                {
                	if (entityplayer.getBedLocation(0) != null)
                	{
                		this.xyzBed = entityplayer.getBedLocation(0);
                		AxisAlignedBB bed = AxisAlignedBB.getAABBPool().getAABB(xyzBed.posX, xyzBed.posY, xyzBed.posZ, (xyzBed.posX + 1), (xyzBed.posY + 1), (xyzBed.posZ + 1));
                		bed.maxY = (double)this.worldObj.getHeight();

                		if (!bedrange.intersectsWith(bed))
                		{
                			entityplayer.setSpawnChunk(new ChunkCoordinates(this.xCoord, this.yCoord + 2, this.zCoord), true, 0);
                		}
                	}
                	else
                	{
                		entityplayer.setSpawnChunk(new ChunkCoordinates(this.xCoord, this.yCoord + 2, this.zCoord), true, 0);
                	}
                }
                else if(addMessage)
                {
                	if (entityplayer.getBedLocation(0) != null)
                	{
                		this.xyzBed = entityplayer.getBedLocation(0);
                		AxisAlignedBB bed = AxisAlignedBB.getAABBPool().getAABB(xyzBed.posX, xyzBed.posY, xyzBed.posZ, (xyzBed.posX + 1), (xyzBed.posY + 1), (xyzBed.posZ + 1));
                		bed.maxY = (double)this.worldObj.getHeight();

                		if (!bedrange.intersectsWith(bed))
                		{
                			entityplayer.addChatMessage("§cProtected zone ! If you die here you will be trapped !");
                		}
                	}
                	else
                	{
                		entityplayer.addChatMessage("§cProtected zone ! If you die here you will be trapped !");
                	}
                	
                }
                
            }
        }
    }
}
