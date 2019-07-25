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
package jetbrains.mps.text.impl;

import jetbrains.mps.messages.IMessage;
import jetbrains.mps.messages.Message;
import jetbrains.mps.messages.MessageKind;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FIXME Just a copy of relevant methods from TextGenBuffer, waits for refactoring
 * @author Artem Tikhomirov
 * @since 2017.2
 */
/*package*/ class ErrorCollector {
  private List<IMessage> myErrors = new ArrayList<>();

  /*package*/ boolean hasErrors() {
    return !myErrors.isEmpty();
  }

  /*package*/ List<IMessage> problems() {
    return Collections.unmodifiableList(myErrors);
  }

  /*package*/ void foundError(String error, @Nullable SNode node, @Nullable Throwable t) {
    myErrors.add(prepare(MessageKind.ERROR, error, node).setException(t));
  }

  private static Message prepare(MessageKind kind, String text, @Nullable SNode node) {
    Message message = new Message(kind, text);
    if (node != null) {
      message.setHintObject(node.getReference());
    }
    return message;
  }
}
