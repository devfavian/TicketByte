package ticketbyte;
import commands.*;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{ 
	
	private final CreateTicket ticket = new CreateTicket();
	
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    	
    	switch (event.getName()) {
    	case "command" -> ticket.handle(event);
    	}
    }
    
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
    	
    	System.out.println("Bottone Cliccato");
    	
    	event.reply("Hai aperto un ticket!").setEphemeral(true).queue();
    	ticket.create(event);
    }
}
