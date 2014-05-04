package com.sionois.terraduellum.Tracker;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import TFC.Items.Tools.ItemJavelin;

import com.sionois.terraduellum.Core.Status;
import com.sionois.terraduellum.Entities.EntityPlayerGhost;

public class GhostManager extends TFC.Core.Player.PlayerTracker
{
	public void onPlayerLogin(EntityPlayer playermp)
	{	
		if(!playermp.worldObj.isRemote)
		{
	    	Status status = (Status) playermp.getExtendedProperties(Status.EXT_PROP_NAME);
	    	status.isGhostOn = false;
			
	    	AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB((double)playermp.posX, (double)playermp.posY, (double)playermp.posZ, (double)(playermp.posX + 1), (double)(playermp.posY + 1), (double)(playermp.posZ + 1)).expand(30, 30, 30);
	    	axisalignedbb.maxY = (double)playermp.worldObj.getHeight();
	    	List list = playermp.worldObj.getEntitiesWithinAABB(EntityPlayerGhost.class, axisalignedbb);
	    	Iterator iterator = list.iterator();
	    	EntityPlayerGhost entityplayerghost;

	    	while (iterator.hasNext())
	    	{
	    		entityplayerghost = (EntityPlayerGhost)iterator.next();
	    		entityplayerghost.remove(playermp.username); 
	    	}
		}
	}
	public void onPlayerLogout(EntityPlayer playermp) 
	{		
		if(!playermp.worldObj.isRemote)
		{
			Status status = (Status) playermp.getExtendedProperties(Status.EXT_PROP_NAME);
			
	    	if (status.isGhostOn)
	    	{
	    		World world = playermp.worldObj;
   		
	    		ItemStack itemstack = playermp.getHeldItem();
	    		
	    		if(itemstack != null && (itemstack.getItem() instanceof ItemJavelin || itemstack.itemID == Item.bow.itemID))
	    		{
	    			Entity entity1 = EntityList.createEntityByName("RangedGhost",world);
	    			//System.out.println("Spawn Ranged Ghost");

	    			((EntityPlayerGhost) entity1).setPlayer(playermp);
  			
	    			EntityLiving entityliving1 = (EntityLiving)entity1;
	    			entity1.setLocationAndAngles(playermp.posX, playermp.posY, playermp.posZ, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
	    			entityliving1.rotationYawHead = entityliving1.rotationYaw;
	    			entityliving1.renderYawOffset = entityliving1.rotationYaw;
	    			
	    			world.spawnEntityInWorld(entityliving1);
	    			
	    			EntityLivingData entitylivingdata = null;
	    			entitylivingdata = entityliving1.onSpawnWithEgg(entitylivingdata);	    			
	    		}
	    		else
	    		{
	    			Entity entity2 = EntityList.createEntityByName("MeleeGhost",world);
	    			//System.out.println("Spawn Melee Ghost");
	    			
	    			((EntityPlayerGhost) entity2).setPlayer(playermp);
	    			
	    			EntityLiving entityliving2 = (EntityLiving)entity2;
	    			entity2.setLocationAndAngles(playermp.posX, playermp.posY, playermp.posZ, MathHelper.wrapAngleTo180_float(world.rand.nextFloat() * 360.0F), 0.0F);
	    			entityliving2.rotationYawHead = entityliving2.rotationYaw;
	    			entityliving2.renderYawOffset = entityliving2.rotationYaw;
	    			
	    			world.spawnEntityInWorld(entityliving2);
	    			
	    			EntityLivingData entitylivingdata = null;
	    			entitylivingdata = entityliving2.onSpawnWithEgg(entitylivingdata);
	    		}
	    	}
		}
	}
}
