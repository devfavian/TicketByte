package ticketbyte;
import commands.*;
import channelmanager.Channel;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{ 
	
	private final CreateTicket ticket = new CreateTicket();
	private final Channel channel = new Channel();
	
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
    	channel.create(event);
    }
}
