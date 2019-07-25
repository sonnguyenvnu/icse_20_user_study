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
package jetbrains.mps.generator.impl;

import jetbrains.mps.generator.IGeneratorLogger;
import jetbrains.mps.messages.IMessage;
import jetbrains.mps.messages.IMessageHandler;
import jetbrains.mps.messages.Message;
import jetbrains.mps.messages.MessageKind;
import jetbrains.mps.util.containers.ConcurrentHashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Translates IGeneratorLogger calls into IMessageHandler's
 * Evgeny Gryaznov, Feb 23, 2010
 */
public class GeneratorLoggerAdapter implements IGeneratorLogger {

  private static final int MAX_EXCEPTION_DEPTH = 10;
  protected final IMessageHandler myMessageHandler;
  protected final MessageFactory myFactory;
  protected final boolean myHandleInfo;
  protected final boolean myHandleWarnings;

  public GeneratorLoggerAdapter(IMessageHandler messageHandler, boolean handleInfo, boolean handleWarnings) {
    this(messageHandler, new BasicFactory(), handleInfo, handleWarnings);
  }

  public GeneratorLoggerAdapter(IMessageHandler messageHandler, MessageFactory msgFactory, boolean handleInfo, boolean handleWarnings) {
    myMessageHandler = messageHandler;
    myFactory = msgFactory;
    myHandleInfo = handleInfo;
    myHandleWarnings = handleWarnings;
  }

  @Override
  public boolean needsInfo() {
    return myHandleInfo;
  }

  @Override
  public boolean needsWarnings() {
    return myHandleWarnings;
  }

  @Override
  public void info(@Nullable SNodeReference node, @NotNull String message) {
    if (!myHandleInfo) {
      return;
    }
    report(MessageKind.INFORMATION, message, node);
  }

  @Override
  public void info(String message) {
    if (!myHandleInfo) {
      return;
    }
    report(MessageKind.INFORMATION, message, null);
  }

  public void trace(String message) {
    for (String s : message.split("\n")) {
      report(MessageKind.INFORMATION, s, null);
    }
  }

  @Override
  public void warning(String message) {
    if (!myHandleWarnings) {
      return;
    }
    warningReported();
    report(MessageKind.WARNING, message, null);
  }

  @Override
  public void warning(@Nullable SNodeReference node, @NotNull String message, @Nullable ProblemDescription... descriptions) {
    if (!myHandleWarnings) {
      return;
    }
    warningReported();
    report(MessageKind.WARNING, message, node, descriptions);
  }

  @Override
  public void error(@Nullable SNodeReference node, @NotNull String message, @Nullable ProblemDescription... descriptions) {
    errorReported();
    report(MessageKind.ERROR, message, node, descriptions);
  }

  @Override
  public void error(String message) {
    errorReported();
    report(MessageKind.ERROR, message, null);
  }

  @Override
  public void handleException(Throwable t) {
    String text = t.getMessage();
    Throwable cause = t;
    for (int i = 0; i < MAX_EXCEPTION_DEPTH; ++i) {
      if (text != null || cause == null) {
        break;
      }
      text = cause.getMessage();
      cause = cause.getCause();
    }
    if (text == null) {
      text = String.format("An exception was encountered: %s (no message)", t.getClass().getName());
    } else {
      text = String.format("(%s): %s", t.getClass().getName(), text);
    }
    errorReported();
    addMessage(new Message(MessageKind.ERROR, text).setException(t));
  }

  protected void errorReported() {
    // no-op
  }

  protected void warningReported() {
    // no-op
  }

  protected final void report(MessageKind kind, String text, SNodeReference node) {
    addMessage(myFactory.prepare(kind, text == null ? "" : text, node));
  }

  protected final void addMessage(@NotNull IMessage msg) {
    myMessageHandler.handle(msg);
  }

  protected final void report(MessageKind kind, String text, SNodeReference node, ProblemDescription... descriptions) {
    if (descriptions == null) {
      report(kind, text, node);
      return;
    }
    List<Message> messages = new ArrayList<>(descriptions.length + 1);
    messages.add(myFactory.prepare(kind, text, node));
    for (ProblemDescription d : descriptions) {
      if (d != null) {
        messages.add(myFactory.prepare(kind, "-- " + d.getMessage(), d.getNode()));
      }
    }
    for (Message m : messages) {
      addMessage(m);
    }
  }

  /*package*/ IMessageHandler getImplementation() {
    return myMessageHandler;
  }

  interface MessageFactory {
    Message prepare(@NotNull MessageKind kind, @NotNull String text, @Nullable SNodeReference node);
  }

  static class BasicFactory implements MessageFactory {
    @NotNull
    public Message prepare(@NotNull MessageKind kind, @NotNull String text, @Nullable SNodeReference node) {
      Message message = new Message(kind, text);
      message.setHintObject(node);
      return message;
    }
  }

  /**
   * Concurrent record of models reported through messages
   */
  static class RecordingFactory implements MessageFactory {
    @SuppressWarnings("unchecked")
    private final Collection<SModelReference>[] a = new Collection[MessageKind.values().length];
    private final MessageFactory myDelegate;

    public RecordingFactory(@NotNull MessageFactory delegate) {
      myDelegate = delegate;
      for (MessageKind k : MessageKind.values()) {
        a[k.ordinal()] = new ConcurrentHashSet<>();
      }
    }
    public Collection<SModelReference> ofKind(MessageKind kind) {
      return a[kind.ordinal()];
    }
    public void reset() {
      for (MessageKind k : MessageKind.values()) {
        a[k.ordinal()].clear();
      }
    }

    /**
     * Record additional access to model, for use from log4j listeners
     */
    public void record(@NotNull MessageKind kind, @Nullable SModelReference modelRef) {
      if (modelRef != null) {
        a[kind.ordinal()].add(modelRef);
      }
    }

    @NotNull
    @Override
    public Message prepare(@NotNull MessageKind kind, @NotNull String text, @Nullable SNodeReference node) {
      if (node != null) {
        record(kind, node.getModelReference());
      }
      return myDelegate.prepare(kind, text, node);
    }
  }
}
