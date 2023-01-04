package muriplz.chatnames.commands;

import muriplz.chatnames.Utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendCoords implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage( "You can't execute this command from console.");
            return false;
        }

        Player cordSender = (Player) sender;
        int x = cordSender.getLocation().getBlockX();
        int y = cordSender.getLocation().getBlockY();
        int z = cordSender.getLocation().getBlockZ();
        if (args.length == 1) {
            Player cordReciever = Bukkit.getPlayer(args[0]);
            if (cordReciever != null) {

                String cord = "(" + x + "," + y + "," + z + ")";

                cordReciever.sendMessage(ChatUtils.color("&6"+cordSender.getName()+"&f has sent you the current coords: &6"+cord));
                cordSender.sendMessage("&6"+cordReciever.getName()+" &fhas recieved your coords.");

            }else{
                cordSender.sendMessage("Player not found");
            }
        }else{
            cordSender.sendMessage("You have to enter a player's name");
        }
        return false;
    }
}
