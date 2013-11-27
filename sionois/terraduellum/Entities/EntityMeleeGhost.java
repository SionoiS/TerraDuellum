package sionois.terraduellum.Entities;

import TFC.API.Enums.EnumDamageType;
import sionois.terraduellum.TDItems;
import sionois.terraduellum.AI.EntityAINearestAttackableHostilePlayer;
import sionois.terraduellum.AI.EntityAIOpenTFCDoor;
import sionois.terraduellum.Core.Status;
import sionois.terraduellum.Tracker.GhostManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMeleeGhost extends EntityPlayerGhost
{	    
    public EntityMeleeGhost(World par1World)
    {  	
        super(par1World);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, this.speed));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, this.speed, true));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, this.arrowAttackRange));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableHostilePlayer(this, true, true));
        
        //System.out.println("AI Melee Ghost");
    }
	@Override
    public boolean attackEntityAsMob(Entity par1Entity)
    {
        float f = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (par1Entity instanceof EntityLivingBase)
        {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)par1Entity);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)par1Entity);
        }

        boolean flag = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            if (i > 0)
            {
                par1Entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                par1Entity.setFire(j * 4);
            }

            if (par1Entity instanceof EntityLivingBase)
            {
                EnchantmentThorns.func_92096_a(this, (EntityLivingBase)par1Entity, this.rand);
            }
        }

        return flag;
    }
	@Override
	public EnumDamageType GetDamageType() 
	{	
		ItemStack itemstack = this.getHeldItem();
		
		if(itemstack != null)
		{
			if (TDItems.Maces.contains(itemstack.getItem()))
			{
				return EnumDamageType.CRUSHING;
			}
			else if (TDItems.Knives.contains(itemstack.getItem()))
			{
				return EnumDamageType.PIERCING;
			}
			else return EnumDamageType.SLASHING;
		}
		else return EnumDamageType.SLASHING;
	}
}
