package sionois.terraduellum.Blocks;

import sionois.terraduellum.ModRef;
import sionois.terraduellum.TDBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlatinumDungeon extends DungeonBasics
{
	public static final int tierModifier = 4;
	
    public BlockPlatinumDungeon(int id)
    {
        super(id);
    }
	@SideOnly(Side.CLIENT)
    public void registerIcon(IconRegister par1RegisterIcon)
    {
        this.blockIcon = par1RegisterIcon.registerIcon(ModRef.ModID + ":" + TDBlocks.platinumDungeonBlockName);
    }
}