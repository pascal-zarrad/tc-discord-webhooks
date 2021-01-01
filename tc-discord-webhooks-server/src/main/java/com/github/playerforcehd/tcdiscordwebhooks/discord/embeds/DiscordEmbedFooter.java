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
 * A footer element for a {@link DiscordEmbed}
 *
 * @author Pascal Zarrad
 */
public class DiscordEmbedFooter {

    /**
     * The text that the footer should contain
     */
    private String text;

    /**
     * The url to the icon of the footer
     */
    private String icon_url;

    public DiscordEmbedFooter(String text, String icon_url) {
        this.text = text;
        this.icon_url = icon_url;
    }

    public DiscordEmbedFooter() {
    }

    public DiscordEmbedFooter(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
