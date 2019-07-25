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
package jetbrains.mps.nodeEditor.reflectiveEditor;

import jetbrains.mps.openapi.editor.update.Updater;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class ReflectiveUpdaterHintsState {
  private final SNode myNode;
  private final Set<ReflectiveHint> myHints;

  ReflectiveUpdaterHintsState(SNode node, Collection<ReflectiveHint> hints) {
    myNode = node;
    myHints = new HashSet<>(hints);
  }

  static ReflectiveUpdaterHintsState load(Updater updater, SNode node) {
    Set<ReflectiveHint> explicitReflectiveHintsForNode = ReflectiveHint.getExplicitReflectiveHintsForNode(updater, node);
    return new ReflectiveUpdaterHintsState(node, explicitReflectiveHintsForNode);
  }

  static void removeAllReflectiveHints(Updater updater, SNode node) {
    new ReflectiveUpdaterHintsState(node, Collections.emptySet()).save(updater);
  }

  void save(Updater updater) {
    Set<ReflectiveHint> explicitReflectiveHintsForNode = ReflectiveHint.getExplicitReflectiveHintsForNode(updater, myNode);
    explicitReflectiveHintsForNode.forEach(reflectiveHint -> reflectiveHint.revoke(updater, myNode));
    myHints.forEach(reflectiveHint -> reflectiveHint.apply(updater, myNode));
  }

  public Set<ReflectiveHint> getHints() {
    return Collections.unmodifiableSet(myHints);
  }
}
