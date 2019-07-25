/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.generator.trace;

import jetbrains.mps.generator.runtime.TemplateReductionRule;
import org.jetbrains.mps.openapi.model.SNode;

import java.nio.ByteBuffer;
import java.util.Collection;

/**
 * Alternative trace mechanism approach. Unlike {@link RuleTrace}, not a 'get a bundle ready and fire as batch', but with distinct notifications for each phase.
 * Perhaps, shall be an interface to hide different implementations (e.g. no-op; the one that reports thread; asynch/with batching; synchronous
 *
 * @author Artem Tikhomirov
 */
public final class RuleTrace2 {
  private final Collection<ClientToken> myClients;
  private final TemplateReductionRule myRule;
  private final byte[] myHeader;

  /*package*/ RuleTrace2(Collection<ClientToken> interestedInTheRule, TemplateReductionRule reductionRule) {
    myClients = interestedInTheRule;
    myRule = reductionRule;
    myHeader = myRule.getRuleNode().toString().getBytes();
  }

  public boolean isActive() {
    // alternative to nullable RuleTrace and != null checks
    return true;
  }

  public void reached() {
    notify("[reached]".getBytes());
  }

  public void blocked(boolean blocked) {
    notify(String.format("[blocked:%b]", blocked).getBytes());
  }

  public void condition(boolean conditionMet) {
    notify(String.format("[condition met:%b]", conditionMet).getBytes());
  }

  public void dismissed() {
    notify("[dismissed]".getBytes());
  }

  public void completed(Collection<SNode> outputNodes) {
    notify(String.format("[completed:%d]", outputNodes == null ? 0 : outputNodes.size()).getBytes());
  }

  private void notify(byte[] message) {
    // could be thread-local with pre-filled header and thread id
    final ByteBuffer bb = ByteBuffer.allocate(myHeader.length + message.length + 8 + 1);
    bb.put(myHeader);
    bb.put((byte) 0);
    bb.putLong(Thread.currentThread().getId());
    bb.put(message);
    final byte[] array = bb.array();
    for (ClientToken client : myClients) {
      client.sendToClient(array);
    }
  }
}
