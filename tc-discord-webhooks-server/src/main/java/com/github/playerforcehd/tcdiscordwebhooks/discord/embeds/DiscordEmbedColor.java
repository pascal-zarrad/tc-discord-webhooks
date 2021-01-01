/*
 * Copyright 2021 Pascal Zarrad
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.playerforcehd.tcdiscordwebhooks.discord.embeds;

/**
 * Provides the basic colors that can be used for embeds.
 * The values of the colors are their decimal value.
 * <p>
 * Also this class provides a method to get the decimal value of a
 *
 * @author Pascal Zarrad
 */
public class DiscordEmbedColor {

    /**
     * Red color
     */
    public static final int RED = 16711680;

    /**
     * Blue color
     */
    public static final int BLUE = 26367;

    /**
     * Green color
     */
    public static final int GREEN = 510208;

    /**
     * Yellow color
     */
    public static final int YELLOW = 15924992;

    /**
     * Orange color
     */
    public static final int ORANGE = 16746496;

    /**
     * Converts a hexadecimal color code to a decimal color code.
     * This method supports hexadecimal strings as parameter with and without a leading #.
     *
     * @param hexCode The hexadecimal color code to convert to a decimal value
     * @return The decimal value
     */
    public static int convertHexToDecColor(String hexCode) throws NumberFormatException {
        if (hexCode.startsWith("#")) {
            String pureHex = hexCode.substring(1);
            return Integer.parseInt(pureHex, 16);
        } else {
            return Integer.parseInt(hexCode, 16);
        }
    }
}
