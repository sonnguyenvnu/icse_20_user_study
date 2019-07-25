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
package jetbrains.mps.generator.impl;

import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.StaticReference;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SReference;
import org.jetbrains.mps.util.DescendantsTreeIterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * Make predictable/consistent ids in the node tree
 * @author Artem Tikhomirov
 * @since 2017.2
 */
/*package*/ class ConsistentNodeIdentityHelper {
  private final Comparator<SNode> mySortComparator;
  private long myValue = 0;
  private final Map<SNodeId, SNodeId> myChangedNodes = new HashMap<>();

  /*package*/ ConsistentNodeIdentityHelper(@Nullable Comparator<SNode> sorter) {
    mySortComparator = sorter;
  }

  public void apply(SModel model) {
    makeReferencesDirect(model);
    ArrayList<SNode> nodes = new ArrayList<>();
    model.getRootNodes().forEach(nodes::add);
    // detach nodes from the model so that we can change their node ids
    nodes.forEach(model::removeRootNode);
    if (mySortComparator != null) {
      // ensure order of roots is the same
      nodes.sort(mySortComparator);
    }
    // assign ids
    nodes.forEach(this::processTree);
    ///
    /// inject nodes back
    nodes.forEach(model::addRootNode);
  }

  public Map<SNodeId,SNodeId> getChangedNodes() {
    return myChangedNodes;
  }

  private void processTree(SNode n) {
    L1: do {
      processRow(n); // assign values for n and all its siblings
      L2: do {
        SNode firstChild = n.getFirstChild();
        if (firstChild != null) {
          n = firstChild;
          continue L1; // process next level
        }
        do {
          SNode nextSibling = n.getNextSibling();
          if (nextSibling != null) {
            n = nextSibling;
            continue L2; // try first child of a next sibling
          }
          n = n.getParent(); // return one level up, try parent's next sibling
        } while (n != null);
      } while (n != null);
    } while (n != null);
  }


  private void processRow(SNode n) {
    do {
      jetbrains.mps.smodel.SNodeId.Regular newId = new jetbrains.mps.smodel.SNodeId.Regular(myValue++);
      myChangedNodes.put(n.getNodeId(), newId);
      ((jetbrains.mps.smodel.SNode) n).setId(newId);
      n = n.getNextSibling();
    } while (n != null);
  }

  /*
   * We are going to change node ids, any mature reference that keeps node id, not a plain Java reference to SNode would get broken.
   * Here, we replace all references with target in the same model with respective direct object.
   */
  private void makeReferencesDirect(SModel model) {
    final SModelReference modelRef = model.getReference();
    for (SNode n : org.jetbrains.mps.openapi.model.SNodeUtil.getDescendants(model)) {
      for (SReference r : n.getReferences()) {
        if (r instanceof StaticReference && modelRef.equals(r.getTargetSModelReference())) {
          // FWIW, StaticReference.makeDirect() doesn't constitute model write, is it good?
          ((StaticReference) r).makeDirect();
        }
      }
    }
  }

  public static void main(String ... args) {
    final Function<String, SNode> f = (String name) -> {
      jetbrains.mps.smodel.SNode node = new jetbrains.mps.smodel.SNode(SNodeUtil.concept_BaseConcept);
      node.setProperty(SNodeUtil.property_INamedConcept_name, name);
      return node;
    };

    SNode root = f.apply("0");
    SNode c1, c2, c3;
    root.addChild(SNodeUtil.link_BaseConcept_smodelAttribute, c1 = f.apply("1"));
    root.addChild(SNodeUtil.link_BaseConcept_smodelAttribute, c2 = f.apply("2"));
    root.addChild(SNodeUtil.link_BaseConcept_smodelAttribute, c3 = f.apply("3"));
    IntStream.rangeClosed(4, 10).mapToObj(String::valueOf).map(f).forEach(node -> c1.addChild(SNodeUtil.link_BaseConcept_smodelAttribute, node));
    IntStream.rangeClosed(11, 15).mapToObj(String::valueOf).map(f).forEach(node -> c2.addChild(SNodeUtil.link_BaseConcept_smodelAttribute, node));
    IntStream.rangeClosed(16, 17).mapToObj(String::valueOf).map(f).forEach(node -> c3.addChild(SNodeUtil.link_BaseConcept_smodelAttribute, node));
    new ConsistentNodeIdentityHelper(null).processTree(root);
    Consumer<SNode> check1 = (SNode n) -> {
      String name = n.getProperty(SNodeUtil.property_INamedConcept_name);
      if (name == null || !name.equals(n.getNodeId().toString())) {
        throw new AssertionError("name:" + name + ", nodeId:" + n.getNodeId());
      }
    };
    long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DescendantsTreeIterator(root), 0), false).count();
    if (18 != count) {
      throw new AssertionError("Expected 18, was " + count);
    }
    StreamSupport.stream(Spliterators.spliteratorUnknownSize(new DescendantsTreeIterator(root), 0), false).forEach(check1);
  }

}
