package net.samagames.hub.games.sign;

import net.samagames.api.SamaGamesAPI;
import net.samagames.api.games.ServerStatus;
import net.samagames.api.games.Status;
import net.samagames.hub.Hub;
import net.samagames.hub.games.AbstractGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.LinkedHashMap;

public class GameSign
{
    private final Sign sign;
    private final AbstractGame game;
    private final String map;

    private LinkedHashMap<String, ServerStatus> lastDatas;

    public GameSign(AbstractGame game, String map, Sign sign)
    {
        this.sign = sign;
        this.game = game;
        this.map = map;

        this.lastDatas = new LinkedHashMap<>();

        this.sign.setMetadata("game", new FixedMetadataValue(Hub.getInstance(), game.getCodeName()));
        this.sign.setMetadata("map", new FixedMetadataValue(Hub.getInstance(), map));

        this.update(null);
    }

    public void update(ServerStatus data)
    {
        if(this.game.isMaintenance())
        {
            this.sign.setLine(0, "");
            this.sign.setLine(1, ChatColor.DARK_RED + "Jeu en");
            this.sign.setLine(2, ChatColor.DARK_RED + "maintenance !");
            this.sign.setLine(3, "");

            Bukkit.getScheduler().runTask(Hub.getInstance(), this.sign::update);
            return;
        }

        String players = "?";
        String maxPlayers = "?";
        String clickLine = ChatColor.RED + "× Attente ×";

        if(data != null)
        {
            if(!data.getStatus().equals(Status.WAITING_FOR_PLAYERS) && !data.getStatus().equals(Status.READY_TO_START))
                this.lastDatas.remove(data.getBungeeName());
            else
                this.lastDatas.put(data.getBungeeName(), data);

            players = String.valueOf(this.getOnlinePlayers());
            maxPlayers = String.valueOf(data.getMaxPlayers());
            clickLine = ChatColor.GREEN + "» Jouer «";
        }

        this.sign.setLine(0, "• " + this.game.getName() + " •");
        this.sign.setLine(1, ChatColor.GREEN + "" + ChatColor.BOLD + map);
        this.sign.setLine(2, players + "/" + maxPlayers + " joueurs");
        this.sign.setLine(3, clickLine);

        Bukkit.getScheduler().runTask(Hub.getInstance(), this.sign::update);
    }

    public void click(Player player)
    {
        if(this.lastDatas == null)
        {
            return;
        }
        else if(this.lastDatas.isEmpty())
        {
            player.sendMessage(ChatColor.RED + "Aucun serveur n'est prêt à vous reçevoir actuellement.");
            return;
        }

        ServerStatus firstServer = this.lastDatas.values().iterator().next();
        String[] serverNameParts = firstServer.getBungeeName().split("_");

        player.sendMessage(ChatColor.GREEN + "Vous avez été envoyé vers le serveur " + serverNameParts[0] + " " + serverNameParts[1] + " !");
        SamaGamesAPI.get().getProxyDataManager().getProxiedPlayer(player.getUniqueId()).connectGame(firstServer.getBungeeName());
    }

    public void developperClick(Player player)
    {
        player.sendMessage(ChatColor.GOLD + "----------------------------------------");
        player.sendMessage(ChatColor.GOLD + "Informations du panneau de jeu :");
        player.sendMessage(ChatColor.GOLD + "> " + ChatColor.AQUA + "Jeu : " + ChatColor.GREEN + this.game.getCodeName());
        player.sendMessage(ChatColor.GOLD + "> " + ChatColor.AQUA + "Map : " + ChatColor.GREEN + this.map);
        player.sendMessage(ChatColor.GOLD + "> " + ChatColor.AQUA + "Joueurs en ligne : " + ChatColor.GREEN + this.getOnlinePlayers());
        player.sendMessage(ChatColor.GOLD + "> " + ChatColor.AQUA + "Serveurs en ligne : " + ChatColor.GREEN + this.lastDatas.size());
        player.sendMessage(ChatColor.GOLD + "----------------------------------------");
    }

    public int getOnlinePlayers()
    {
        int temp = 0;

        for(ServerStatus status : this.lastDatas.values())
            temp += status.getPlayers();

        return temp;
    }

    public Sign getSign()
    {
        return this.sign;
    }
}