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

import com.github.playerforcehd.tcdiscordwebhooks.discord.embeds.DiscordEmbed;

/**
 * A basic Discord payload that can be serialized into
 * JSON to be sent to Discord's WebHook API.
 * <p>
 * The fields of this class represent the attributes of a
 * WebHook request as described in:
 * https://discordapp.com/developers/docs/resources/webhook
 *
 * @author Pascal Zarrad
 */
public class DiscordWebHookPayload {

    /**
     * The username that will be used as the sender username
     */
    private String username;

    /**
     * The url to the avatar to use in the message
     */
    private String avatar_url;

    /**
     * Enable or disable the text-to-speech feature for this message
     */
    private boolean tts;

    /**
     * The content as a simple text message that should be send with the payload
     */
    private String content;

    /**
     * The embeds that should be send with the payload
     */
    private DiscordEmbed[] embeds;

    /**
     * Creates a new {@link DiscordWebHookPayload} that is prepared to send a simple text message bundled with an embed.
     *
     * @param username   The username that will be used as the sender username
     * @param avatar_url The url to the avatar to use in the message
     * @param tts        Enable or disable the text-to-speech feature for this message
     * @param content    The content as a simple text message that should be send with the payload
     * @param embeds     The embeds that should be send with the payload
     */
    public DiscordWebHookPayload(String username, String avatar_url, boolean tts, String content, DiscordEmbed[] embeds) {
        this.username = username;
        this.avatar_url = avatar_url;
        this.tts = tts;
        this.content = content;
        this.embeds = embeds;
    }

    /**
     * Creates a new {@link DiscordWebHookPayload} that is prepared to be send as a simple text message
     *
     * @param username   The username that will be used as the sender username
     * @param avatar_url The url to the avatar to use in the message
     * @param tts        Enable or disable the text-to-speech feature for this message
     * @param content    The content as a simple text message that should be send with the payload
     */
    public DiscordWebHookPayload(String username, String avatar_url, boolean tts, String content) {
        this.username = username;
        this.avatar_url = avatar_url;
        this.tts = tts;
        this.content = content;
    }

    /**
     * Creates a new {@link DiscordWebHookPayload} that is prepared to be send bundled with {@link DiscordEmbed}'s
     *
     * @param username   The username that will be used as the sender username
     * @param avatar_url The url to the avatar to use in the message
     * @param embeds     The embeds that should be send with the payload
     */
    public DiscordWebHookPayload(String username, String avatar_url, DiscordEmbed[] embeds) {
        this.username = username;
        this.avatar_url = avatar_url;
        this.embeds = embeds;
    }

    public DiscordWebHookPayload() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public boolean isTts() {
        return tts;
    }

    public void setTts(boolean tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DiscordEmbed[] getEmbeds() {
        return embeds;
    }

    public void setEmbeds(DiscordEmbed[] embeds) {
        this.embeds = embeds;
    }
}
