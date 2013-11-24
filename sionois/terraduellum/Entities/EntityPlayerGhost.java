package sionois.terraduellum.Entities;

import java.util.ArrayList;
import java.util.Iterator;

import cpw.mods.fml.relauncher.SideOnly;
import sionois.terraduellum.AI.EntityAINearestAttackableHostilePlayer;
import sionois.terraduellum.Tracker.GhostManager;
import TFC.TFCItems;
import TFC.API.ICausesDamage;
import TFC.API.IProjectile;
import TFC.API.Enums.EnumDamageType;
import TFC.Entities.EntityJavelin;
import TFC.Entities.EntityProjectileTFC;
import TFC.Items.Tools.ItemCustomBow;
import TFC.Items.Tools.ItemCustomSword;
import TFC.Items.Tools.ItemJavelin;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPlayerGhost extends EntityGolem implements IRangedAttackMob, ICausesDamage
{
	/**AttackRange*/
	public static final float arrowAttackRange = 25.0F;	
	/**Projectile Speed*/
	private static final float force = 1.6F;
	/**Projectile Accuracy*/	
	private static final float forceVariation = 4.0F;
	/**Mob speed*/
	private static final double speed = 0.6D;
	/**Mob Health*/
	private static final float health = 1000;
	
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.0D, 20, 60, this.arrowAttackRange);
    private EntityAIArrowAttack aiJavelinAttack = new EntityAIArrowAttack(this, 0.0D, 20, 120, this.arrowAttackRange);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, this.speed, true);

    private String spawner;
    private EntityPlayer player;
    private int homeX;
    private int homeY;
    private int homeZ;
    private int aiTime = 0;
    public ArrayList friendlist = new ArrayList();
        
    public EntityPlayerGhost(World par1World)
    {  	
        super(par1World);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, this.arrowAttackRange));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableHostilePlayer(this, true, false));
        
        //System.out.println("AI");
    } 
    @Override
    public boolean isAIEnabled()
    {
        return true;
    }
    @Override
	protected void applyEntityAttributes()
	{	
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(this.health);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(this.arrowAttackRange);
		this.getAttributeMap().func_111150_b(SharedMonsterAttributes.attackDamage).setAttribute(10F);
		
		//System.out.println("applyEntityAttributes");
	}
    private void addPlayerArmor()
    {   
    	ItemStack[] itemstack = this.player.getLastActiveItems();
    	ItemStack itemheld = this.player.getHeldItem();
    	int i = 0;
    	
    	while(i <= 3)
    	{
    		if(itemstack[i] != null)
    		{
    			this.setCurrentItemOrArmor(i+1, itemstack[i]);
    		}
    		i++;
    	}
    	if(itemheld != null)
    	{
    		this.setCurrentItemOrArmor(0, itemheld);
    	}
    }
	public void setCombatTask()
	{
		this.tasks.removeTask(this.aiAttackOnCollide);
		this.tasks.removeTask(this.aiArrowAttack);
		this.tasks.removeTask(this.aiJavelinAttack);
    	ItemStack itemstack = this.getHeldItem();
    		
		if (itemstack != null)
		{    
			if(itemstack.itemID == Item.bow.itemID)
			{
				this.tasks.addTask(2, this.aiArrowAttack);
			}
			if (itemstack.getItem() instanceof ItemJavelin)
			{
				this.tasks.addTask(2, this.aiJavelinAttack);
			}
			else this.tasks.addTask(2, this.aiAttackOnCollide);
		}
        else this.tasks.addTask(2, this.aiAttackOnCollide);	
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
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
    {
    	par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
    	
    	this.spawner = GhostManager.player.username;
    	this.setCustomNameTag(this.spawner);
    	this.setAlwaysRenderNameTag(true);
    	
    	this.player = GhostManager.player;
    	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(this.player.getHealth());
    	this.addPlayerArmor();

    	this.friendlist = GhostManager.friendlist;
    	
    	this.homeX = MathHelper.floor_double(this.player.posX);
    	this.homeY = MathHelper.floor_double(this.player.posY);
    	this.homeZ = MathHelper.floor_double(this.player.posZ);
    	this.setHomeArea(this.homeX, this.homeY, this.homeZ, 15);
    	
    	//System.out.println("onSpawnWithEgg");
    	
    	return par1EntityLivingData;
    }
	@Override
    protected void updateAITick()
    {	
		if(this.aiTime <= 20)
		{
    	++this.aiTime;
		}
    	if(this.aiTime == 20)
    	{
    		this.setHomeArea(this.homeX, this.homeY, this.homeZ, 15);
    		this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, this.speed));
    		this.setCombatTask();
    		
    		//System.out.println("updateAITick");
    	}
    }
    public void onLivingUpdate()
    {
        this.updateArmSwingProgress();
        super.onLivingUpdate();
    }
	@Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime == 600)
        {
        	this.setHealth(this.getMaxHealth());
        	this.deathTime = 0;
        	
        	this.setLocationAndAngles(this.homeX, this.homeY, this.homeZ, MathHelper.wrapAngleTo180_float(worldObj.rand.nextFloat() * 360.0F), 0.0F);
    	    this.rotationYawHead = this.rotationYaw;
    	    this.renderYawOffset = this.rotationYaw;
        }
    }
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setString("Player", spawner);
        par1NBTTagCompound.setInteger("HomeXCoords", homeX);
        par1NBTTagCompound.setInteger("HomeYCoords", homeY);
        par1NBTTagCompound.setInteger("HomeZCoords", homeZ);
        
		if (this.friendlist != null && !this.friendlist.isEmpty())
		{			
			Iterator iterator = friendlist.iterator();
			int i = 0;
			while(iterator.hasNext())
			{
				par1NBTTagCompound.setString("Friend" + i, (String) this.friendlist.get(i));				
				++i;
				iterator.next();
			}
		}
		
		//System.out.println("writeEntityToNBT");
    }
    @Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
		super.readEntityFromNBT(par1NBTTagCompound);
		
        if(par1NBTTagCompound.hasKey("Player"))
        {       	
        	this.spawner = par1NBTTagCompound.getString("Player");
        }
        if(par1NBTTagCompound.hasKey("HomeXCoords"))
        {
        	this.homeX = par1NBTTagCompound.getInteger("HomeXCoords");
        }
        if(par1NBTTagCompound.hasKey("HomeYCoords"))
        {
        	this.homeY = par1NBTTagCompound.getInteger("HomeYCoords");
        }
        if(par1NBTTagCompound.hasKey("HomeZCoords"))
        {
        	this.homeZ = par1NBTTagCompound.getInteger("HomeZCoords");
        }
        
		int i = 0;
		while(par1NBTTagCompound.hasKey("Friend" + i) && !(par1NBTTagCompound.getString("Friend" + i).isEmpty()))
		{
			this.friendlist.add(par1NBTTagCompound.getString("Friend" + i));
			++i;
		}
		
		//System.out.println("readEntityFromNBT");
    }
    @Override
    protected void dropEquipment(boolean par1, int par2){}
    public void remove()
    {
    	if(this.spawner.equalsIgnoreCase(GhostManager.player.username))
    	{
    		setDead();
    	}
	}
    public boolean allowLeashing()
    {
        return false;
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
	public EnumDamageType GetDamageType() 
	{	
		ItemStack itemstack = this.getHeldItem();
		
		if(itemstack != null)
		{
			if (itemstack.getItem() instanceof IProjectile || itemstack.getItem() == ItemCustomBow.bow)
			{
				return EnumDamageType.PIERCING;
			}
			else if ((itemstack.getItem() == TFCItems.BismuthBronzeMace) || (itemstack.getItem() == TFCItems.BlackBronzeMace) || (itemstack.getItem() == TFCItems.BlackSteelMace) || (itemstack.getItem() == TFCItems.BlueSteelMace) || (itemstack.getItem() == TFCItems.BronzeMace) || (itemstack.getItem() == TFCItems.CopperMace) || (itemstack.getItem() == TFCItems.RedSteelMace) || (itemstack.getItem() == TFCItems.SteelMace) || (itemstack.getItem() == TFCItems.WroughtIronMace))
			{
				return EnumDamageType.CRUSHING;
			}
			else return EnumDamageType.SLASHING;
		}
		else return EnumDamageType.SLASHING;
	}
}
