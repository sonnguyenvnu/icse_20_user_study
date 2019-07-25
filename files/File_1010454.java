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
package jetbrains.mps.lang.pattern;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Walk/match/extract values of children inside given aggregation link.
 *
 * @author Artem Tikhomirov
 * @since 3.4
 */
public final class ChildMatcher {
  private final NodeMatcher myParent;
  // FIXME some sort of sparse array would be great
  private Map<Integer, NodeMatcher> myIndexToExtractor;
  private String myListPatternVarName;

  /*package*/ ChildMatcher(@NotNull NodeMatcher parent) {
    myParent = parent;
  }

  public NodeMatcher at(int zeroBasedIndex) {
    assert zeroBasedIndex >= 0;
    if (myIndexToExtractor == null) {
      myIndexToExtractor = new HashMap<>(8);
    }
    NodeMatcher rv = myIndexToExtractor.get(zeroBasedIndex);
    if (rv == null) {
      myIndexToExtractor.put(zeroBasedIndex, rv = new NodeMatcher(this));
    }
    return rv;
  }

  public ChildMatcher capture(String listVarName) {
    myListPatternVarName = listVarName;
    return this;
  }

  public NodeMatcher done() {
    return myParent;
  }

  /*package*/ ValueContainer getValues() {
    return myParent.getValues();
  }

  public boolean match(@NotNull List<SNode> pattern, @NotNull List<SNode> actual) {
    if (myListPatternVarName != null) {
      getValues().put(myListPatternVarName, actual);
      // generated code for list pattern variable didn't dig deeper
      return true;
    }
    final boolean noPatternChildrenExtractorsOnly;
    if (pattern.size() != actual.size()) {
      if (pattern.size() == 0 && myIndexToExtractor != null && actual.size() == myIndexToExtractor.size()) {
        // for completeness, shall check that all index keys are continuous values [0..actual.size)
        // but don't want to bother unless face an issue that breaks without such check.
        noPatternChildrenExtractorsOnly = true;
      } else {
        return false;
      }
    } else {
      noPatternChildrenExtractorsOnly = false;
    }
    final Set<Integer> index = new HashSet<>(myIndexToExtractor == null ? Collections.emptySet() : myIndexToExtractor.keySet());
    final NodeMatcher defaultHandler = new NodeMatcher(this);
    for (int i = 0, x = actual.size(); i < x; i++) {
      NodeMatcher childExtractor = defaultHandler;
      if (index.remove(i)) {
        childExtractor = myIndexToExtractor.get(i);
      }
      if (!childExtractor.internalMatch(noPatternChildrenExtractorsOnly ? null : pattern.get(i), actual.get(i))) {
        return false;
      }
    }
    assert index.isEmpty() : String.format("Children with index %s were expected", index.toArray());
    return true;
  }
}
