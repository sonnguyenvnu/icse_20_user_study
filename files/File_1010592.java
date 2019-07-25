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
import jetbrains.mps.smodel.StringBasedIdForJavaStubMethods;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.ArrayList;
import java.util.List;

/**
 * if not stub id everything works the same
 *
 * if stub id then we have the following:
 * The stub method node is created with StringBasedIdForJavaStubMethods with the long (with return type) string inside.
 * This id is not used anywhere else.
 * In the method #put we have this node added to the two maps simultaneously: #myStringBasedIds and #myFallbackMap.
 * Notice that the string keys in the string ids maps are written without prefixes.
 *
 * The #get or any other requesting method might receive:
 * 0. non java stub method id => everything is the same
 * 1. The foreign id of <=191.eap7 style (with return method stub id) => the node from the fallback map (#myFallbackForJavaStubMethodRefsMap) will be returned
 * 2. The StringBasedIdForJavaStubMethods id of 191.eap7 style (with return method stub id) => the node from the fallback map will be returned
 * 3. The StringBasedIdForJavaStubMethods id of >=191.eap8 style (with return method stub id) => the node from the fallback map will be returned
 * 4. The foreign id of >=191.eap8 style (without return method stub id) => the node from the default map (#myStringBasedIds) will be returned
 *
 * @author apyshkin
 */
@ToRemove(version = 193)
public final class MigratingJavaStubRefsNodeIdMap implements INodeIdToNodeMap {
  /**
   * contains all the nodes with the usual foreign ids + java stub method node ids with the short (no return) string key.
   */
  private final THashMap<String, SNode> myStringBasedIds = new THashMap<>();
  /**
   * contains all the java stub method node ids with long (with return) string key
   */
  private final THashMap<String, SNode> myFallbackForJavaStubMethodRefsMap = new THashMap<>();
  /**
   * contains not string-based ids
   */
  private final THashMap<SNodeId, SNode> myOtherMap = new THashMap<>();

  @Override
  public int size() {
    return myOtherMap.size() + myStringBasedIds.size();
  }

  @Override
  public SNode get(SNodeId key) {
    if (key instanceof StringBasedId) {
      String id = ((StringBasedId) key).getId();
      if (myStringBasedIds.containsKey(id)) {
        return myStringBasedIds.get(id);
      }
      return myFallbackForJavaStubMethodRefsMap.get(id);
    }

    return myOtherMap.get(key);
  }

  @Override
  public SNode put(SNodeId key, SNode value) {
    if (key instanceof StringBasedIdForJavaStubMethods) {
      StringBasedIdForJavaStubMethods stubId = (StringBasedIdForJavaStubMethods) key;
      myFallbackForJavaStubMethodRefsMap.put(stubId.getIdWithReturnTypeNoPrefix(), value);
      return myStringBasedIds.put(stubId.getIdWithoutReturnTypeNoPrefix(), value);
    } else if (key instanceof StringBasedId) {
      return myStringBasedIds.put(((StringBasedId) key).getId(), value);
    }
    return myOtherMap.put(key, value);
  }

  @Override
  public boolean containsKey(SNodeId key) {
    if (key instanceof StringBasedId) {
      String id = ((StringBasedId) key).getId();
      return myStringBasedIds.containsKey(id) || myFallbackForJavaStubMethodRefsMap.containsKey(id);
    }

    return myOtherMap.containsKey(key);
  }

  @Override
  public SNode remove(SNodeId key) {
    if (key instanceof StringBasedIdForJavaStubMethods) {
      StringBasedIdForJavaStubMethods stubId = (StringBasedIdForJavaStubMethods) key;
      myFallbackForJavaStubMethodRefsMap.remove(stubId.getIdWithReturnTypeNoPrefix());
      return myStringBasedIds.remove(stubId.getIdWithoutReturnTypeNoPrefix());
    } else if (key instanceof StringBasedId) {
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
