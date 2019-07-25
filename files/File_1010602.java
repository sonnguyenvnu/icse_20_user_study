/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.smodel.references;

import gnu.trove.THashSet;
import jetbrains.mps.util.PairMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.Set;

public class UnregisteredNodes {
  private static final Logger LOG = LogManager.getLogger(UnregisteredNodes.class);
  private static UnregisteredNodes ourInstance;

  private final PairMap<SModelReference, SNodeId, SNode> myMap = new PairMap<>();
  private final Set<SNode> myNodesWithoutRefs = new THashSet<>();
  private final Object myLock = new Object();

  private boolean myDisabled = true;
  private WarningLevel myWarningLevel = WarningLevel.ERROR;

  public static UnregisteredNodes instance() {
    if (ourInstance == null) {
      ourInstance = new UnregisteredNodes();
    }
    return ourInstance;
  }

  public void enable() {
    myDisabled = false;
  }

  public void disable() {
    myDisabled = true;
    clear();
  }

  public WarningLevel setWarningLevel(WarningLevel level) {
    WarningLevel oldLevel = myWarningLevel;
    myWarningLevel = level;
    return oldLevel;
  }

  private UnregisteredNodes() {

  }

  public void clear() {
    synchronized (myLock) {
      myMap.clear();
      myNodesWithoutRefs.clear();
    }
  }

  public boolean contains(SNode node) {
    if (myDisabled) {
      return false;
    }
    synchronized (myLock) {
      return myMap.values().contains(node) || myNodesWithoutRefs.contains(node);
    }
  }

  // XXX Does it really pay off to put/remove nodes in UN one by one? E.g. when a huge root is removed, we get a lot of nodes recorded this way
  //     while it looks plausible to register a subtree at once. Indeed, extra care would be needed with 'remove' (node structure might get changed
  //     between put and remove, e.g. children removed A->B->C, A.detach, subtree of A put here, then C detached and not recoded anywhere, then A with its B
  //     child attached elsewhere and hence removed from this registry, and then request to resolve C as ref target comes in). Need to think it over.
  public void put(SNode node) {
    if (myDisabled) return;
    if (node.getNodeId() == null || node.getModel() == null) {
      myNodesWithoutRefs.add(node);
      return;
    }
    add(node.getModel().getReference(), node.getNodeId(), node);
  }

  public void remove(SNode node) {
    if (myDisabled) return;
    synchronized (myLock) {
      myMap.remove(node);
      myNodesWithoutRefs.remove(node);
    }
  }

  public SNode get(SModelReference modelReference, SNodeId nodeId) {
    if (myDisabled) return null;
    synchronized (myLock) {
      return myMap.get(modelReference, nodeId);
    }
  }

  private void add(SModelReference reference, SNodeId id, SNode node) {
    boolean showError = false;
    synchronized (myLock) {
      if (myMap.contains(reference, id) && myMap.get(reference, id) != node) {
        showError = true;
      }
      myMap.put(reference, id, node);
    }
    if (showError) {
      switch (myWarningLevel) {
        case ERROR:
          IllegalStateException ex = new IllegalStateException("attempt to put another node with same key: " + reference + "#" + id);
          LOG.error(ex, ex);
          break;
        case WARNING:
          LOG.warn("attempt to put another node with same key: " + reference + "#" + id + ". Undo can be broken.");
          myWarningLevel = WarningLevel.SILENT;
          break;
        case SILENT:
          break;
      }
    }
  }

  public enum WarningLevel {
    ERROR,
    WARNING,
    SILENT
  }
}
