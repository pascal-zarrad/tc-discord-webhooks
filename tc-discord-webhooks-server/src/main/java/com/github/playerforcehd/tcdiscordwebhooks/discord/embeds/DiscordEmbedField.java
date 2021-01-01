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
 * Fields that can be added to an {@link DiscordEmbed}
 * to display embedded text
 *
 * @author Pascal Zarrad
 */
public class DiscordEmbedField {

    /**
     * The name of the embed field
     */
    private String name;

    /**
     * The value of the embed field
     */
    private String value;

    /**
     * Decides whether the field should be displayed inline or not
     */
    private boolean inline;

    public DiscordEmbedField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public DiscordEmbedField(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }

    public DiscordEmbedField() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }
}
