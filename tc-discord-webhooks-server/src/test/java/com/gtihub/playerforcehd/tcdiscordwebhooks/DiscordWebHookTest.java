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

package com.gtihub.playerforcehd.tcdiscordwebhooks;

import com.github.playerforcehd.tcdiscordwebhooks.discord.DiscordWebHookPayload;
import com.github.playerforcehd.tcdiscordwebhooks.discord.DiscordWebHookProcessor;
import com.github.playerforcehd.tcdiscordwebhooks.discord.embeds.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.testng.Assert.*;

/**
 * Tests to check if the DiscordWebHookPayload's are working properly.
 *
 * @author Pascal Zarrad
 */
public class DiscordWebHookTest {

    /**
     * An example payload that has been validated to work properly.
     * It uses every supported feature, so the serialization and sending of WebHooks can
     * be tested full scope.
     */
    private final String exampleWebHookPayload = "{\n" +
            "    \"username\": \"Test User\",\n" +
            "    \"avatar_url\": \"http://localhost/myAvatar.png\",\n" +
            "    \"tts\": true,\n" +
            "    \"content\": \"This is great content!\",\n" +
            "    \"embeds\": [\n" +
            "        {\n" +
            "            \"title\": \"Embed Title!\",\n" +
            "            \"description\": \"My Description\",\n" +
            "            \"url\": \"https://discordapp.com/\",\n" +
            "            \"color\": 16711680,\n" +
            "            \"footer\": {\n" +
            "                \"text\": \"Test Text\",\n" +
            "                \"icon_url\": \"http://localhost/footerIcon.png\"\n" +
            "            },\n" +
            "            \"image\": {\n" +
            "                \"url\": \"http://localhost/EmbeddedImage.png\"\n" +
            "            },\n" +
            "            \"thumbnail\": {\n" +
            "                \"url\": \"http://localhost/thumbnail.png\"\n" +
            "            },\n" +
            "            \"fields\": [\n" +
            "                {\n" +
            "                    \"name\": \"An embedded field\",\n" +
            "                    \"value\": \"This is an embedded field!\",\n" +
            "                    \"inline\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    /**
     * The {@link WireMockServer}used to mock a WebServer to test the execution of WebHooks
     */
    private WireMockServer wireMockServer;

    /**
     * Prepares a {@link WireMockServer} to provide support for test relying on a WebServer.
     */
    @BeforeMethod
    public void prepare() {
        this.wireMockServer = new WireMockServer(options().dynamicPort().bindAddress("127.0.0.1"));
        this.wireMockServer.start();
    }

    /**
     * Stops the running {@link WireMockServer}
     */
    @AfterMethod
    public void reset() {
        this.wireMockServer.stop();
    }

    /**
     * Tests if the values that are passed to a DiscordWebHookPayload are assigned to the right fields.
     */
    @Test
    public void testSerializationOfDiscordWebHookPayloadEqualsReferencePayloadJSON() {
        // Create a DiscordWebHookPayload
        DiscordWebHookPayload discordWebHookPayload = new DiscordWebHookPayload("Test User", "http://localhost/myAvatar.png", true, "This is great content!", new DiscordEmbed[]{
                new DiscordEmbed("Embed Title!", "My Description", "https://discordapp.com/", DiscordEmbedColor.RED,
                        new DiscordEmbedFooter("Test Text", "http://localhost/footerIcon.png"),
                        new DiscordEmbedImage("http://localhost/EmbeddedImage.png"),
                        new DiscordEmbedImage("http://localhost/thumbnail.png"),
                        new DiscordEmbedField[]{
                                new DiscordEmbedField("An embedded field", "This is an embedded field!")
                        })
        });
        // Create DiscordWebHookProcessor
        DiscordWebHookProcessor discordWebHookProcessor = new DiscordWebHookProcessor();
        // Serialize the DiscordWebHookPayload into a JSON string
        String discordWebHookPayloadJSON = discordWebHookProcessor.serializeDiscordWebHookPayload(discordWebHookPayload);
        // Parse our created payload
        JsonParser parser = new JsonParser();
        JsonObject payloadElement = (JsonObject) parser.parse(discordWebHookPayloadJSON);
        // Parse the reference sting
        JsonObject referenceElement = (JsonObject) parser.parse(this.exampleWebHookPayload);
        // Now the two JSonElements should have the same content
        assertEquals(payloadElement, referenceElement, "The serialized payload element does not equal to the reference element!");
    }

    /**
     * Test if the {@link DiscordWebHookProcessor#sendDiscordWebHook(String, String)} sends a valid WebHook request
     */
    @Test
    public void testValidDiscordWebHook() {
        // Create a DiscordWebHookPayload
        DiscordWebHookPayload discordWebHookPayload = new DiscordWebHookPayload("Test User", "http://localhost/myAvatar.png", false, "This is great content!", new DiscordEmbed[]{
                new DiscordEmbed("Embed Title!", "My Description", "https://discordapp.com/", DiscordEmbedColor.RED,
                        new DiscordEmbedFooter("Test Text", "http://localhost/footerIcon.png"),
                        new DiscordEmbedImage("http://localhost/EmbeddedImage.png"),
                        new DiscordEmbedImage("http://localhost/thumbnail.png"),
                        new DiscordEmbedField[]{
                                new DiscordEmbedField("An embedded field", "This is an embedded field!")
                        })
        });
        // Create DiscordWebHookProcessor
        DiscordWebHookProcessor discordWebHookProcessor = new DiscordWebHookProcessor();
        String discordWebHookContent = discordWebHookProcessor.serializeDiscordWebHookPayload(discordWebHookPayload);
        // Configure WireMock
        String localRequestUrl = "http://" + this.wireMockServer.getOptions().bindAddress() + ":" + this.wireMockServer.port() + "/anyWebHookID/anyWebHookToken";
        this.wireMockServer.stubFor(any(urlPathEqualTo("/anyWebHookID/anyWebHookToken"))
                .withHeader("User-Agent", equalTo("TeamCity Discord WebHook v1"))
                .withHeader("Accept-Language", equalTo("en-US,en;q=0.5"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalToJson(discordWebHookContent))
                .willReturn(noContent())
        );
        // Send WebHook
        try {
            assertTrue(discordWebHookProcessor.sendDiscordWebHook(localRequestUrl, discordWebHookPayload), "The send request should end up in Error 204 (No content) and trigger a return true, which represents a valid request!");
        } catch (IOException | URISyntaxException e) {
            fail("Failed to send Discord WebHook due to an IOException!", e);
        }
    }

    /**
     * Test if the {@link DiscordWebHookProcessor#sendDiscordWebHook(String, String)} fails when sending an invalid request
     */
    @Test
    public void testValidDiscordWebHookFailsForInvalidContent() {
        // Create a DiscordWebHookPayload
        DiscordWebHookPayload discordWebHookPayload = new DiscordWebHookPayload(null, null, false, null);
        // Create DiscordWebHookProcessor
        DiscordWebHookProcessor discordWebHookProcessor = new DiscordWebHookProcessor();
        String discordWebHookContent = discordWebHookProcessor.serializeDiscordWebHookPayload(discordWebHookPayload);
        // Configure WireMock
        String localRequestUrl = "http://" + this.wireMockServer.getOptions().bindAddress() + ":" + this.wireMockServer.port() + "/anyWebHookID/anyWebHookToken";
        this.wireMockServer.stubFor(any(urlPathEqualTo("/anyWebHookID/anyWebHookToken"))
                .withHeader("User-Agent", equalTo("TeamCity Discord WebHook v1"))
                .withHeader("Accept-Language", equalTo("en-US,en;q=0.5"))
                .withHeader("Content-Type", equalTo("application/json"))
                .withRequestBody(equalToJson(discordWebHookContent)) // Using the example payload content to check here.
                .willReturn(badRequest())
        );
        // Send WebHook
        try {
            assertFalse(discordWebHookProcessor.sendDiscordWebHook(localRequestUrl, discordWebHookPayload), "The WebServer accepted the request, but the content did not equal a valid format or had valid content!");
        } catch (IOException | URISyntaxException e) {
            fail("Failed to send Discord WebHook due to an IOException!", e);
        }
    }
}
