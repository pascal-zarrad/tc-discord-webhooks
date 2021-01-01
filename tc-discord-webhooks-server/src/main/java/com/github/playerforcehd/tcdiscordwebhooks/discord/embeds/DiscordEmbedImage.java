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
 * An image element for a {@link DiscordEmbed}.
 * This image element provides only the url attribute, due to
 * the fact that Discord's WebHooks do not support any of the other
 * attributes.
 *
 * @author Pascal Zarrad
 */
public class DiscordEmbedImage {

    /**
     * The url of the image to use
     */
    private String url;

    public DiscordEmbedImage(String url) {
        this.url = url;
    }

    public DiscordEmbedImage() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
