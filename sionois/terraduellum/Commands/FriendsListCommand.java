package sionois.terraduellum.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.EnumGameType;
import sionois.terraduellum.Core.Status;

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
		return "/friendlist add/remove <PlayerName> or list";
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
		EntityPlayer entityplayer =  getCommandSenderAsPlayer(sender);
		
		if(!entityplayer.worldObj.isRemote)
		{
			if (astring.length == 2)
			{
				if(astring[0].equalsIgnoreCase("add") && astring[1] != null)
				{
					Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
					prop.friendlist.add(astring[1]);
					entityplayer.addChatMessage("§aPlayer " + astring[1] + "§a is now friendly");
				}
				
				if(astring[0].equalsIgnoreCase("remove") && astring[1] != null)
				{
					Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
					prop.friendlist.remove(astring[1]);
					entityplayer.addChatMessage("§cPlayer " + astring[1] + "§c is now hostile");
				}
			}
			
			if(astring.length == 1 & astring[0].equalsIgnoreCase("list"))
			{
				Status prop = (Status) entityplayer.getExtendedProperties(Status.EXT_PROP_NAME);
				entityplayer.addChatMessage("§9Friend List = " + prop.friendlist);
			}
		}
	}
}