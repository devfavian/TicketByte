package channelmanager;

import java.util.EnumSet;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import utils.Counter;

public class Channel {
	public void create(ButtonInteractionEvent event) {
		
		Category category = event.getGuild().getCategoryById("1405212724644020296");
		Guild server = event.getGuild();
		Long serverID = event.getGuild().getIdLong();
		Role everyone = event.getGuild().getPublicRole();
		Role rolename = event.getGuild().getRoleById("1170339078974222521");
		
		int numero = Counter.read(serverID);
		numero++;
		ChannelAction channel = server.createTextChannel("ticket-" + numero).setParent(category);
		Counter.write(serverID, numero);
		
		channel
		.addPermissionOverride(everyone, null, EnumSet.of(Permission.VIEW_CHANNEL))
		.addPermissionOverride(rolename, EnumSet.of(Permission.VIEW_CHANNEL), null)
		.queue();
		
		
		
	}
}
