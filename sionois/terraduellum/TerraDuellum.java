package sionois.terraduellum;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import sionois.terraduellum.Blocks.BlockWroughtIronDoor;
import sionois.terraduellum.Blocks.BlockSteelDoor;
import sionois.terraduellum.Blocks.BlockBrassDungeon;
import sionois.terraduellum.Blocks.BlockSterlingSilverDungeon;
import sionois.terraduellum.Blocks.BlockRoseGoldDungeon;
import sionois.terraduellum.Blocks.BlockPlatinumDungeon;
import sionois.terraduellum.Commands.FriendsListCommand;
import sionois.terraduellum.Commands.SetGhostOnOffCommand;
import sionois.terraduellum.Entities.EntityPlayerGhost;
import sionois.terraduellum.TileEntities.TileEntityDungeon;
import sionois.terraduellum.Handlers.StatusHandler;
import TFC.Reference;
import TFC.TerraFirmaCraft;
import TFC.API.Constant.TFCBlockID;
import TFC.API.Constant.TFCItemID;
import TFC.Core.Player.PlayerTracker;
import TFC.Handlers.AnvilCraftingHandler;
import TFC.Handlers.ChunkDataEventHandler;
import TFC.Handlers.ChunkEventHandler;
import TFC.Handlers.EnteringChunkHandler;
import TFC.Handlers.EntityDamageHandler;
import TFC.Handlers.EntityLivingHandler;
import TFC.Handlers.EntitySpawnHandler;
import TFC.Handlers.PlayerTossEventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=ModRef.ModID, name=ModRef.ModName, version=ModRef.ModVersion ,  dependencies = "after:Terrafirmacraft")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class TerraDuellum
{ 
    public static int baseDungeonRange;
    public static int baseBedCheckRange;
    
        @Instance("TerraDuellum")
        public static TerraDuellum instance;
        
        @SidedProxy(clientSide=ModRef.CLIENT_PROXY_CLASS, serverSide=ModRef.SERVER_PROXY_CLASS)
        public static CommonProxy proxy;
        
        @EventHandler     
        public void preInit(FMLPreInitializationEvent event) 
        {       	
        	loadConfig();
        	
        	proxy.registerTileEntities();
   
        }
        
        @EventHandler
        public void load(FMLInitializationEvent event) 
        {
        	GameRegistry.registerPlayerTracker(new TFC.Core.Player.PlayerTracker());
        	GameRegistry.registerPlayerTracker(new sionois.terraduellum.Tracker.GhostManager());
        	
            Recipes.addRecipes();
            
            proxy.registerRenderInformation();             	              	
        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) 
        {
        	MinecraftForge.EVENT_BUS.register(new StatusHandler());
        }
        @EventHandler
        public void serverLoad(FMLServerStartingEvent event)
        {
        	event.registerServerCommand(new SetGhostOnOffCommand());
        	event.registerServerCommand(new FriendsListCommand());
        }
        public void loadConfig() 
        {
    		Configuration config;
    		try
    		{
    			config = new Configuration(new File(TerraFirmaCraft.proxy.getMinecraftDir(), "/config/TerraDuellum.cfg"));
    			config.load();
    		} catch (Exception e) {
    			System.out.println(new StringBuilder().append("[TD] Error while trying to access configuration !").toString());
    			config = null;
    		}
    		System.out.println(new StringBuilder().append("[TD] Loading Config").toString());
    		
    		/**Blocks*/
    		
    		TDBlocks.brassDungeonBlockID = config.getBlock(TDBlocks.brassDungeonBlockName + " ID", 2120).getInt(2120);
    		TDBlocks.sterlingSilverDungeonBlockID = config.getBlock(TDBlocks.sterlingSilverDungeonBlockName + " ID", 2121).getInt(2121);
    		TDBlocks.roseGoldDungeonBlockID = config.getBlock(TDBlocks.roseGoldDungeonBlockName + " ID", 2122).getInt(2122);
    		TDBlocks.platinumDungeonBlockID = config.getBlock(TDBlocks.platinumDungeonBlockName + " ID", 2123).getInt(2123);

    		TDBlocks.wroughtIronDoorBlockID = config.getBlock(TDItems.wroughtIronDoorName + " Block ID", 2124).getInt(2124);
    		TDBlocks.steelDoorBlockID = config.getBlock(TDItems.steelDoorName + " Block ID", 2125).getInt(2125);
    		
    		TDBlocks.LoadBlocks();
    		TDBlocks.RegisterBlocks();
    		TDBlocks.NameBlocks();
    		
            /**Items*/ 
    		
    		TDItems.wroughtIronItemID = config.getItem(TDItems.wroughtIronDoorName + "ID", 30000).getInt(30000);
            TDItems.steelDoorItemID = config.getItem(TDItems.steelDoorName + "ID", 30001).getInt(30001);
            TDItems.LoadItems();
            TDItems.RegisterItems();
            TDItems.NameItems();
            
            /**Ranges*/
            
            this.baseDungeonRange = config.get("Dungeon", "Area Of Effect ?", 30).getInt(30);
            this.baseBedCheckRange = config.get("Bed Check", "Area Of Effect ?", 10).getInt(10);
            
            if (config != null)
            {
    			config.save();
            }
        }
}