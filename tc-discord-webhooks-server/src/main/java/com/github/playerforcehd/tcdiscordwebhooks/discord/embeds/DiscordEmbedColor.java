/*
 * MIT License
 *
 * Copyright (c) 2019 Pascal Zarrad
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
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
