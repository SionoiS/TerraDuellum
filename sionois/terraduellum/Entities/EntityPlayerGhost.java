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
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPlayerGhost extends EntityMob implements IRangedAttackMob, ICausesDamage
{
	/**AttackRange don't seem to work for more that 16.0F*/
	private static final float arrowAttackRange = 20.0F;
	private static final float watchRange = 20.0F;
	
	/** Projectile Speed**/
	private static final float force = 1.6F;
	/**Projectile Accuracy**/	
	private static final float forceVariation = 4.0F;
	/**Mob speed**/
	private static final double speed = 0.55D;
	/**Mob Health**/
	private static final float maxHealth = 1000;
	
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 0.0D, 20, 60, this.arrowAttackRange);
    private EntityAIArrowAttack aiJavelinAttack = new EntityAIArrowAttack(this, 0.0D, 20, 120, this.arrowAttackRange);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, this.speed, false);
    
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
        //System.out.println("AI"); 	    	
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, this.watchRange));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableHostilePlayer(this, EntityPlayer.class, 0, true, false, null));
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
		//System.out.println("applyEntityAttributes");
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(this.maxHealth);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(10.0F);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(this.speed);
	}
    private void addPlayerArmor()
    {   
    	ItemStack itemstack0 = player.getCurrentItemOrArmor(0);
    	ItemStack itemstack1 = player.getCurrentItemOrArmor(1);
    	ItemStack itemstack2 = player.getCurrentItemOrArmor(2);
    	ItemStack itemstack3 = player.getCurrentItemOrArmor(3);
    	ItemStack itemstack4 = player.getCurrentItemOrArmor(4);
    	if(itemstack0 != null)
    	{
		this.setCurrentItemOrArmor(0, itemstack0);
    	}
		if(itemstack1 != null)
		{
        this.setCurrentItemOrArmor(1, itemstack1);
		}
		if(itemstack2 != null)
		{
        this.setCurrentItemOrArmor(2, itemstack2);
		}
		if(itemstack3 != null)
		{
        this.setCurrentItemOrArmor(3, itemstack3);
		}
		if(itemstack4 != null)
		{
        this.setCurrentItemOrArmor(4, itemstack4);
		}
    }
	public void setCombatTask()
	{
		//System.out.println("CombatAI");
		this.tasks.removeTask(this.aiAttackOnCollide);
		this.tasks.removeTask(this.aiArrowAttack);
		this.tasks.removeTask(this.aiJavelinAttack);
    	ItemStack itemstack = this.getHeldItem();
    	//System.out.println(itemstack);
		if (itemstack != null)
		{    
			if(itemstack.itemID == Item.bow.itemID)
			{
				//System.out.println("aiArrowAttack");
				this.tasks.addTask(2, this.aiArrowAttack);
			}
			if (itemstack.getItem() instanceof ItemJavelin)
			{
				//System.out.println("aiJavelinAttack");
				this.tasks.addTask(2, this.aiJavelinAttack);
			}
			else this.tasks.addTask(2, this.aiAttackOnCollide);
		}
        else this.tasks.addTask(2, this.aiAttackOnCollide);	
	}
	@Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
    {
    	par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
    	//System.out.println("onSpawnWithEgg");
    	
    	this.spawner = GhostManager.playername;
    	this.setCustomNameTag(this.spawner);
    	this.setAlwaysRenderNameTag(true);
    	//System.out.println("PlayerName = " + spawner);
    	
    	this.player = GhostManager.player;
    	
    	this.friendlist = GhostManager.friendlist;
    	//System.out.println("Ghost Friend List = " + this.friendlist);
    	
    	this.addPlayerArmor();
    	
   	    this.homeX = MathHelper.floor_double(this.player.posX);
    	this.homeY = MathHelper.floor_double(this.player.posY);
    	this.homeZ = MathHelper.floor_double(this.player.posZ);
    	this.setHomeArea(this.homeX, this.homeY, this.homeZ, 15);

    	//System.out.println("setHomeArea to" + (int)player.posX + (int)player.posY + (int)player.posZ);
    	
    	return par1EntityLivingData;
    }
	@Override
    protected void updateAITick()
    { 
    	++this.aiTime;
    	
    	if(this.aiTime == 20)
    	{
    		//System.out.println("AI Tick");
    		//System.out.println(this.homeX + this.homeY + this.homeZ);
    		this.setHomeArea(this.homeX, this.homeY, this.homeZ, 15);
    		this.tasks.addTask(1, new EntityAIMoveTowardsRestriction(this, this.speed + 0.1D));
    		this.setCombatTask();
    	}
    }
	@Override
    protected void onDeathUpdate()
    {
        ++this.deathTime;

        if (this.deathTime == 600)
        {   
        	//System.out.println("Death Update");
        	this.setHealth(this.getMaxHealth());
        	//System.out.println(this.getMaxHealth());
        	this.deathTime = 0;
        	
        	//System.out.println("setLocation Home");
        	this.setLocationAndAngles(this.homeX, this.homeY, this.homeZ, MathHelper.wrapAngleTo180_float(worldObj.rand.nextFloat() * 360.0F), 0.0F);
    	    this.rotationYawHead = this.rotationYaw;
    	    this.renderYawOffset = this.rotationYaw;
        }
    }
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {   
        super.writeEntityToNBT(par1NBTTagCompound);
        //System.out.println("writeEntityToNBT");
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
				//System.out.println(i);
				par1NBTTagCompound.setString("Friend" + i, (String) this.friendlist.get(i));				
				++i;
				iterator.next();
			}
			//System.out.println("Friend " + this.friendlist);
		}
    }
    @Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
		super.readEntityFromNBT(par1NBTTagCompound);
		//System.out.println("readEntityFromNBT");
		
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
			//System.out.println(i);
			this.friendlist.add(par1NBTTagCompound.getString("Friend" + i));
			++i;
		}
		//System.out.println("Friend List " + this.friendlist);
    }
    @Override
    protected void dropEquipment(boolean par1, int par2){}
    @Override
    protected boolean canDespawn()
    {
        return false;
    }
    public void remove()
    {
    	if(this.spawner.equalsIgnoreCase(GhostManager.playername))
    	{
    		//System.out.println("remove");
    		setDead();
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
