package sionois.terraduellum.Entities;

import sionois.terraduellum.AI.EntityAINearestAttackableHostilePlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import TFC.API.IProjectile;
import TFC.API.Enums.EnumDamageType;
import TFC.Entities.EntityJavelin;
import TFC.Entities.EntityProjectileTFC;
import TFC.Items.Tools.ItemJavelin;

public class EntityRangedGhost extends EntityPlayerGhost
{
	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.0D, 20, 60, this.arrowAttackRange);
	private EntityAIArrowAttack aiJavelinAttack = new EntityAIArrowAttack(this, 0.0D, 20, 120, this.arrowAttackRange);
	
    public EntityRangedGhost(World par1World)
    {  	
        super(par1World);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, this.speed));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, this.arrowAttackRange));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableHostilePlayer(this, true, true));
        
        if (par1World != null && !par1World.isRemote)
        {
            this.setCombatTask();
        }
        
        System.out.println("AI Ranged Ghost");
    }
    /**
     * sets this entity's combat AI.
     */
    protected void setCombatTask()
    {
    	System.out.println("setCombatTask");
    	
    	this.tasks.removeTask(this.aiJavelinAttack);
        this.tasks.removeTask(this.aiArrowAttack);
        
        ItemStack itemstack = this.getHeldItem();

        if(itemstack != null)
        {
        	if (itemstack.getItem() instanceof ItemJavelin)
        	{
        		System.out.println("aiJavelinAttack");
        		this.tasks.addTask(2, this.aiJavelinAttack);
        	}
        	else
        	{
        		System.out.println("aiArrowAttack");
        		this.tasks.addTask(2, this.aiArrowAttack);
        	}  
        }
    }
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving,float par2) 
	{
		EntityProjectileTFC projectile = null;
		ItemStack itemstack = this.getHeldItem();
		
		if(itemstack!= null)
		{
			if(itemstack.getItem() instanceof IProjectile)
			{
				projectile = new EntityJavelin(this.worldObj, this, par1EntityLiving, this.force - 0.1F, this.forceVariation, itemstack.getItem().itemID);
				double dam = ((IProjectile)itemstack.getItem()).getRangedDamage();
				projectile.setDamage(dam);
			}
			else if (itemstack.itemID == Item.bow.itemID)
			{
				projectile = new EntityProjectileTFC(this.worldObj, this, par1EntityLiving, this.force, this.forceVariation - 2.0F, Item.arrow.itemID);
				projectile.setDamage(65.0);
			}
		}
	
		if(projectile != null)
		{
			this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.spawnEntityInWorld(projectile);
		}
	}
    @Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.readEntityFromNBT(par1NBTTagCompound);
    	this.setCombatTask();
    }
	@Override
	public EnumDamageType GetDamageType() 
	{	
		return EnumDamageType.PIERCING;
	}
}
