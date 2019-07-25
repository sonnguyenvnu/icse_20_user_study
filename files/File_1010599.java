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
import gnu.trove.TLongObjectHashMap;
import jetbrains.mps.smodel.SNodeId.Regular;
import jetbrains.mps.smodel.SNodeId.StringBasedId;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class UniversalOptimizedNodeIdMap implements INodeIdToNodeMap {
  private final TLongObjectHashMap<SNode> myRegularMap = new TLongObjectHashMap<>();
  private final THashMap<String, SNode> myStringBasedIdMap = new THashMap<>();

  private final THashMap<SNodeId, SNode> myOtherMap = new THashMap<>();

  @Override
  public int size() {
    return myRegularMap.size() + myStringBasedIdMap.size() + myOtherMap.size();
  }

  @Override
  public SNode get(SNodeId key) {
    if (key instanceof Regular) {
      return myRegularMap.get(((Regular) key).getId());
    } else if (key instanceof StringBasedId) {
      return myStringBasedIdMap.get(((StringBasedId) key).getId());
    } else {
      return myOtherMap.get(key);
    }
  }

  @Override
  public SNode put(SNodeId key, SNode value) {
    if (key instanceof Regular) {
      return myRegularMap.put(((Regular) key).getId(), value);
    } else if (key instanceof StringBasedId) {
      return myStringBasedIdMap.put(((StringBasedId) key).getId(), value);
    } else {
      return myOtherMap.put(key, value);
    }
  }

  @Override
  public boolean containsKey(SNodeId key) {
    if (key instanceof Regular) {
      return myRegularMap.containsKey(((Regular) key).getId());
    } else if (key instanceof StringBasedId) {
      return myStringBasedIdMap.containsKey(((StringBasedId) key).getId());
    } else {
      return myOtherMap.containsKey(key);
    }
  }

  @Override
  public SNode remove(SNodeId key) {
    if (key instanceof Regular) {
      return myRegularMap.remove(((Regular) key).getId());
    } else if (key instanceof StringBasedId) {
      return myStringBasedIdMap.remove(((StringBasedId) key).getId());
    } else {
      return myOtherMap.remove(key);
    }
  }

  @Override
  public Iterable<SNode> values() {
    ArrayList<SNode> rv = new ArrayList<>(myRegularMap.size() + myStringBasedIdMap.size() + myOtherMap.size());
    rv.addAll((Collection) Arrays.asList(myRegularMap.getValues()));
    rv.addAll(myStringBasedIdMap.values());
    rv.addAll(myOtherMap.values());
    return rv;
  }
}
