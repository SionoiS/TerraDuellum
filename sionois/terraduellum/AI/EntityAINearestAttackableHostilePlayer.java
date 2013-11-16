package sionois.terraduellum.AI;

import java.util.Collections;
import java.util.List;

import sionois.terraduellum.Entities.EntityPlayerGhost;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTargetSorter;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAINearestAttackableHostilePlayer extends EntityAITarget
{
    private final Class targetClass = EntityPlayer.class;
    private final int targetChance;

    /** Instance of EntityAINearestAttackableTargetSorter. */
    private final EntityAINearestAttackableTargetSorter theNearestAttackableTargetSorter;
    private final IEntitySelector field_82643_g;
    private EntityLivingBase targetEntity;
    
    public EntityAINearestAttackableHostilePlayer(EntityPlayerGhost par1EntityPlayerGhost, Class par2Class, int par3, boolean par4, boolean par5, IEntitySelector par6IEntitySelector)
    {
        super(par1EntityPlayerGhost, par4, par5);
        this.targetChance = par3;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTargetSorter(par1EntityPlayerGhost);
        this.setMutexBits(1);
        this.field_82643_g = new EntityAINearestAttackableHostilePlayerSelector(this, par6IEntitySelector);
    }

	@Override
    public boolean shouldExecute()
    {
        if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
        {
            return false;
        }
        else
        {
            List list = this.taskOwner.worldObj.selectEntitiesWithinAABB(this.targetClass, this.taskOwner.boundingBox.expand(20.0D, 20.0D, 20.0D), this.field_82643_g);
            Collections.sort(list, this.theNearestAttackableTargetSorter);

            if (list.isEmpty())
            {
                return false;
            }
            else
            	
            this.targetEntity = (EntityPlayer)list.get(0);
            String entityName = this.targetEntity.getEntityName().toLowerCase();
            EntityPlayerGhost entityplayerghost = (EntityPlayerGhost)this.taskOwner;
            
            if (entityplayerghost.friendlist != null && entityplayerghost.friendlist.contains(entityName))
            {
            	return false;
            }   
            else 
            	return true;
            }
        }
	
    public void startExecuting()
    {
        this.taskOwner.setAttackTarget(this.targetEntity);
        super.startExecuting();
    }
}
