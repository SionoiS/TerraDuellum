package sionois.terraduellum.AI;

import java.util.Arrays;

import sionois.terraduellum.TDItems;
import TFC.TFCBlocks;
import TFC.API.Constant.Global;
import TFC.API.Constant.TFCBlockID;
import TFC.Blocks.Vanilla.BlockCustomDoor;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

public class EntityAITFCDoorInteract extends EntityAIBase
{
    protected EntityLiving theEntity;
    protected int entityPosX;
    protected int entityPosY;
    protected int entityPosZ;
    protected BlockCustomDoor targetDoor;

    /**
     * If is true then the Entity has stopped Door Interaction and compoleted the task.
     */
    boolean hasStoppedDoorInteraction;
    float entityPositionX;
    float entityPositionZ;

    public EntityAITFCDoorInteract(EntityLiving par1EntityLiving)
    {
        this.theEntity = par1EntityLiving;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!this.theEntity.isCollidedHorizontally)
        {
            return false;
        }
        else
        {
            PathNavigate pathnavigate = this.theEntity.getNavigator();
            PathEntity pathentity = pathnavigate.getPath();

            if (pathentity != null && !pathentity.isFinished() && pathnavigate.getCanBreakDoors())
            {
                for (int i = 0; i < Math.min(pathentity.getCurrentPathIndex() + 2, pathentity.getCurrentPathLength()); ++i)
                {
                    PathPoint pathpoint = pathentity.getPathPointFromIndex(i);
                    this.entityPosX = pathpoint.xCoord;
                    this.entityPosY = pathpoint.yCoord + 1;
                    this.entityPosZ = pathpoint.zCoord;

                    if (this.theEntity.getDistanceSq((double)this.entityPosX, this.theEntity.posY, (double)this.entityPosZ) <= 20.25D)
                    {
                        this.targetDoor = this.findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);

                        if (this.targetDoor != null)
                        {
                            return true;
                        }
                    }
                }

                this.entityPosX = MathHelper.floor_double(this.theEntity.posX);
                this.entityPosY = MathHelper.floor_double(this.theEntity.posY + 1.0D);
                this.entityPosZ = MathHelper.floor_double(this.theEntity.posZ);
                this.targetDoor = this.findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
                return this.targetDoor != null;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.hasStoppedDoorInteraction;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.hasStoppedDoorInteraction = false;
        this.entityPositionX = (float)((double)((float)this.entityPosX + 0.5F) - this.theEntity.posX);
        this.entityPositionZ = (float)((double)((float)this.entityPosZ + 0.5F) - this.theEntity.posZ);
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        float f = (float)((double)((float)this.entityPosX + 0.5F) - this.theEntity.posX);
        float f1 = (float)((double)((float)this.entityPosZ + 0.5F) - this.theEntity.posZ);
        float f2 = this.entityPositionX * f + this.entityPositionZ * f1;

        if (f2 < 0.0F)
        {
            this.hasStoppedDoorInteraction = true;
        }
    }

    /**
     * Determines if a door can be broken with AI.
     */
    private BlockCustomDoor findUsableDoor(int par1, int par2, int par3)
    {
    	int k = this.theEntity.worldObj.getBlockId(par1, par2, par3);
        if(TDItems.DoorsId.contains(k))
        {
        	System.out.println("Door");
        	return (BlockCustomDoor)TFCBlocks.Doors[k-2041];
        }
        else 
        	System.out.println("Not Door");
        	return null;
    }
}
