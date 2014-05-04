package com.sionois.terraduellum;

import java.util.HashMap;
import java.util.Map;

import com.sionois.terraduellum.Entities.EntityMeleeGhost;
import com.sionois.terraduellum.Entities.EntityRangedGhost;
import com.sionois.terraduellum.TileEntities.TileEntityDungeon;

import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public void registerTileEntities() 
	{
		GameRegistry.registerTileEntity(TileEntityDungeon.class, "Dungeon");
		
    	EntityRegistry.registerGlobalEntityID(EntityMeleeGhost.class, "MeleeGhost", EntityRegistry.findGlobalUniqueEntityId());
    	EntityRegistry.registerGlobalEntityID(EntityRangedGhost.class, "RangedGhost", EntityRegistry.findGlobalUniqueEntityId());
	}
	
	public void registerRenderInformation() {}
	
	public static void storeEntityData(String name, NBTTagCompound compound)
	{
		extendedEntityData.put(name, compound);
	}
	
	public static NBTTagCompound getEntityData(String name)
	{
		return extendedEntityData.remove(name);
	}
}
