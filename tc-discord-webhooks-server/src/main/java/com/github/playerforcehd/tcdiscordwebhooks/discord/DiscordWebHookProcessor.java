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

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Handles the communication between the Discord WebHook API and the TeamCity server.
 * Also handles the serialization of the Discord WebHook Payloads
 *
 * @author Pascal Zarrad
 */
public class DiscordWebHookProcessor {

    public static final String HTTP_CHARSET = StandardCharsets.UTF_8.toString();

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
     * @throws IOException Thrown when any I/O operation fails
     */
    public boolean sendDiscordWebHook(String webHookURL, DiscordWebHookPayload discordWebHookPayload) throws IOException {
        return this.sendDiscordWebHook(webHookURL, this.serializeDiscordWebHookPayload(discordWebHookPayload));
    }

    /**
     * Send a WebHook request to the Discord API
     *
     * @param webHookURL            The URL of the WebHook that is targeted
     * @param discordWebHookPayload The payload which contains the content to send
     * @return true if the request succeeded
     * @throws IOException Thrown when any I/O operation fails
     */
    public boolean sendDiscordWebHook(String webHookURL, String discordWebHookPayload) throws IOException {
        // Prepare POST data
        byte[] data = discordWebHookPayload.getBytes(HTTP_CHARSET);
        // Send Discord WebHook
        URL url = new URL(webHookURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", "TeamCity Discord WebHook v1");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
        httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
        out.write(data, 0, data.length);
        out.flush();
        out.close();
        int responseCode = httpURLConnection.getResponseCode();
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
