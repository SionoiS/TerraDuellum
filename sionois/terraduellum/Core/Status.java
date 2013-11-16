package sionois.terraduellum.Core;

import java.util.ArrayList;
import java.util.Iterator;

import sionois.terraduellum.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class Status implements IExtendedEntityProperties
{
	public final static String EXT_PROP_NAME = "Status";
	private final EntityPlayer player;
	public int ghoststatus;
	public ArrayList friendlist = new ArrayList();
	
	public Status(EntityPlayer player)
	{
		this.player = player;
		this.ghoststatus = 0;
	}
	private static String getSaveKey(EntityPlayer player)
	{
		return player.username + ":" + EXT_PROP_NAME;
	}
	public static Status get(EntityPlayer player)
	{
		return (Status) player.getExtendedProperties(EXT_PROP_NAME);
	}
	public static void register(EntityPlayer player)
	{
		player.registerExtendedProperties(Status.EXT_PROP_NAME, new Status(player));
	}
	public static void saveProxyData(EntityPlayer player)
	{
		Status playerData = Status.get(player);
		NBTTagCompound savedData = new NBTTagCompound();
		playerData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player) + Status.EXT_PROP_NAME, savedData);
	}
	public static void loadProxyData(EntityPlayer player) 
	{
		//System.out.println("loadProxyData");
		Status playerData = Status.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player) + Status.EXT_PROP_NAME);
		//System.out.println(savedData);
		if(savedData != null) 
		{
			playerData.loadNBTData(savedData);
		}
	}
	@Override
	public void init(Entity entity, World world){}
	@Override
	public void loadNBTData(NBTTagCompound par1NBTTagCompound)
	{
			//System.out.println("Status read From NBT");
			
			NBTTagCompound status = par1NBTTagCompound.getCompoundTag(EXT_PROP_NAME);
			this.ghoststatus = status.getInteger("GhostStatus");
			//System.out.println("ghotstatus " + this.ghoststatus);
			
			int i = 0;
			while(!(status.getString("Friend" + i).isEmpty()))
			{
				//System.out.println(i);
				this.friendlist.add(status.getString("Friend" + i));
				++i;
			}
			//System.out.println("Friend List " + this.friendlist);
	}
	@Override
	public void saveNBTData(NBTTagCompound par1NBTTagCompound)
	{
		//System.out.println("Status write To NBT");
		
		NBTTagCompound status = new NBTTagCompound();
				
		//System.out.println("ghotstatus " + this.ghoststatus);
		status.setInteger("GhostStatus", this.ghoststatus);
		
		if (this.friendlist != null && !this.friendlist.isEmpty())
		{
			Iterator iterator = this.friendlist.iterator();
			int i = 0;
			while(iterator.hasNext())
			{
				//System.out.println(i);				
				status.setString("Friend" + i, (String) this.friendlist.get(i));				
				++i;
				iterator.next();
			}
			
			//System.out.println("Friend List " + this.friendlist);			
			par1NBTTagCompound.setCompoundTag(EXT_PROP_NAME, status);				
		}				
	}
	public void turnGhostOn(EntityPlayer player)
	{
		this.ghoststatus = 1;
		//System.out.println("ghotstatus of " + player.username +" is " + this.ghoststatus);
	}
	public void turnGhostOff(EntityPlayer player)
	{
		this.ghoststatus = 0;
		//System.out.println("ghotstatus of " + player.username +" is " + this.ghoststatus);
	}
	public boolean isGhostOn(EntityPlayer player)
	{
		if(this.ghoststatus == 1)
		{
			return true;
		}
		else 
			return false;
	}
	public ArrayList getFriendList(EntityPlayer player)
	{
		return this.friendlist;
	}
}

