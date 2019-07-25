/*
 * Copyright 2003-2013 JetBrains s.r.o.
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
package jetbrains.mps.logging;


import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.*;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.project.Project;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implement this to have your own appender.
 */
public abstract class MPSAppenderBase extends AppenderSkeleton {
  private final static AtomicInteger ourCount = new AtomicInteger(0);

  public MPSAppenderBase() {
    this("MPS_APPENDER_" + ourCount.getAndIncrement());
  }

  @SuppressWarnings("ParameterHidesMemberVariable")
  protected MPSAppenderBase(String name) {
    setName(name);
  }

  public void register() {
    register(Logger.getRootLogger());
  }

  public void unregister() {
    unregister(Logger.getRootLogger());
  }

  protected void register(@NotNull Logger logger) {
    logger.addAppender(this);
  }

  protected void unregister(@NotNull Logger logger) {
    logger.removeAppender(this);
  }

  /**
   * @deprecated use with a specified project method instead
   */
  @ToRemove(version = 2017.2)
  @Deprecated
  protected abstract void append(@NotNull Priority level,
                                 @NotNull String categoryName,
                                 @NotNull String message,
                                 @Nullable Throwable t,
                                 @Nullable Object hintObject);

  protected /*abstract*/ void append(@Nullable Project project,
                                 @NotNull Priority level,
                                 @NotNull String categoryName,
                                 @NotNull String message,
                                 @Nullable Throwable t,
                                 @Nullable Object hintObject) {
    append(level, categoryName, message, t, hintObject);
  }

  @Override
  protected void append(LoggingEvent event) {
    ThrowableInformation throwableInformation = event.getThrowableInformation();
    String renderedMessage = event.getRenderedMessage();
    Object message = event.getMessage();
    String categoryName = event.getLoggerName();
    Project project = null;
    if (renderedMessage != null && renderedMessage.equals(message)) {
      message = null;
    } else if (message instanceof MessageObject) {
      MessageObject messageObject = (MessageObject) message;
      renderedMessage = messageObject.getMessage();
      message = messageObject.getHintObject();
      if (messageObject.getSender() != null) {
        categoryName = messageObject.getSender();
      }
      if (messageObject.getProject() != null) {
        project = messageObject.getProject();
      }
    }
    if (renderedMessage == null) {
      renderedMessage = "";
    }
    Throwable throwable = null;
    if (throwableInformation != null) {
      throwable = throwableInformation.getThrowable();
    } else if (message instanceof Throwable) {
      throwable = (Throwable) message;
    }

    append(project, event.getLevel(), categoryName, renderedMessage, throwable, message);
  }

  @Override
  public boolean requiresLayout() {
    return true;
  }

  @Override
  public void close() {
  }
}
