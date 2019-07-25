/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.smodel.nodeidmap;

import gnu.trove.THashMap;
import jetbrains.mps.smodel.SNodeId.StringBasedId;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.ArrayList;
import java.util.List;

public class StringBasedNodeIdMap implements INodeIdToNodeMap {
  private final THashMap<String, SNode> myStringBasedIds = new THashMap<>();
  private final THashMap<SNodeId, SNode> myOtherMap = new THashMap<>();

  @Override
  public int size() {
    return myOtherMap.size() + myStringBasedIds.size();
  }

  @Override
  public SNode get(SNodeId key) {
    if (key instanceof StringBasedId) {
      return myStringBasedIds.get(((StringBasedId) key).getId());
    }
    return myOtherMap.get(key);
  }

  @Override
  public SNode put(SNodeId key, SNode value) {
    if (key instanceof StringBasedId) {
      return myStringBasedIds.put(((StringBasedId) key).getId(), value);
    }
    return myOtherMap.put(key, value);
  }

  @Override
  public boolean containsKey(SNodeId key) {
    if (key instanceof StringBasedId) {
      return myStringBasedIds.containsKey(((StringBasedId) key).getId());
    }

    return myOtherMap.containsKey(key);
  }

  @Override
  public SNode remove(SNodeId key) {
    if (key instanceof StringBasedId) {
      return myStringBasedIds.remove(((StringBasedId) key).getId());
    }

    return myOtherMap.remove(key);
  }

  @Override
  public Iterable<SNode> values() {
    List<SNode> res = new ArrayList<>();
    res.addAll(myOtherMap.values());
    res.addAll(myStringBasedIds.values());
    return res;
  }
}
