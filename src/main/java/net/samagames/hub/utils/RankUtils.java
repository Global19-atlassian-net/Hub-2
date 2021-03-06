package net.samagames.hub.utils;

import net.md_5.bungee.api.ChatColor;
import net.samagames.api.SamaGamesAPI;
import net.samagames.api.permissions.IPermissionsEntity;

import java.util.UUID;

/*
 * This file is part of Hub.
 *
 * Hub is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Hub is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Hub.  If not, see <http://www.gnu.org/licenses/>.
 */
public class RankUtils
{
    public static String getFormattedRank(UUID uuid, boolean overrideNickname)
    {
        IPermissionsEntity permissionsEntity = SamaGamesAPI.get().getPermissionsManager().getPlayer(uuid);

        String prefix = overrideNickname ? permissionsEntity.getPrefix() : permissionsEntity.getDisplayPrefix();
        String display = (overrideNickname ? permissionsEntity.getTag() : permissionsEntity.getDisplayTag()).replace("[", "").replace("]", "");

        if (ChatColor.stripColor(display).isEmpty())
            display = ChatColor.GRAY + "Joueur";

        return prefix + display;
    }
}
