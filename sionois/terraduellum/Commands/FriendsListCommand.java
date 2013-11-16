package sionois.terraduellum.Commands;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.EnumGameType;
import sionois.terraduellum.Core.Status;
import sionois.terraduellum.Tracker.GhostManager;

public class FriendsListCommand extends CommandBase
{
	@Override
	public String getCommandName() 
	{
		return "friendlist";
	}
	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/friendlist add/remove/list";
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
		EntityPlayer entityplayer =  getCommandSenderAsPlayer(sender);
		
		if (astring.length == 2 && !entityplayer.worldObj.isRemote)
		{
			if(astring[0].equalsIgnoreCase("add") && astring[1] != null)
			{
				Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
				prop.friendlist.add(astring[1]);
				throw new PlayerNotFoundException("Player " + astring[1] + " is now friendly");
			}
			if(astring[0].equalsIgnoreCase("remove")  && astring[1] != null)
			{
				Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
				prop.friendlist.remove(astring[1]);
				throw new PlayerNotFoundException("Player " + astring[1] + " is now hostile");
			}
			else throw new PlayerNotFoundException("add/remove <PlayerName> or list");
		}
		if(astring.length == 1 && (astring[0].equalsIgnoreCase("list") & !entityplayer.worldObj.isRemote))
		{
			Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
			throw new PlayerNotFoundException("Friend List= " + prop.getFriendList(entityplayer));
		}		
		else throw new PlayerNotFoundException("add/remove <PlayerName> or list");
	}
}