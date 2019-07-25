/*
 * Copyright 2003-2018 JetBrains s.r.o.
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

import java.util.Collection;

/**
 * Information to pass to {@link TraceClient} about triggered reduction (or any similar) rule.
 * Builder to get filled with rule/context information which then dispatches it to a TraceClient
 * by means of ClientToken streams.
 * @author Artem Tikhomirov
 */
public final class RuleTrace {
  private final Collection<ClientToken> myClients;
  private final TemplateReductionRule myRule;

  /*package*/ RuleTrace(Collection<ClientToken> interestedInTheRule, TemplateReductionRule reductionRule) {
    myClients = interestedInTheRule;
    myRule = reductionRule;
  }

  private byte[] build() {
    throw new UnsupportedOperationException();
  }

  public void push() {
    byte[] message = build();
    for (ClientToken client : myClients) {
      client.sendToClient(message);
    }
  }
}
