package co.vulpin.birthday.bot

import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.events.ReadyEvent
import net.dv8tion.jda.core.events.guild.GuildJoinEvent
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import org.discordbots.api.client.DiscordBotListAPI

class ServerCountUpdater extends ListenerAdapter {

    private DiscordBotListAPI api = null

    @Override
    void onReady(ReadyEvent event) {
        // I could use the elvis assignment operator here
        // but Intellij doesn't support it yet so it just cries that its invalid
        api = api ?: new DiscordBotListAPI.Builder()
            .botId(event.JDA.selfUser.id)
            .token(System.getenv("DBL_TOKEN"))
            .build()
    }

    @Override
    void onGuildJoin(GuildJoinEvent event) {
        postStats(event.JDA)
    }


    @Override
    void onGuildLeave(GuildLeaveEvent event) {
        postStats(event.JDA)
    }

    private void postStats(JDA jda) {
        api?.setStats(jda.guilds.size(), jda.shardInfo.shardId, jda.shardInfo.shardTotal)
    }

}