package com.sionois.terraduellum.Entities;

import java.util.ArrayList;
import java.util.Iterator;

import com.sionois.terraduellum.Core.Status;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import TFC.API.ICausesDamage;
import TFC.API.Enums.EnumDamageType;

public abstract class EntityPlayerGhost extends EntityGolem implements IRangedAttackMob, ICausesDamage
{
	/**Attack Range*/
	public static final float arrowAttackRange = 25.0F;	
	/**Projectile Speed*/
	protected static final float force = 1.6F;
	/**Projectile Accuracy*/	
	protected static final float forceVariation = 4.0F;
	/**Mob speed*/
	protected static final double speed = 0.6D;
	/**Mob Health*/
	protected static final float health = 1000;
	
	public ArrayList friendlist = new ArrayList();
	private String creatorname;
    private EntityPlayer creator;
    private int homeX;
    private int homeY;
    private int homeZ;
        
    public EntityPlayerGhost(World par1World)
    {  	
        super(par1World);
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
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(this.speed);
		this.getAttributeMap().func_111150_b(SharedMonsterAttributes.attackDamage).setAttribute(10F);
		
		//System.out.println("applyEntityAttributes");
	}
    protected void addPlayerArmor()
    {
    	ItemStack[] itemstack = this.creator.getLastActiveItems();
    	ItemStack itemheld = this.creator.getHeldItem();
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
	public void setPlayer(EntityPlayer entityplayer)
	{
		this.creator = entityplayer;
		//System.out.println("setPlayer");
	}
	@Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
    {
    	par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

    	this.creatorname = this.creator.username;
    	this.setCustomNameTag(this.creatorname);
    	this.setAlwaysRenderNameTag(true);
    	
    	this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(this.creator.getHealth());
    	
    	this.addPlayerArmor();
    	
    	Status status = (Status) creator.getExtendedProperties(Status.EXT_PROP_NAME);
    	this.friendlist = status.friendlist;

    	this.homeX = MathHelper.floor_double(this.creator.posX);
    	this.homeY = MathHelper.floor_double(this.creator.posY);
    	this.homeZ = MathHelper.floor_double(this.creator.posZ);
    	this.setHomeArea(this.homeX, this.homeY, this.homeZ, 15);
    	
    	//System.out.println("onSpawnWithEgg");
    	//System.out.println("Home = " + this.getHomePosition().posX + " " + this.getHomePosition().posZ);
    	//System.out.println("Friend List = " + this.friendlist);
    	
    	return par1EntityLivingData;
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
        par1NBTTagCompound.setString("Player", this.creatorname);
        par1NBTTagCompound.setInteger("HomeXCoords", this.homeX);
        par1NBTTagCompound.setInteger("HomeYCoords", this.homeY);
        par1NBTTagCompound.setInteger("HomeZCoords", this.homeZ);
        
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
		//System.out.println("Home = " + this.getHomePosition().posX + " " + this.getHomePosition().posZ);
		//System.out.println("Friend List = " + this.friendlist);
    }
    @Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
		super.readEntityFromNBT(par1NBTTagCompound);
		
        if(par1NBTTagCompound.hasKey("Player"))
        {       	
        	this.creatorname = par1NBTTagCompound.getString("Player");
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
		this.setHomeArea(this.homeX, this.homeY, this.homeZ, 15);
		//System.out.println("readEntityFromNBT");
		//System.out.println("Home = " + this.getHomePosition().posX + " " + this.getHomePosition().posZ);
		//System.out.println("Friend List = " + this.friendlist);
    }
    @Override
    protected void dropEquipment(boolean par1, int par2){}
    public void remove(String playername)
    {
    	if(this.creatorname.equalsIgnoreCase(playername))
    	{
    		setDead();
    	}
	}
    public boolean allowLeashing()
    {
        return false;
    }
    
    protected boolean canDespawn()
    {
        return false;
    }
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving,float par2){}
	@Override
	public EnumDamageType GetDamageType()
	{
		return null;
	}
}
