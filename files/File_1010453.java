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
package org.jetbrains.mps.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;

/**
 * SNode condition which is satisfied with an instance of specified concept.
 * @author Artem Tikhomirov
 */
public final class InstanceOfCondition implements Condition<SNode> {
  private final SAbstractConcept[] myConcepts;
  private boolean myTolerateNull = false;

  public InstanceOfCondition(@NotNull SAbstractConcept concept) {
    myConcepts = new SAbstractConcept[1];
    myConcepts[0] = concept;
  }

  /**
   * Generally, condition doesn't expect null values to come to {@link #met(org.jetbrains.mps.openapi.model.SNode)}, as
   * it's likely a programming error, and there's little reason to hide such.
   *    * However, if input sequence might legally contain <code>null</code> values, use this method
   * to switch tolerance to <code>null</code> on - condition simply is not met in this case.
   * @return <code>this</code> for convenience
   */
  public InstanceOfCondition tolerateNulls() {
    myTolerateNull = true;
    return this;
  }

  public InstanceOfCondition(@NotNull SAbstractConcept[] concepts) {
    ArrayList<SAbstractConcept> a = new ArrayList<>(concepts.length);
    for (SAbstractConcept n : concepts) {
      if (n != null) {
        a.add(n);
      }
    }
    myConcepts = a.toArray(new SAbstractConcept[0]);
  }

  @Override
  public boolean met(SNode node) {
    if (node == null) {
      if (myTolerateNull) {
        return false;
      }
      throw new NullPointerException();
    }
    for (SAbstractConcept c : myConcepts) {
      if (node.getConcept().isSubConceptOf(c)) {
        return true;
      }
    }
    return false;
  }
}
