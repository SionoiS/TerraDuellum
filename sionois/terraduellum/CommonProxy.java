package sionois.terraduellum;

import java.util.HashMap;
import java.util.Map;

import TFC.TerraFirmaCraft;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import sionois.terraduellum.Core.Status;
import sionois.terraduellum.Entities.EntityMeleeGhost;
import sionois.terraduellum.Entities.EntityRangedGhost;
import sionois.terraduellum.TileEntities.TileEntityDungeon;

public class CommonProxy {

	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public void registerTileEntities() 
	{
		ModLoader.registerTileEntity(TileEntityDungeon.class, "Dungeon");
		
    	EntityRegistry.registerGlobalEntityID(EntityMeleeGhost.class, "MeleeGhost", ModLoader.getUniqueEntityId());
    	EntityRegistry.registerGlobalEntityID(EntityRangedGhost.class, "RangedGhost", ModLoader.getUniqueEntityId());
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
