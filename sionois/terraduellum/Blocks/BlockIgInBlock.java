package sionois.terraduellum.Blocks;

import sionois.terraduellum.ModRef;
import net.minecraft.client.renderer.texture.IconRegister;
import TFC.Blocks.Terrain.BlockIgInBrick;

public class BlockIgInBlock extends BlockIgInBrick{

	public BlockIgInBlock(int i)
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
