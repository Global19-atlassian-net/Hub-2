package net.samagames.hub.common.hydroconnect.packets.queues;

import net.samagames.hub.common.hydroconnect.connection.AbstractPacket;
import net.samagames.hub.common.hydroconnect.queue.QPlayer;

/**
 * This file is a part of the SamaGames Project CodeBase
 * This code is absolutely confidential.
 * Created by Geekpower14 on 24/08/2015.
 * (C) Copyright Elydra Network 2014 & 2015
 * All rights reserved.
 */
public class QueueInfosUpdatePacket extends AbstractPacket
{

    private Type type;
    private boolean success;
    private String errorMessage;

    private String message;

    private String game;
    private String map;

    private QPlayer player;

    public QueueInfosUpdatePacket()
    {
    }

    public QueueInfosUpdatePacket(QPlayer player, Type type, boolean success, String errorMessage)
    {
        this.player = player;
        this.type = type;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public QueueInfosUpdatePacket(QPlayer player, Type type, String game, String map)
    {
        this.player = player;
        this.type = type;
        this.game = game;
        this.map = map;
    }

    public Type getType()
    {
        return type;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public String getGame()
    {
        return game;
    }

    public String getMap()
    {
        return map;
    }

    public QPlayer getPlayer()
    {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Type
    {
        ADD, REMOVE, INFO
    }
}
