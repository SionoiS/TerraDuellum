package com.sionois.terraduellum.Commands;

import com.sionois.terraduellum.Core.Status;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.EnumGameType;

public class SetGhostOnOffCommand extends CommandBase
{
	@Override
	public String getCommandName() 
	{
		return "ghost";
	}
	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/ghost on/off";
	}
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
    	EntityPlayerMP entityplayermp = getCommandSenderAsPlayer(par1ICommandSender);
    	EnumGameType gametype = entityplayermp.theItemInWorldManager.getGameType();
    	if(gametype == EnumGameType.SURVIVAL)
    	{
        return true;
    	}
    	else return false;
    }
	@Override
	public void processCommand(ICommandSender sender, String[] astring) 
	{
		EntityPlayer entityplayer = getCommandSenderAsPlayer(sender);
		
		if (!entityplayer.worldObj.isRemote && astring.length == 1)
		{
			if(astring[0].equalsIgnoreCase("off"))
			{
				Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
				prop.isGhostOn = false;
				entityplayer.addChatMessage("§cGhost Off");
			}
			if(astring[0].equalsIgnoreCase("on"))
			{		
				Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
				prop.isGhostOn = true;
				entityplayer.addChatMessage("§aGhost On");
			}
		}
	}
}
