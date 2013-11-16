package sionois.terraduellum;


import sionois.terraduellum.Entities.EntityPlayerGhost;
import sionois.terraduellum.Render.RenderPlayerGhost;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
    public void registerRenderInformation() 
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityPlayerGhost.class, new RenderPlayerGhost());
    }
}
