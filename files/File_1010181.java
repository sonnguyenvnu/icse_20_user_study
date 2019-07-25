/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.generator.impl.template;

import jetbrains.mps.generator.impl.TemplateGenerator;
import jetbrains.mps.generator.impl.reference.PostponedReference;
import jetbrains.mps.generator.impl.reference.ReferenceInfo;
import jetbrains.mps.generator.impl.reference.ReferenceInfo_CopiedInputNode;
import jetbrains.mps.smodel.FastNodeFinderManager;
import jetbrains.mps.smodel.StaticReference;
import jetbrains.mps.smodel.nodeidmap.INodeIdToNodeMap;
import jetbrains.mps.smodel.nodeidmap.UniversalOptimizedNodeIdMap;
import jetbrains.mps.util.SNodeOperations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.model.SReference;
import org.jetbrains.mps.openapi.util.TreeIterator;
import org.jetbrains.mps.util.DescendantsTreeIterator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Collect changes during template processing
 * @author Artem Tikhomirov
 */
public abstract class DeltaBuilder {
  private final List<DeltaRoot> myDelta = new ArrayList<>();
  private final List<ReplacedRoot> myReplacedRoots = new ArrayList<>(); // view: myDelta.select(ReplacedRoot)
  private final List<NewRoot> myNewRoots = new ArrayList<>(); // view: myDelta.select(NewRoot)
  private final List<CopyRoot> myCopyRoots; // view: myDelta.select(CopyRoot)
  private final List<DeletedRoot> myDeletedRoots = new ArrayList<>(); // view: myDelta.select(DeletedRoot)
  private final UniversalOptimizedNodeIdMap myNewNodes = new UniversalOptimizedNodeIdMap();

  protected DeltaBuilder(List<CopyRoot> rootsStorage) {
    myCopyRoots = rootsStorage;
  }

  public static DeltaBuilder newSingleThreadDeltaBuilder() {
    return new DeltaBuilder(new ArrayList<>()) {
      private final Deque<SNode> myNestedCopyRoots = new ArrayDeque<>();
      private CopyRoot myCurrentRoot;
      private final List<SubTree> myCurrentFragments = new ArrayList<>();

      @Override
      protected Deque<SNode> getNestedCopyRoots() {
        return myNestedCopyRoots;
      }

      @Override
      protected CopyRoot getCurrentRoot() {
        return myCurrentRoot;
      }

      @Override
      protected void setCurrentRoot(CopyRoot currentRoot) {
        myCurrentRoot = currentRoot;
      }

      @Override
      protected List<SubTree> getCurrentFragments() {
        return myCurrentFragments;
      }

      @Override
      protected void initCurrentFragments() {
        // no-op
      }

      @Override
      protected void clearCurrentFragments() {
        myCurrentFragments.clear();
      }
    };
  }

  public static DeltaBuilder newConcurrentDeltaBuilder() {
    return new DeltaBuilder(Collections.synchronizedList(new ArrayList<CopyRoot>())) {
      private final ThreadLocal<CopyRoot> myCurrentRoot = new ThreadLocal<>();
      private final ThreadLocal<Deque<SNode>> myNestedCopyRoots = new ThreadLocal<>();
      private final ThreadLocal<List<SubTree>> myCurrentFragments = new ThreadLocal<>();

      @Override
      protected Deque<SNode> getNestedCopyRoots() {
        Deque<SNode> ncr = myNestedCopyRoots.get();
        if (ncr == null) {
          myNestedCopyRoots.set(ncr = new ArrayDeque<>());
        }
        return ncr;
      }

      @Override
      protected CopyRoot getCurrentRoot() {
        return myCurrentRoot.get();
      }

      @Override
      protected void setCurrentRoot(CopyRoot currentRoot) {
        myCurrentRoot.set(currentRoot);
      }

      @Override
      protected List<SubTree> getCurrentFragments() {
        List<SubTree> l = myCurrentFragments.get();
        if (l == null) {
          return Collections.emptyList();
        }
        return l;
      }

      @Override
      protected void initCurrentFragments() {
        assert myCurrentFragments.get() == null;
        myCurrentFragments.set(new ArrayList<>());
      }

      @Override
      protected void clearCurrentFragments() {
        assert myCurrentFragments.get() != null;
        myCurrentFragments.set(null);
      }
    };
  }

  public void enterInputRoot(@NotNull SNode node) {
    assert getCurrentRoot() == null;
    assert getCurrentFragments().isEmpty();
    final CopyRoot r = new CopyRoot(node);
    setCurrentRoot(r);
    initCurrentFragments();
    myCopyRoots.add(r);
  }

  public void deleteInputRoot(@NotNull SNode node) {
    DeletedRoot r = new DeletedRoot(node);
    synchronized (myDelta) { // XXX synchronize here is just quick-n-dirty guard, revisit multi-threading and use of ThreadLocals
      myDeletedRoots.add(r);
      myDelta.add(r); // we don't care about order of deletions
    }
  }

  public void leaveInputRoot(@NotNull SNode node) {
    assert getCurrentRoot() != null;
    assert getCurrentRoot().myRoot == node;

    final List<SubTree> fragments = getCurrentFragments();
    getCurrentRoot().mySubTrees = fragments.toArray(new SubTree[0]);
    setCurrentRoot(null);
    clearCurrentFragments();
  }

  public void enterNestedCopySrc(@NotNull SNode node) {
    if (!isInsideCopyRoot()) {
      return;
    }
    if (getNestedCopyRoots().isEmpty()) {
      assert getCurrentRoot() != null;
      getCurrentFragments().add(new SubTree(node));
    }
    getNestedCopyRoots().push(node);
  }

  public void leaveNestedCopySrc(@NotNull SNode node) {
    if (!isInsideCopyRoot()) {
      return;
    }
    SNode n = getNestedCopyRoots().pop();
    if (n != node) {
      throw new IllegalStateException();
    }
  }

  /**
   * With parallel generation and in-place at any step, there are chances COPY_SRC from root mapping rule is executed
   * after TemplateGenerator.myDeltaBuilder have been initialized, interleaved with root copying.
   * This check is a sort of quick-n-dirty fix, as there seems to be the only place (TEE.copyNodes and eventually TemplateGenerator.copySrc)
   * and I don't have time to refactor TG in a way DeltaBuilder is not instance field but lives inside root copy facility
   * (although copy facility might be the field, and TG#copySRC might extract DeltaBuilder from it, if present)
   */
  private boolean isInsideCopyRoot() {
    return getCurrentRoot() != null;
  }

  /**
   * @param replacedNode node in the input model to be replaced with nodes from <code>subTree</code>
   * @param roleInParent node's containment
   * @param subTree new nodes to put into model, can be empty to indicate node removal.
   */
  public void registerSubTree(@NotNull SNode replacedNode, @NotNull SContainmentLink roleInParent, @NotNull Collection<SNode> subTree) {
    if (getNestedCopyRoots().isEmpty()) {
      assert getCurrentRoot() != null;
      getCurrentFragments().add(new SubTree(replacedNode, roleInParent, subTree));
    }
  }

  /*
   * Invoked from the single thread
   */
  public void registerRoot(@Nullable SNode oldRoot, @NotNull SNode newRoot) {
    if (oldRoot == null) {
      NewRoot r = new NewRoot(newRoot);
      myDelta.add(r);
      myNewRoots.add(r);
      fillNodeMap(newRoot);
    } else if (oldRoot == newRoot) {
      CopyRoot cr = null;
      for (CopyRoot r : myCopyRoots) {
        if (r.myRoot == newRoot) {
          cr = r;
          break;
        }
      }
      if (cr == null) {
        throw new IllegalStateException();
      }
      myDelta.add(cr);
      cr.fillNodeMap(myNewNodes);
    } else {
      ReplacedRoot rr = null;
      for (ReplacedRoot r : myReplacedRoots) {
        if (r.myReplacedRoot == oldRoot) {
          rr = r;
          break;
        }
      }
      if (rr == null) {
        myDelta.add(rr = new ReplacedRoot(oldRoot, newRoot));
        myReplacedRoots.add(rr);
      } else {
        rr.myReplacements.add(newRoot);
      }
      fillNodeMap(newRoot);
    }
  }

  /**
   * Delayed/postponed changes may replace nodes created earlier, and we shall update
   * delta accordingly.
   */
  public void replacePlaceholderNode(@NotNull SNode placeholder, @NotNull SNode actual) {
    clearNodeMap(placeholder);
    fillNodeMap(actual);
    if (placeholder.getParent() == null) {
      // e.g. MAP-SRC with mapper function at root node in CreateRootRule or MapRootRule
      for (NewRoot r : myNewRoots) {
        if (r.myRoot == placeholder) {
          myNewRoots.remove(r);
          int i = myDelta.indexOf(r);
          myDelta.set(i, new NewRoot(actual));
          return;
        }
      }
      for (ReplacedRoot r : myReplacedRoots) {
        int i = r.myReplacements.indexOf(placeholder);
        if (i != -1) {
          r.myReplacements.set(i, actual);
          return;
        }
      }
      // mapper func in MAP-SRC for top node of in-place change
      for (CopyRoot r : myCopyRoots) {
        for (SubTree t : r.mySubTrees) {
          if (t.isSourceCopy()) {
            continue;
          }
          int i = t.myReplacement.indexOf(placeholder);
          if (i != -1) {
            t.myReplacement.set(i, actual);
            return;
          }
        }
      }
    } else {
      // it's a child, go ahead and replace it. Once delta is applied, actual would get where expected.
      SNodeUtil.replaceWithAnother(placeholder, actual);
    }
  }

  void fillNodeMap(@NotNull SNode newNode) {
    for (SNode n : SNodeUtil.getDescendants(newNode)) {
      myNewNodes.put(n.getNodeId(), n);
    }
  }

  void clearNodeMap(@NotNull SNode newNode) {
    for (SNode n : SNodeUtil.getDescendants(newNode)) {
      myNewNodes.remove(n.getNodeId());
    }
  }

  @Nullable
  public SNode findOutputNodeById(@NotNull SNodeId nodeId) {
    return myNewNodes.get(nodeId);
  }

  public boolean hasChanges() {
    for (DeltaRoot dr : myDelta) {
      if (false == dr instanceof CopyRoot) {
        return true; // both new and replaced root do constitute a change
      }
      if (((CopyRoot) dr).bringsChanges()) {
        return true;
      }
    }
    return false;
  }

  public void prepareReferences(SModel inputModel, TemplateGenerator generator) {
    HashSet<SNode> allReplacedNodes = new HashSet<>();
    for (CopyRoot root : myCopyRoots) {
      allReplacedNodes.addAll(root.getReplacedNodes());
    }
    // reference target under deleted root needs update, too
    for (DeletedRoot root : myDeletedRoots) {
      allReplacedNodes.add(root.myRoot);
    }
    for (ReplacedRoot rr : myReplacedRoots) {
      allReplacedNodes.add(rr.myReplacedRoot);
    }
    // FastNodeFinder update mechanism performs poorly (badly, in fact) with massive in-place updates.
    // It's faster to rebuild FNF completely than to update it. E.g step 4 for lang.editor/editor
    // spent 90 seconds out of 105 in replace of 9k children
    FastNodeFinderManager.dispose(inputModel);
    final SModelReference inputModelRef = inputModel.getReference();
    // update references between changed model elements
    for (CopyRoot root : myCopyRoots) {
      final Set<SNode> replacedNodes;
      replacedNodes = root.getReplacedNodes();
      TreeIterator<SNode> it = root.iterateOrigin();
      while (it.hasNext()) {
        SNode next = it.next();
        if (replacedNodes.contains(next)) {
          // nodes under replaced already have PostponedReferences
          it.skipChildren();
          continue;
        }
        for (SReference reference : next.getReferences()) {
          assert reference instanceof PostponedReference == false : "!!! unexpected PostponedReference in the input model";
          if (!inputModelRef.equals(reference.getTargetSModelReference())) {
            continue;
          }
          final SNode referenceTarget = reference.getTargetNode();
          SNode outputTarget = referenceTarget;
          while (outputTarget != null) {
            if (allReplacedNodes.contains(outputTarget)) {
              // reference points elsewhere in this model under a replaced node.
              // reference needs update, its outputTarget is among replaced nodes
              ReferenceInfo refInfo = new ReferenceInfo_CopiedInputNode(next, referenceTarget);
              new PostponedReference(reference.getLink(), reference.getSourceNode(), refInfo).registerWith(generator);
              break; // while outputTarget
            }
            outputTarget = outputTarget.getParent();
          }
        }
      }
    }
    // make references to point to node directly, not (ModelId+NodeId)
    // as it would be impossible to resolve model once root is detached
    for (SNode rn : allReplacedNodes) {
      for (SNode n : SNodeUtil.getDescendants(rn)) {
        for (SReference r : n.getReferences()) {
          if (!inputModelRef.equals(r.getTargetSModelReference()) || ! (r instanceof StaticReference)) {
            continue;
          }
          ((StaticReference) r).makeDirect();
        }
      }
    }
  }

  public void applyInplace(SModel inputModel) {
    // make the structure change, at last
    for (DeltaRoot dr : myDelta) {
      // additions from NewRoot and ReplacedRoot come in the order they were scheduled to be applied
      // not the order they were ready - to get same order in parallel gen. Although additions from replaced
      // come to the tail of root nodes list as there's no way to keep index of root node.
      if (dr instanceof NewRoot) {
        inputModel.addRootNode(((NewRoot) dr).myRoot);
      } else if (dr instanceof ReplacedRoot) {
        ReplacedRoot rr = (ReplacedRoot) dr;
        // XXX Seems there's no way to replace root node in its original position ?!
        inputModel.removeRootNode(rr.myReplacedRoot);
        for (SNode replacement : rr.myReplacements) {
          inputModel.addRootNode(replacement);
        }
      } else if (dr instanceof DeletedRoot) {
        DeletedRoot root = (DeletedRoot) dr;
        SModel rootModel = root.myRoot.getModel();
        if (rootModel != null) {
          // it's possible for the root to be deleted already, e.g. when there are rootMapRules with keepSourceRoot==true and
          // a drop rule to clear origin root once all desired targets have been created.
          assert root.myRoot.getModel() == inputModel;
          inputModel.removeRootNode(root.myRoot);
        }
      } else {
        CopyRoot root = (CopyRoot) dr;
        // replace nodes
        for (SubTree tree : root.mySubTrees) {
          if (tree.isSourceCopy()) {
            continue;
          }
          assert tree.myInputNode.getModel() == inputModel;
          SNode inputParentNode = tree.myInputNode.getParent();
          SNode anchor = tree.myInputNode.getNextSibling();
          inputParentNode.removeChild(tree.myInputNode);
          for (SNode replacement : tree.myReplacement) {
            inputParentNode.insertChildBefore(tree.myRoleInParent, replacement, anchor);
          }
        }
      }
    }
  }

  @SuppressWarnings("unused")
  public void dump() {
    for (DeltaRoot dr : myDelta) {
      if (dr instanceof NewRoot) {
        System.out.printf("+%s\n", SNodeOperations.getDebugText(((NewRoot) dr).myRoot));
      } else if (dr instanceof ReplacedRoot) {
        ReplacedRoot rr = (ReplacedRoot) dr;
        System.out.printf("R%s - %d\n", SNodeOperations.getDebugText(rr.myReplacedRoot), rr.myReplacements.size());
      } else if (dr instanceof DeletedRoot) {
        System.out.printf("-%s\n", SNodeOperations.getDebugText(((DeletedRoot) dr).myRoot));
      } else {
        CopyRoot root = (CopyRoot) dr;
        char c = root.mySubTrees.length > 0 ? '*' : '~';
        System.out.printf("%c%s\n", c, SNodeOperations.getDebugText(root.myRoot));
        for (SubTree tree : root.mySubTrees) {
          if (tree.isSourceCopy()) {
            System.out.printf("    copysrc %s\n", tree.myInputNode);
          } else {
            StringBuilder sb = new StringBuilder();
            for (SNode r : tree.myReplacement) {
              sb.append(r.toString());
              sb.append(',');
            }
            System.out.printf("    %s - %d - %s -> (%s)\n", tree.myRoleInParent, tree.myReplacement.size(), tree.myInputNode, sb);
          }
        }
      }
    }
    System.out.println();
  }

  protected abstract Deque<SNode> getNestedCopyRoots();
  protected abstract CopyRoot getCurrentRoot();
  protected abstract void setCurrentRoot(CopyRoot currentRoot);
  protected abstract List<SubTree> getCurrentFragments();
  protected abstract void initCurrentFragments();
  protected abstract void clearCurrentFragments();

  private interface DeltaRoot {
  }

  private static class NewRoot implements DeltaRoot {
    public final SNode myRoot;
    public NewRoot(@NotNull SNode newRoot) {
      myRoot = newRoot;
    }
  }
  private static class DeletedRoot implements DeltaRoot {
    public final SNode myRoot;
    public DeletedRoot(@NotNull SNode root) {
      myRoot = root;
    }
  }
  private static class ReplacedRoot implements DeltaRoot {
    public final SNode myReplacedRoot;
    public final List<SNode> myReplacements;
    public ReplacedRoot(@NotNull SNode oldRoot, @NotNull SNode newRoot) {
      myReplacedRoot = oldRoot;
      myReplacements = new ArrayList<>(4);
      myReplacements.add(newRoot);
    }
  }
  private static class CopyRoot implements DeltaRoot {
    public final SNode myRoot;
    private SubTree[] mySubTrees;

    CopyRoot(SNode root) {
      myRoot = root;
    }

    public boolean bringsChanges() {
      for (SubTree tree : mySubTrees) {
        if (!tree.isSourceCopy()) {
          return true;
        }
      }
      return false;
    }

    /**
     * walk over input root
     */
    public TreeIterator<SNode> iterateOrigin() {
      return new DescendantsTreeIterator(myRoot);
    }

    // get to know nodes about to be injected
    public void fillNodeMap(INodeIdToNodeMap map) {
      if (mySubTrees == null) {
        return;
      }
      Set<SNode> replacedNodes = getReplacedNodes();
      TreeIterator<SNode> it = iterateOrigin();
      while (it.hasNext()) {
        SNode next = it.next();
        if (replacedNodes.contains(next)) {
          it.skipChildren();
          continue;
        }
        map.put(next.getNodeId(), next);
      }
      for (SubTree t : mySubTrees) {
        if (t.isSourceCopy()) {
          continue;
        }
        t.fillNodeMap(map);
      }
    }

    public Set<SNode> getReplacedNodes() {
      if (mySubTrees == null) {
        return Collections.emptySet();
      }
      HashSet<SNode> rv = new HashSet<>(mySubTrees.length);
      for (SubTree tree : mySubTrees) {
        if (!tree.isSourceCopy()) {
          rv.add(tree.myInputNode);
        }
      }
      return rv;
    }
  }

  private static class SubTree {
    @NotNull
    private final SNode myInputNode;
    private final SContainmentLink myRoleInParent;
    private final List<SNode> myReplacement; // we need to ensure order doesn't change if we later alter new nodes (MAP-SRC replacements)

    public SubTree(@NotNull SNode inputNode, @NotNull SContainmentLink roleInParent, @NotNull Collection<SNode> subTree) {
      myInputNode = inputNode;
      myRoleInParent = roleInParent;
      myReplacement = subTree instanceof List ? (List<SNode>) subTree : new ArrayList<>(subTree);
    }

    public SubTree(@NotNull SNode inputNode) {
      myInputNode = inputNode;
      myRoleInParent = null;
      myReplacement = null;
    }

    public boolean isSourceCopy() {
      return myRoleInParent == null;
    }

    public void fillNodeMap(INodeIdToNodeMap map) {
      assert !isSourceCopy();
      for (SNode r : myReplacement) {
        for (SNode n : SNodeUtil.getDescendants(r)) {
          map.put(n.getNodeId(), n);
        }
      }
    }
  }

}
