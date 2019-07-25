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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Records values matched with a pattern.
 *
 * XXX instead of this container, could have dedicated value holders, and pass them
 * right into {@link NodeMatcher#capture(String)} or {@link ChildMatcher#capture(String)},
 * having fields with this value holders in the generated matcher. It gonna take a bit more work (extra classes),
 * hence stick to this one unless get time/taste to refactor.
 *
 * @author Artem Tikhomirov
 * @since 3.4
 */
public final class ValueContainer {
  private final Map<String,Object> myValues = new HashMap<>();

  public void put(String variableName, SNode value) {
    myValues.put(variableName, value);
  }

  public void put(String variableName, SNodeReference targetRef, SNode target) {
    // in tests, we can't set real nodes (due to SReference implementation rigidness)
    // hence we keep extra node ptr.
    myValues.put("SNodeReference:" + variableName, targetRef);
    myValues.put(variableName, target);
  }

  @Deprecated
  public void put(String variableName, String value) {
    myValues.put(variableName, value);
  }

  public void putProperty(String variableName, Object value) {
    myValues.put(variableName, value);
  }

  public void put(String variableName, List<SNode> value) {
    myValues.put(variableName, value);
  }

  @Nullable
  public SNode getNode(String variableName) {
    Object v = myValues.get(variableName);
    return v instanceof SNode ? (SNode) v : null;
  }

  @Nullable
  public SNode getRefTarget(String variableName) {
    // although could have been replaced with getNode(), I'd like to keep
    // distinct accessor for link pattern variables, just in case I'd need it later.
    return getNode(variableName);
  }

  /**
   * For testing purposes
   */
  /*package*/ SNodeReference getRefTargetPointer(String variableName) {
    Object v = myValues.get("SNodeReference:" + variableName);
    return v instanceof SNodeReference ? (SNodeReference) v : null;
  }

  @SuppressWarnings("unchecked")
  @Nullable
  public List<SNode> getList(String variableName) {
    Object v = myValues.get(variableName);
    return v instanceof List ? (List<SNode>) v : null;
  }

  @Nullable
  @Deprecated
  public String getProperty(String variableName) {
    Object v = myValues.get(variableName);
    return v instanceof String ? (String) v : null;
  }

  @Nullable
  public Object getPropertyValue(String variableName) {
    return myValues.get(variableName);
  }

  /**
   * Reset values in this container with values from supplied one
   */
  public void reset(@NotNull ValueContainer other) {
    myValues.clear();
    myValues.putAll(other.myValues);
  }
}
