/*
 * Copyright 2015, 2016 Ether.Camp Inc. (US)
 * This file is part of Ethereum Harmony.
 *
 * Ethereum Harmony is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Ethereum Harmony is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Ethereum Harmony.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ethercamp.harmony.config;

import org.ethereum.config.SystemProperties;

/**
 * Harmony properties are here
 * For EthereumJ properties check {@link org.ethereum.config.SystemProperties}
 */
public class HarmonyProperties {

    public static HarmonyProperties DEFAULT = new HarmonyProperties(SystemProperties.getDefault());

    private SystemProperties config;

    public HarmonyProperties(SystemProperties config) {
        this.config = config;
    }

    public boolean isWebEnabled() {
        return config.getConfig().getBoolean("modules.web.enabled");
    }

    public boolean isRpcEnabled() {
        return config.getConfig().getBoolean("modules.rpc.enabled");
    }

    public Integer rpcPort() {
        if (config.getConfig().hasPath("modules.rpc.listen.port") && isRpcEnabled()) {
            return config.getConfig().getInt("modules.rpc.listen.port");
        } else if (isRpcEnabled()) {
            return getServerPort();
        }

        return null;
    }

    public Integer webPort() {
        if (config.getConfig().hasPath("modules.web.listen.port") && isWebEnabled()) {
            return config.getConfig().getInt("modules.web.listen.port");
        } else if (isWebEnabled()) {
            return getServerPort();
        }

        return null;
    }

    private Integer getServerPort() {
        String serverPort = System.getProperty("server.port");
        if (serverPort != null) {
            return Integer.valueOf(serverPort);
        } else {
            return 8080;
        }
    }

    public boolean isContractStorageEnabled() {
        return config.getConfig().getBoolean("modules.contracts.enabled");
    }
}