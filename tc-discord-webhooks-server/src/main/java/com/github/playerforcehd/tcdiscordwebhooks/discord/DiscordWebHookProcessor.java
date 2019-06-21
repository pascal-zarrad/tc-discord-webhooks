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

package com.github.playerforcehd.tcdiscordwebhooks.discord;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Handles the communication between the Discord WebHook API and the TeamCity server.
 * Also handles the serialization of the Discord WebHook Payloads
 *
 * @author Pascal Zarrad
 */
public class DiscordWebHookProcessor {

    /**
     * The charset used for the requests
     */
    private static final String HTTP_CHARSET = StandardCharsets.UTF_8.toString();

    /**
     * The GSON instance used to serialize the {@link DiscordWebHookPayload}'s
     */
    private final Gson GSON;

    public DiscordWebHookProcessor() {
        this.GSON = new Gson();
    }

    /**
     * Send a WebHook request to the Discord API.
     * This method accepts a {@link DiscordWebHookPayload} as argument and serialises it before sending it.
     *
     * @param webHookURL            The URL of the WebHook that is targeted
     * @param discordWebHookPayload The payload which contains the content to send
     * @return true if the request succeeded
     * @throws IOException        Thrown when any I/O operation fails
     * @throws URISyntaxException Thrown when the given #webHookURL is invalid
     * @see DiscordWebHookProcessor#sendDiscordWebHook(String, String)
     */
    public boolean sendDiscordWebHook(String webHookURL, DiscordWebHookPayload discordWebHookPayload) throws IOException, URISyntaxException {
        return this.sendDiscordWebHook(webHookURL, this.serializeDiscordWebHookPayload(discordWebHookPayload));
    }

    /**
     * Send a WebHook request to the Discord API
     *
     * @param webHookURL            The URL of the WebHook that is targeted
     * @param discordWebHookPayload The payload which contains the content to send
     * @return true if the request succeeded
     * @throws IOException        Thrown when any I/O operation fails
     * @throws URISyntaxException Thrown when the given #webHookURL is invalid
     */
    public boolean sendDiscordWebHook(String webHookURL, String discordWebHookPayload) throws IOException, URISyntaxException {
        // Send Discord WebHook
        URL url = new URL(webHookURL);
        int responseCode; // We default to 400, when request succeeded, this should be 204
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url.toURI());
            httpPost.addHeader("User-Agent", "TeamCity Discord WebHook v1");
            httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(discordWebHookPayload, HTTP_CHARSET));
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                responseCode = response.getStatusLine().getStatusCode();
            }
        }
        return responseCode == 204; // When request returned status 204, the request was a success
    }

    /**
     * Serializes a {@link DiscordWebHookPayload} into a JSON string.
     *
     * @param discordWebHookPayload The payload the serialize
     * @return The JSOn string of the {@link DiscordWebHookPayload}
     */
    public String serializeDiscordWebHookPayload(DiscordWebHookPayload discordWebHookPayload) {
        return this.GSON.toJson(discordWebHookPayload);
    }

    public Gson getGSON() {
        return GSON;
    }
}
