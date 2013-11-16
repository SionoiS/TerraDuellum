package sionois.terraduellum.AI;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;



public class EntityAINearestAttackableHostilePlayerSelector  implements IEntitySelector
{
    final IEntitySelector field_111103_c;

    final EntityAINearestAttackableHostilePlayer field_111102_d;

    EntityAINearestAttackableHostilePlayerSelector(EntityAINearestAttackableHostilePlayer entityAINearestAttackableHostilePlayer, IEntitySelector par2IEntitySelector)
    {
        this.field_111102_d = entityAINearestAttackableHostilePlayer;
        this.field_111103_c = par2IEntitySelector;
    }

    /**
     * Return whether the specified entity is applicable to this filter.
     */
    public boolean isEntityApplicable(Entity par1Entity)
    {
       if(par1Entity instanceof EntityPlayer)
       {
    	   return true;
       }
       else return false;
    }

}
