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

package com.gtihub.playerforcehd.tcdiscordwebhooks.discord;

import com.github.playerforcehd.tcdiscordwebhooks.discord.embeds.DiscordEmbedColor;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

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
            assertEquals(decimalColorCode, 14361893, "Expected decimal color code to be 14361893 but got " + decimalColorCode + "!");
        } catch (NumberFormatException e) {
            fail("Failed to convert hexadecimal to decimal color code.", e);
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
            assertEquals(decimalColorCode, 14361893, "Expected decimal color code to be 14361893 but got " + decimalColorCode + "!");
        } catch (NumberFormatException e) {
            fail("Failed to convert hexadecimal to decimal color code.", e);
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
            fail("Entering an invalid hexadecimal string should result in a NumberFormatException!");
        } catch (Exception e) {
            if (!(e instanceof NumberFormatException)) {
                fail("The thrown exception was expected to be a NumberFormatException but got any other exception!", e);
            }
        }
    }
}
