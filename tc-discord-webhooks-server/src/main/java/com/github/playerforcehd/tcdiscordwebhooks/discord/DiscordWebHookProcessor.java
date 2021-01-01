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
