package commands;
import utils.Counter;

import java.awt.Color;
import java.util.EnumSet;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class CreateTicket {
	public void handle(SlashCommandInteractionEvent event) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.BLACK);
		embed.setTitle("Ticket Panel");
		
		
		event.replyEmbeds(embed.build()).addActionRow(
					Button.primary("1", "ticket generale")
				).queue();
		
	}
	
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
