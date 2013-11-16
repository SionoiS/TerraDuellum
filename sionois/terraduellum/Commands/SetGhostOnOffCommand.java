package sionois.terraduellum.Commands;

import java.util.List;

import sionois.terraduellum.Core.Status;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

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
		return "/ghost off/on";
	}
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
    	EntityPlayerMP entityplayermp = getCommandSenderAsPlayer(par1ICommandSender);
    	EnumGameType gametype = entityplayermp.theItemInWorldManager.getGameType();
    	//System.out.println(gametype);
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
		
		if (astring.length == 1 && !entityplayer.worldObj.isRemote)
		{
			if(astring[0].equalsIgnoreCase("off"))
			{
				Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
				prop.turnGhostOff(entityplayer);
				throw new PlayerNotFoundException("Ghost Off");
			}
			if(astring[0].equalsIgnoreCase("on"))
			{		
				Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
				prop.turnGhostOn(entityplayer);
				throw new PlayerNotFoundException("Ghost On");
			}
			else throw new PlayerNotFoundException("on/off");
		}
		else throw new PlayerNotFoundException("on/off");
	}
}