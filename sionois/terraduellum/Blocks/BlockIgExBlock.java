package sionois.terraduellum.Blocks;

import net.minecraft.client.renderer.texture.IconRegister;
import sionois.terraduellum.ModRef;
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
