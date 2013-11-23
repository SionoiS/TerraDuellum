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
		Status playerData = Status.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player) + Status.EXT_PROP_NAME);
		
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
			NBTTagCompound status = par1NBTTagCompound.getCompoundTag(EXT_PROP_NAME);
			this.ghoststatus = status.getInteger("GhostStatus");
			
			int i = 0;
			while(!(status.getString("Friend" + i).isEmpty()))
			{
				this.friendlist.add(status.getString("Friend" + i));
				++i;
			}
	}
	@Override
	public void saveNBTData(NBTTagCompound par1NBTTagCompound)
	{
		NBTTagCompound status = new NBTTagCompound();
		status.setInteger("GhostStatus", this.ghoststatus);
		
		if (this.friendlist != null && !this.friendlist.isEmpty())
		{
			Iterator iterator = this.friendlist.iterator();
			int i = 0;
			while(iterator.hasNext())
			{				
				status.setString("Friend" + i, (String) this.friendlist.get(i));				
				++i;
				iterator.next();
			}
			
			par1NBTTagCompound.setCompoundTag(EXT_PROP_NAME, status);				
		}				
	}
	public void turnGhostOn(EntityPlayer player)
	{
		this.ghoststatus = 1;
	}
	public void turnGhostOff(EntityPlayer player)
	{
		this.ghoststatus = 0;
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

