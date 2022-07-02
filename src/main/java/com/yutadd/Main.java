package com.yutadd;

import java.awt.Color;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
	public static void main(String[] args) {
		String token = "token!";
		JDABuilder jdaBuilder = JDABuilder.createDefault(token);
		jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
		jdaBuilder.setActivity(Activity.playing("Potato"));
		jdaBuilder.addEventListeners(new ListenerAdapter() {
			@Override
			public void onGuildMemberJoin(GuildMemberJoinEvent event) {
				String user = event.getMember().getEffectiveName();
				EmbedBuilder embedBuilder = new EmbedBuilder();
				embedBuilder.setColor(Color.red);
				embedBuilder.setTitle("Welcome " + "\'" + user + "\'" + " to our Discord Channel called " + "\'" + event.getGuild().getName() + "\'");
				event.getGuild().getDefaultChannel().sendMessageEmbeds(embedBuilder.build()).queue();
			}

			// Guild is the Discord Server, when bot receives message from the discord 
			// server
			@Override
			public void onMessageReceived(MessageReceivedEvent event) {
				// if the user is not a bot reply with Hello. Preventing infinite loop.
				//				int count = event.getMessage().getContentRaw().replaceAll(" ", "").length();
				//				if (!event.getAuthor().isBot()) {
				//					event.getMessage().reply("Number of letters in your message: " + count).queue();
				//					event.getMessage().reply("Time is: " + java.time.LocalTime.now()).queue();

				// Shows the current date according to system time
				String date = event.getMessage().getContentRaw();
				if (date.equals("?date") && !event.getAuthor().isBot()) {
					event.getMessage().reply("The date is: " + java.time.LocalDate.now()).queue(); // !,?,&
				}
			}
		});
		try {
			jdaBuilder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}
