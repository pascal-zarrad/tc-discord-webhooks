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

package com.gtihub.playerforcehd.tcdiscordwebhooks.discord;

import com.github.playerforcehd.tcdiscordwebhooks.discord.embeds.DiscordEmbedColor;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the {@link DiscordEmbedColor#convertHexToDecColor(String)} method which
 * is used to convert hexadecimal color codes into their decimal equivalent.
 *
 * @author Pascal Zarrad
 */
public class DiscordEmbedColorTest {

    /**
     * The color code used for the check
     */
    private final String hexColor = "db2525";

    /**
     * Test if the conversion of a hexadecimal number to a decimal number does work properly.
     */
    @Test
    public void testConversionOfHexToDecColor() {
        try {
            int decimalColorCode = DiscordEmbedColor.convertHexToDecColor(this.hexColor);
            Assert.assertEquals(decimalColorCode, 14361893, "Expected decimal color code to be 14361893 but got " + decimalColorCode + "!");
        } catch (NumberFormatException e) {
            Assert.fail("Failed to convert hexadecimal to decimal color code.", e);
        }
    }

    /**
     * Test if the conversion of a hexadecimal number with a leading diamond
     * to a decimal number does work properly.
     */
    @Test
    public void testConversionOfHexToDecColorWithLeadingDiamond() {
        try {
            String hexColorWithDiamond = "#" + this.hexColor;
            int decimalColorCode = DiscordEmbedColor.convertHexToDecColor(hexColorWithDiamond);
            Assert.assertEquals(decimalColorCode, 14361893, "Expected decimal color code to be 14361893 but got " + decimalColorCode + "!");
        } catch (NumberFormatException e) {
            Assert.fail("Failed to convert hexadecimal to decimal color code.", e);
        }
    }

    /**
     * Tests if entering an invalid hexadecimal string ends up in a {@link NumberFormatException}
     */
    @Test
    public void testConversionFailsWhenEnteringInvalidHexString() {
        String invalidHexadecimalString = "a24fZ"; // Z is not a hexadecimal digit
        try {
            DiscordEmbedColor.convertHexToDecColor(invalidHexadecimalString);
            Assert.fail("Entering an invalid hexadecimal string should result in a NumberFormatException!");
        } catch (Exception e) {
            if (!(e instanceof NumberFormatException)) {
                Assert.fail("The thrown exception was expected to be a NumberFormatException but got any other exception!", e);
            }
        }
    }
}
