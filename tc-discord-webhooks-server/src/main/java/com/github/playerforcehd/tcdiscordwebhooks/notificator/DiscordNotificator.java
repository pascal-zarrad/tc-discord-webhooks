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

package com.github.playerforcehd.tcdiscordwebhooks.notificator;

import com.github.playerforcehd.tcdiscordwebhooks.discord.DiscordWebHookPayload;
import com.github.playerforcehd.tcdiscordwebhooks.discord.DiscordWebHookProcessor;
import jetbrains.buildServer.Build;
import jetbrains.buildServer.notification.Notificator;
import jetbrains.buildServer.notification.NotificatorRegistry;
import jetbrains.buildServer.responsibility.ResponsibilityEntry;
import jetbrains.buildServer.responsibility.TestNameResponsibilityEntry;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.mute.MuteInfo;
import jetbrains.buildServer.serverSide.problems.BuildProblemInfo;
import jetbrains.buildServer.tests.TestName;
import jetbrains.buildServer.users.NotificatorPropertyKey;
import jetbrains.buildServer.users.PropertyKey;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.vcs.VcsRoot;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * The {@link Notificator} service that handles triggered notifications
 *
 * @author Pascal Zarrad
 */
public class DiscordNotificator implements Notificator {

    /**
     * The logger used for debug messages.
     * Mostly used for error logging.
     */
    private static final Logger LOGGER = Logger.getLogger(DiscordNotificator.class);

    /**
     * The type of this {@link Notificator}
     */
    private static final String TYPE = "DiscordWebHookNotificator";

    /**
     * The display name of this notificator
     */
    private static final String DISPLAY_NAME = "Discord WebHook";

    /**
     * Name of the property that defines the URL of the WebHook
     */
    private static final PropertyKey WEBHOOK_URL = new NotificatorPropertyKey(TYPE, "DiscordWebHook_WebHookURL");

    /**
     * Name of the property that defines the Username of the WebHook
     */
    private static final PropertyKey USERNAME = new NotificatorPropertyKey(TYPE, "DiscordWebHook_Username");

    /**
     * The {@link DiscordWebHookProcessor} that is used to trigger the WebHooks
     */
    private final DiscordWebHookProcessor discordWebHookProcessor;

    public DiscordNotificator(NotificatorRegistry notificatorRegistry) {
        this.discordWebHookProcessor = new DiscordWebHookProcessor();
        this.initializeNotificator(notificatorRegistry);
    }

    /**
     * Creates all the {@link UserPropertyInfo}'s  and registers this {@link Notificator}
     *
     * @param notificatorRegistry The {@link NotificatorRegistry} where this {@link Notificator} will be registered to
     */
    private void initializeNotificator(NotificatorRegistry notificatorRegistry) {
        ArrayList<UserPropertyInfo> userProperties = new ArrayList<>();
        userProperties.add(new UserPropertyInfo(WEBHOOK_URL.getKey(), "WebHook URL"));
        userProperties.add(new UserPropertyInfo(USERNAME.getKey(), "Username"));
        notificatorRegistry.register(this, userProperties);
    }

    /**
     * Send the notification by triggering the
     * {@link DiscordWebHookProcessor#sendDiscordWebHook(String, DiscordWebHookPayload)}
     * method using the data given in the discordWebHookPayload parameter.
     *
     * @param discordWebHookPayload The payload to send
     * @param users                 The users that should be notified
     */
    private void processNotify(@NotNull DiscordWebHookPayload discordWebHookPayload, @NotNull Set<SUser> users) {
        for (SUser user : users) {
            String webHookUrl = user.getPropertyValue(WEBHOOK_URL);
            String username = user.getPropertyValue(USERNAME);
            if (webHookUrl == null || webHookUrl.equals("")) {
                LOGGER.error("The Discord WebHook URL for user '" + user.getName() + "' has not been set. Can't execute the WebHook!");
                return;
            }
            if (username != null && !username.equals("")) {
                discordWebHookPayload.setUsername(username);
            }
            try {
                this.discordWebHookProcessor.sendDiscordWebHook(webHookUrl, discordWebHookPayload);
            } catch (IOException | URISyntaxException e) {
                LOGGER.error("Failed to send the WebHook!", e);
            }
        }
    }

    @Override
    public void notifyBuildStarted(@NotNull SRunningBuild sRunningBuild, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildSuccessful(@NotNull SRunningBuild sRunningBuild, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildFailed(@NotNull SRunningBuild sRunningBuild, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildFailedToStart(@NotNull SRunningBuild sRunningBuild, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyLabelingFailed(@NotNull Build build, @NotNull VcsRoot vcsRoot, @NotNull Throwable throwable, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildFailing(@NotNull SRunningBuild sRunningBuild, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildProbablyHanging(@NotNull SRunningBuild sRunningBuild, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyResponsibleChanged(@NotNull SBuildType sBuildType, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyResponsibleAssigned(@NotNull SBuildType sBuildType, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyResponsibleChanged(@Nullable TestNameResponsibilityEntry testNameResponsibilityEntry, @NotNull TestNameResponsibilityEntry testNameResponsibilityEntry1, @NotNull SProject sProject, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyResponsibleAssigned(@Nullable TestNameResponsibilityEntry testNameResponsibilityEntry, @NotNull TestNameResponsibilityEntry testNameResponsibilityEntry1, @NotNull SProject sProject, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyResponsibleChanged(@NotNull Collection<TestName> collection, @NotNull ResponsibilityEntry responsibilityEntry, @NotNull SProject sProject, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyResponsibleAssigned(@NotNull Collection<TestName> collection, @NotNull ResponsibilityEntry responsibilityEntry, @NotNull SProject sProject, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildProblemResponsibleAssigned(@NotNull Collection<BuildProblemInfo> collection, @NotNull ResponsibilityEntry responsibilityEntry, @NotNull SProject sProject, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildProblemResponsibleChanged(@NotNull Collection<BuildProblemInfo> collection, @NotNull ResponsibilityEntry responsibilityEntry, @NotNull SProject sProject, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyTestsMuted(@NotNull Collection<STest> collection, @NotNull MuteInfo muteInfo, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyTestsUnmuted(@NotNull Collection<STest> collection, @NotNull MuteInfo muteInfo, @Nullable SUser sUser, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildProblemsMuted(@NotNull Collection<BuildProblemInfo> collection, @NotNull MuteInfo muteInfo, @NotNull Set<SUser> set) {

    }

    @Override
    public void notifyBuildProblemsUnmuted(@NotNull Collection<BuildProblemInfo> collection, @NotNull MuteInfo muteInfo, @Nullable SUser sUser, @NotNull Set<SUser> set) {

    }

    @NotNull
    @Override
    public String getNotificatorType() {
        return TYPE;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
