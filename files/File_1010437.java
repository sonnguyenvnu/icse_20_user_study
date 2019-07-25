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
package jetbrains.mps.make;

import jetbrains.mps.logging.Log4jUtil;
import jetbrains.mps.messages.IMessageHandler;
import jetbrains.mps.messages.Message;
import jetbrains.mps.messages.MessageKind;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Satisfies needs of a code that reports both end-user tailored messages and low-level debug messages.
 * We could have used log4j.Logger if it's an interface to implement. It's also similar to {@link jetbrains.mps.logging.Logger}.
 *
 * Created by apyshkin on 5/25/16.
 */
public class MessageSender {
  private final IMessageHandler myHandler;
  private final Logger myTraceHandler;
  private final Object mySender;

  private final Level myLevel; // only this kind and higher

  /**
   * @param endUserHandler handler to accept end-user tailored message of level info and higher, but not lower than the {@code level} specified.
   * @param lowLevelHandler receives all messages (i.e. from trace to error), with respect to specified {@code level}.
   * @param sender designation of a message source, could be {@code null}. Its {@link Object#toString()} value matters.
   * @param level minimum level of a message we'd like to see in our handlers.
   */
  MessageSender(@NotNull IMessageHandler endUserHandler, @NotNull Logger lowLevelHandler, Object sender, Level level) {
    myHandler = endUserHandler;
    myTraceHandler = lowLevelHandler;
    mySender = sender == null ? "" : sender;
    myLevel = level;
  }

  /**
   * Copy cons that updates message source designation.
   */
  public MessageSender(@NotNull MessageSender anotherSender, Object sender) {
    myHandler = anotherSender.myHandler;
    myTraceHandler = anotherSender.myTraceHandler;
    myLevel = anotherSender.myLevel;
    mySender = sender == null ? "" : sender;
  }

  public void error(@NotNull String msg) {
    error(msg, null);
  }

  public void error(@NotNull String msg, @Nullable Throwable ex) {
    if (isLevelEnabled(Level.ERROR)) {
      myHandler.handle(Message.createMessage(MessageKind.ERROR, mySender.toString(), msg, ex));
      Log4jUtil.error(myTraceHandler, msg, ex, null);
    }
  }

  public void error(@NotNull String msg, @Nullable Object hintObject) {
    if (isLevelEnabled(Level.ERROR)) {
      myHandler.handle(Message.createMessage(MessageKind.ERROR, mySender.toString(), msg, hintObject));
      Log4jUtil.error(myTraceHandler, msg, null, hintObject);
    }
  }

  public void warn(@NotNull String msg, @Nullable Object hintObject) {
    if (isLevelEnabled(Level.WARN)) {
      myHandler.handle(Message.createMessage(MessageKind.WARNING, mySender.toString(), msg, hintObject));
      Log4jUtil.warning(myTraceHandler, msg, null, hintObject);
    }
  }

  public void info(@NotNull String msg) {
    if (isLevelEnabled(Level.INFO)) {
      myHandler.handle(Message.createMessage(MessageKind.INFORMATION, mySender.toString(), msg));
      Log4jUtil.info(myTraceHandler, msg, null, null);
    }
  }

  public void debug(@NotNull String msg) {
    if (isLevelEnabled(Level.DEBUG)) {
      myTraceHandler.debug(msg);
    }
  }

  public void trace(@NotNull String msg) {
    if (isLevelEnabled(Level.TRACE)) {
      myTraceHandler.trace(msg);
    }
  }


  private boolean isLevelEnabled(Level level) {
    return level.isGreaterOrEqual(myLevel);
  }
}
