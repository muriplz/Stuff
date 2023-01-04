package muriplz.chatnames.commands;

import muriplz.chatnames.Utils.ChatUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rules implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TextComponent message = new TextComponent(ChatUtils.color("Rules -> &9&nhttps://forum.kryeit.com/t/kryeit-rules/22"));
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://forum.kryeit.com/t/kryeit-rules/22"));
        Player player = (Player) sender;
        player.spigot().sendMessage(message);
        return false;
    }
}
