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
    	case "createticketpanel" -> ticket.handle(event);
    	}
    }
    
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
    	
    	String buttonID = event.getComponentId();
    	System.out.println("Bottone Cliccato");
    	
    	if(Integer.parseInt(buttonID) <= 4) {	//4 is the number of option in the ticket panel
    		channel.create(event, buttonID);		
    		event.reply("Hai aperto un ticket!").setEphemeral(true).queue();
    	}
    	else	channel.interactions(event, buttonID);
    }
}
