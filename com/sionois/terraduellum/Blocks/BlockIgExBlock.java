package com.sionois.terraduellum.Blocks;

import com.sionois.terraduellum.ModRef;

import net.minecraft.client.renderer.texture.IconRegister;
import TFC.Blocks.Terrain.BlockIgExBrick;

public class BlockIgExBlock extends BlockIgExBrick
{
	public BlockIgExBlock(int i)
	{
		super(i);
	}
	@Override
	public void registerIcons(IconRegister iconRegisterer)
	{
		for(int i = 0; i < names.length; i++)
		{
			icons[i] = iconRegisterer.registerIcon(ModRef.ModID + ":" + "wall/"+names[i]+" Block");
		}
	}
}
