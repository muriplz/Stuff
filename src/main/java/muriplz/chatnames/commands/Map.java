package muriplz.chatnames.commands;

import muriplz.chatnames.Utils.ChatUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Map implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TextComponent message = new TextComponent(ChatUtils.color("Dynmap -> &9&nhttp://kryeit.com:8001/"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://kryeit.com:8001/"));
        Player player = (Player) sender;
        player.spigot().sendMessage(message);
        return false;
    }
}
