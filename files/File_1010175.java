/*
 * Copyright 2003-2014 JetBrains s.r.o.
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

import jetbrains.mps.generator.impl.FastRuleFinder.BlockedReductionsData;
import jetbrains.mps.generator.runtime.TemplateReductionRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Tracks history of applied reductions.
 * Intended to be used from a single thread.
 *
 * XXX Perhaps, shall also absorb tryToReduce(ReductionRule[], TemplateContext)
 *
 * @author Artem Tikhomirov
 */
final class ReductionTrack {
  private final BlockedReductionsData myReductionsData;
  private final Deque<ReductionContext> myReductionStack;

  public ReductionTrack(@NotNull BlockedReductionsData reductionsData) {
    myReductionsData = reductionsData;
    myReductionStack = new ArrayDeque<>(20);
    myReductionStack.push(new ReductionContext());
  }

  /**
   * prevents applying of reduction rules which have already been applied to the input node.
   */
  public void blockReductionsForCopiedNode(SNode inputNode, SNode outputNode) {
    myReductionsData.blockReductionsForCopiedNode(inputNode, outputNode, actual());
  }

  public boolean isReductionBlocked(SNode node, TemplateReductionRule rule) {
    return myReductionsData.isReductionBlocked(node, rule) || actual().isBlocked(node, rule);
  }

  public ReductionContext actual() {
    return myReductionStack.peek();
  }

  public void enter(SNode inputNode, TemplateReductionRule rule) {
    myReductionStack.push(new ReductionContext(actual(), inputNode, rule));
  }

  public void leave() {
    myReductionStack.pop();
  }
}
