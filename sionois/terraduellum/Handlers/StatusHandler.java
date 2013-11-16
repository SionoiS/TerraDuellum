package sionois.terraduellum.Handlers;

import sionois.terraduellum.TerraDuellum;
import sionois.terraduellum.Core.Status;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class StatusHandler {
	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(Status.EXT_PROP_NAME) == null)
			{
				Status.register((EntityPlayer) event.entity);
			}
	}
	@ForgeSubscribe
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			//System.out.println("onEntityjoinWorld");
			Status.loadProxyData((EntityPlayer) event.entity);
		}
	}
	@ForgeSubscribe
	public void onLivingDeathEvent(LivingDeathEvent event)
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			//System.out.println("onLivingDeathEvent");
			Status.saveProxyData((EntityPlayer) event.entity);
		}
	}
}
