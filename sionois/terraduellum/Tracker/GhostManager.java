package sionois.terraduellum.Tracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sionois.terraduellum.Core.Status;
import sionois.terraduellum.Entities.EntityPlayerGhost;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GhostManager extends TFC.Core.Player.PlayerTracker
{
	public static String playername;
	public static EntityPlayer player;
	public static ArrayList friendlist;
	
	public void onPlayerLogin(EntityPlayer playermp)
	{	
		if(!playermp.worldObj.isRemote)
		{
	    	player = playermp;
	    	playername = playermp.username;
	    	
	    	Status prop = (Status) playermp.getExtendedProperties(Status.EXT_PROP_NAME);
	    	prop.turnGhostOff(playermp);
	    	
			//System.out.println(this.playername + " Friend List = " + this.friendlist);
			
	    	AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB((double)playermp.posX, (double)playermp.posY, (double)playermp.posZ, (double)(playermp.posX + 1), (double)(playermp.posY + 1), (double)(playermp.posZ + 1)).expand(30, 30, 30);
	    	axisalignedbb.maxY = (double)playermp.worldObj.getHeight();
	    	List list = playermp.worldObj.getEntitiesWithinAABB(EntityPlayerGhost.class, axisalignedbb);
	    	Iterator iterator = list.iterator();
	    	EntityPlayerGhost entityplayerghost;

	    	while (iterator.hasNext())
	    	{
	    		entityplayerghost = (EntityPlayerGhost)iterator.next();
	    		entityplayerghost.remove(); 
	    	}
		}
	}
	public void onPlayerLogout(EntityPlayer playermp) 
	{		
		if(!playermp.worldObj.isRemote)
		{
			player = playermp;
			playername = playermp.username;
			
			Status prop = (Status) playermp.getExtendedProperties(Status.EXT_PROP_NAME);
			this.friendlist = prop.getFriendList(playermp);
			
			//System.out.println(this.playername + " Friend List = " + this.friendlist);
			//System.out.println("For player " + playermp.username + " is ghost on ? " + prop.isGhostOn(playermp));
			
	    	if (prop.isGhostOn(playermp))
	    	{
	    		World world = playermp.worldObj;
	    		Entity entity = EntityList.createEntityByName("PlayerGhost",world);
	    		EntityLiving entityliving = (EntityLiving)entity;
	    		EntityLivingData entitylivingdata = null;
	    		entity.setLocationAndAngles(playermp.posX, playermp.posY, playermp.posZ, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
		    	entityliving.rotationYawHead = entityliving.rotationYaw;
		    	entityliving.renderYawOffset = entityliving.rotationYaw;
	    		world.spawnEntityInWorld(entityliving);
	    		entitylivingdata = entityliving.onSpawnWithEgg(entitylivingdata);
	    	}
		}
	}
}
