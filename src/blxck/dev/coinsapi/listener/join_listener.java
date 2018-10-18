package blxck.dev.coinsapi.listener;

import blxck.dev.coinsapi.CoinsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join_listener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!CoinsAPI.isRegistered(p))
        {
            CoinsAPI.register(p);
            CoinsAPI.addCoins(p, 1);
        }
    }
}
