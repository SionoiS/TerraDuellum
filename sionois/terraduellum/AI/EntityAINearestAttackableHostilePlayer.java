package sionois.terraduellum.AI;

import java.util.Collections;
import java.util.List;

import sionois.terraduellum.Entities.EntityPlayerGhost;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTargetSorter;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAINearestAttackableHostilePlayer extends EntityAITarget
{
    /** Instance of EntityAINearestAttackableTargetSorter. */
    private final EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;
    private EntityLivingBase targetEntity;
    
    public EntityAINearestAttackableHostilePlayer(EntityPlayerGhost par1EntityPlayerGhost, boolean par4, boolean par5)
    {
        super(par1EntityPlayerGhost, par4, par5);
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTargetSorter(par1EntityPlayerGhost);
        this.setMutexBits(1);
    }
    @Override
    public boolean shouldExecute()
    {
            List list = this.taskOwner.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.taskOwner.boundingBox.expand(EntityPlayerGhost.arrowAttackRange, EntityPlayerGhost.arrowAttackRange, EntityPlayerGhost.arrowAttackRange));
            Collections.sort(list, this.theNearestAttackableTargetSorter);

            if (list.isEmpty())
            {
                return false;
            }
            else
                    
            this.targetEntity = (EntityPlayer)list.get(0);
            String entityName = this.targetEntity.getEntityName();
            EntityPlayerGhost entityplayerghost = (EntityPlayerGhost)this.taskOwner;
            
            if (entityplayerghost.friendlist != null && entityplayerghost.friendlist.contains(entityName))
            {
                    return false;
            }   
            else 
                    return true;
    }
   
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}