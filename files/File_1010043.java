/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.core.aspects.behaviour;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SNode;

final class NodeOrConcept {
  private final SNode myNode;
  private final SAbstractConcept myAbstractConcept;

  private NodeOrConcept(SNode node, SAbstractConcept abstractConcept) {
    myNode = node;
    myAbstractConcept = abstractConcept;
  }

  public SNode getNode() {
    return myNode;
  }

  public SAbstractConcept getConcept() {
    return myNode != null ? myNode.getConcept() : myAbstractConcept;
  }

  public static NodeOrConcept create(@NotNull SNode node) {
    return new NodeOrConcept(node, null);
  }

  public static NodeOrConcept create(@NotNull SAbstractConcept concept) {
    return new NodeOrConcept(null, concept);
  }

  @NotNull
  public Object getObject() {
    if (myNode != null) {
      return myNode;
    }
    return myAbstractConcept;
  }
}
