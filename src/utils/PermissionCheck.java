package utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class PermissionCheck {
	
	public static boolean checkbutton(ButtonInteractionEvent event) {
		if(!event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setTitle("Error!");
			embed.setDescription("You don't have necessary permission!");
			event.replyEmbeds(embed.build()).setEphemeral(true).queue();
			return false;
		}
		return true;
	}
	
}
