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
 * An element to set the author of an {@link DiscordEmbed}.
 *
 * @author Pascal Zarrad
 */
public class DiscordAuthorEmbed {

    /**
     * The name of the author
     */
    private String name;

    /**
     * The url of the author
     */
    private String url;

    /**
     * The url to the icon of the author
     */
    private String icon_url;

    public DiscordAuthorEmbed(String name, String url, String icon_url) {
        this.name = name;
        this.url = url;
        this.icon_url = icon_url;
    }

    public DiscordAuthorEmbed(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public DiscordAuthorEmbed(String name) {
        this.name = name;
    }

    public DiscordAuthorEmbed() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
