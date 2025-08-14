package commands;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class CreateTicket {
	public void handle(SlashCommandInteractionEvent event) {
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.BLACK);
		embed.setAuthor("🎫 TicketByte • TicketPanel", null, null);
		embed.setColor(new java.awt.Color(0x5865F2));
		embed.setTimestamp(java.time.Instant.now());
		embed.setFooter("TicketByte");
		embed.setTitle("📋 Choose a category:");
		embed.setDescription("Use the buttons below to open a ticket in the right category.");
		embed.addField("\u200E", "", true);
		embed.addField("**🛠️ | BUG REPORT**", "Found a bug or glitch in our code, scripts, or systems? Open a ticket here to report it so our team can investigate and fix it.\n", false);
		embed.addBlankField(true);
		embed.addField("**💡 | FEATURE REQUEST**", "Have an idea for a new feature, improvement, or tool? Submit your suggestion here so we can review and consider it for future updates.\n", false);
		embed.addBlankField(true);
		embed.addField("**📚 | GENERAL HELP**", "Need general assistance with coding, setting up scripts, or understanding programming concepts? Open a ticket and our support team will help you out.\n", false);
		embed.addBlankField(true);
		embed.addField("**💳 | DONATION / PURCHASE**", "For all donation-related topics, including redeeming rewards or asking about available purchases such as premium scripts, tools, or resources.\n", false);
		embed.addField("\u200E", "", true);
		
		event.replyEmbeds(embed.build()).addActionRow(
					Button.primary("1", "BUG REPORT").withEmoji(Emoji.fromUnicode("🛠️")),
					Button.danger("2", "FEATURE REQUEST").withEmoji(Emoji.fromUnicode("💡")),
					Button.secondary("3", "GENERAL HELP").withEmoji(Emoji.fromUnicode("📚")),
					Button.success("4", "DONATION / PURCHASE").withEmoji(Emoji.fromUnicode("💳"))
				).queue();
		
	}
}
