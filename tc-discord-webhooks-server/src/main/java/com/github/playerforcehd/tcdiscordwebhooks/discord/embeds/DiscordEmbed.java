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
 * A {@link DiscordEmbed} represents a Java object that contains every attribute
 * that can be applied to an embedded Discord message.
 * <p>
 * NOTE: As embeds produced by this application are meant to be used for WebHooks,
 * the attribute type is always set to "rich".
 * Some attributes like video are not available because they are not
 * supported. So implementing them does not make any sense.
 * <p>
 * The documentation about Discord's embeds can be found here:
 * https://discordapp.com/developers/docs/resources/webhook
 *
 * @author Pascal Zarrad
 */
public class DiscordEmbed {

    /**
     * The title that of the embed
     */
    private String title;
    /**
     * The description of the embed
     */
    private String description;

    /**
     * The URL of the embed
     */
    private String url;

    /**
     * The color that the embed should have
     */
    private int color;

    /**
     * The footer to use for the embed
     */
    private DiscordEmbedFooter footer;

    /**
     * The image to use for the embed
     */
    private DiscordEmbedImage image;

    /**
     * The thumbnail to set for the embed.
     * NOTE: The thumbnail embed object has the same properties as
     * an image embed, so they share a class.
     */
    private DiscordEmbedImage thumbnail;

    /**
     * Contains additional fields of text for the embed
     */
    private DiscordEmbedField[] fields;

    public DiscordEmbed(String title, String description, String url, int color, DiscordEmbedFooter footer, DiscordEmbedImage image, DiscordEmbedImage thumbnail, DiscordEmbedField[] fields) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.color = color;
        this.footer = footer;
        this.image = image;
        this.thumbnail = thumbnail;
        this.fields = fields;
    }

    public DiscordEmbed(String title, String description, String url, int color, DiscordEmbedFooter footer, DiscordEmbedImage thumbnail, DiscordEmbedField[] fields) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.color = color;
        this.footer = footer;
        this.thumbnail = thumbnail;
        this.fields = fields;
    }

    public DiscordEmbed(String title, String description, String url, int color, DiscordEmbedFooter footer, DiscordEmbedField[] fields) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.color = color;
        this.footer = footer;
        this.fields = fields;
    }

    public DiscordEmbed() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public DiscordEmbedFooter getFooter() {
        return footer;
    }

    public void setFooter(DiscordEmbedFooter footer) {
        this.footer = footer;
    }

    public DiscordEmbedImage getImage() {
        return image;
    }

    public void setImage(DiscordEmbedImage image) {
        this.image = image;
    }

    public DiscordEmbedImage getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(DiscordEmbedImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public DiscordEmbedField[] getFields() {
        return fields;
    }

    public void setFields(DiscordEmbedField[] fields) {
        this.fields = fields;
    }
}
