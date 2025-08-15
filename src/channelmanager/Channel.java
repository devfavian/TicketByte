package channelmanager;
import utils.*;

import java.util.EnumSet;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;
import utils.Counter;

public class Channel {
	public void create(ButtonInteractionEvent event, String idbutton) {
		
		Category category = event.getGuild().getCategoryById("1405212724644020296");
		Guild server = event.getGuild();
		Long serverID = event.getGuild().getIdLong();
		Role everyone = event.getGuild().getPublicRole();
		Role rolename = event.getGuild().getRoleById("1170339078974222521");
		
		int numero = Counter.read(serverID);
		numero++;
		ChannelAction<TextChannel> channel = server.createTextChannel("ticket-" + numero).setParent(category);
		Counter.write(serverID, numero);
		
		
		channel
		.addPermissionOverride(everyone, null, EnumSet.of(Permission.VIEW_CHANNEL))
		.addPermissionOverride(rolename, EnumSet.of(Permission.VIEW_CHANNEL), null)
		.queue(((TextChannel created) -> {
			String id = created.getId();
			Channel.message(event, id, idbutton);
		}));
		
	}
	
	public static void message(ButtonInteractionEvent event, String idchannel, String idbutton) {		
		TextChannel channelID = event.getGuild().getTextChannelById(idchannel);
		
		String userMention = event.getUser().getAsMention();
	    String botIcon     = event.getJDA().getSelfUser().getEffectiveAvatarUrl();
	    String guildIcon   = event.getGuild().getIconUrl();
	    
	    String title;
	    String desc;
	    java.awt.Color color;
		
		switch(idbutton) {
			case "1":
	            title = "🛠️ Bug Report";
	            desc  = "Hi " + userMention + ", thanks for opening a bug report!\n"
	                  + "Describe the bug in your own words: what happened, when it happened, "
	                  + "and anything that might have caused it. The more context you share, "
	                  + "the easier it’ll be for us to investigate and fix.";
	            color = new java.awt.Color(0xE67E22);
	            break;
			
			case "2":
	            title = "💡 Feature Request";
	            desc  = "Hi " + userMention + "! Tell us about the feature or improvement you have in mind. "
	                  + "Explain how you imagine it working and why it would be useful. "
	                  + "Feel free to share any inspiration or examples—keep it casual!";
	            color = new java.awt.Color(0x2ECC71);
	            break;
			
			case "3":
	            title = "📚 General Help";
	            desc  = "Hi " + userMention + "! Tell us what you need help with—coding questions, script setup, "
	                  + "server access, or anything else. No strict format needed, just explain the situation.";
	            color = new java.awt.Color(0x5865F2);
	            break;
				
			case "4":
	            title = "💳 Donation / Purchase";
	            desc  = "Hi " + userMention + "! Let us know what you’d like to purchase or redeem, "
	                  + "or ask anything related to donations. Share any details you think matter, "
	                  + "and we’ll guide you through the next steps.";
	            color = new java.awt.Color(0xF1C40F);
	            break;
	            
	        default:
	            title = "🎫 Ticket";
	            desc  = "Tell us more about your request in your own words.";
	            color = new java.awt.Color(0x5865F2);
	            break;
		}
		
	    EmbedBuilder embed = new EmbedBuilder()
	            .setColor(color)
	            .setAuthor("TicketByte", null, botIcon)
	            .setTitle(title)
	            .setDescription(desc)
	            .setThumbnail(guildIcon)
	            .setFooter("TicketByte", botIcon)
	            .setTimestamp(java.time.Instant.now());
	    
	    
		
		channelID.sendMessageEmbeds(embed.build()).addActionRow(
					Button.primary("5", "✋ Claim"),
					Button.danger("6", "🔒 Close")
				).queue();
		
	}
	
	public void interactions(ButtonInteractionEvent event, String idbutton) {
		
		PermissionCheck.checkbutton(event);
		
		String mention = event.getUser().getAsMention();
	    String botIcon     = event.getJDA().getSelfUser().getEffectiveAvatarUrl();
		
	    EmbedBuilder embed = new EmbedBuilder()
	            .setColor(new java.awt.Color(0x5865F2))
	            .setAuthor("TicketByte", null, botIcon)
	            .setFooter("TicketByte", botIcon)
	            .setTimestamp(java.time.Instant.now());
	    
		switch(idbutton) { //CLAIM
        case "5": {
            event.deferEdit().queue();

            embed.setDescription("Ticket claimed by " + mention);
            event.getChannel().sendMessageEmbeds(embed.build()).queue();
            break;
        }

        case "6": { // DELETE
            event.reply("Channel will be deleted in 3 seconds…")
            .setEphemeral(true)
            .queue(hook -> {
                java.util.concurrent.Executors.newSingleThreadScheduledExecutor()
                    .schedule(() -> {
                        try {
                            event.getChannel()
                                 .asTextChannel()
                                 .delete()
                                 .queue();
                        } catch (Exception ignored) {}
                    }, 3, java.util.concurrent.TimeUnit.SECONDS);
            });
       break;
        }

        default:
            event.reply("Unknown action").setEphemeral(true).queue();
		}	    
	    
	}
}
