/*
 * Copyright 2003-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.ide.messages;

import jetbrains.mps.logging.MPSAppenderBase;
import jetbrains.mps.messages.Message;
import jetbrains.mps.messages.MessageKind;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Priority;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.project.Project;

/**
 * Special handler for messages view employing the log4j mechanism
 * Created by apyshkin on 3/28/17.
 */
final class MessageViewLoggingHandler extends MPSAppenderBase {
  private static final org.apache.log4j.Logger MESSAGE_VIEW_LOG = LogManager.getLogger("###MESSAGES_VIEW_TOKEN###");

  private MessagesViewTool myMessagesView;
  @NotNull private final Project myProject;

  MessageViewLoggingHandler(@NotNull MessagesViewTool messagesView, @NotNull Project project) {
    myMessagesView = messagesView;
    myProject = project;
    setUpApacheLogger();
  }

  private void setUpApacheLogger() {
    MESSAGE_VIEW_LOG.setAdditivity(false);
    MESSAGE_VIEW_LOG.setLevel(Level.ALL);
  }

  @Override
  protected void append(@Nullable Project project,
                        @NotNull Priority level,
                        @NotNull String categoryName,
                        @NotNull String messageText,
                        @Nullable Throwable t,
                        @Nullable Object hintObject) {
    if (projectMatches(project)) {
      MessageKind kind = MessageKind.fromPriority(level);
      Message message = new Message(kind, categoryName, messageText);
      message.setHintObject(hintObject);
      message.setException(t);
      myMessagesView.add(message);
    }
  }

  private boolean projectMatches(@Nullable Project project) {
    return project == null || project.equals(myProject);
  }

  @Override
  public void register() {
    super.register(MESSAGE_VIEW_LOG);
  }

  @Override
  public void unregister() {
    super.unregister(MESSAGE_VIEW_LOG);
  }

  @Override
  protected void append(@NotNull Priority level,
                        @NotNull String categoryName,
                        @NotNull String message,
                        @Nullable Throwable t,
                        @Nullable Object hintObject) {
    append(null, level, categoryName, message, t, hintObject);
  }
}
