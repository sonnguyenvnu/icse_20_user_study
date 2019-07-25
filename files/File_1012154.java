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
package jetbrains.mps.ide.projectPane;

import gnu.trove.TObjectIntHashMap;
import jetbrains.mps.ide.ui.tree.MPSTreeChildOrder;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SNodeTreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Sorting logic geared towards use in project tree. Groups nodes by their type and then by their name.
 * Optionally may group SNode tree elements by their concepts.
 *
 * Use {@link LogicalViewChildOrder} if you need to allow externally-pluggable sorters in your logical view's tree
 *
 * @see LogicalViewChildOrder
 * @author Artem Tikhomirov
 * @since 2019.1
 */
public class ProjectTreeChildOrder implements MPSTreeChildOrder {

  private final Predicate<MPSTreeNode> myGroupByConcept;

  public ProjectTreeChildOrder() {
    myGroupByConcept = null;
  }

  public ProjectTreeChildOrder(Predicate<MPSTreeNode> groupByConcept) {
    myGroupByConcept = groupByConcept;
  }

  @Override
  public boolean reorder(@NotNull MPSTreeNode parent, @NotNull List<MPSTreeNode> childrenToSort) {
    if (!(parent instanceof SModelTreeNode)) {
      return false;
    }
    // case-insensitive is vital for end users (see MPSSPRT-139), and as it's likely all the same for language designers,
    // I decided to keep it here.
    Comparator<MPSTreeNode> byName = Comparator.comparing(MPSTreeNode::getText, String.CASE_INSENSITIVE_ORDER);
    // just in case sort() algorithm doesn't walk list from start to end but compare its elements in a different order (quicksort?),
    // ensure comparator is properly initialized at expense of extra list walk
    childrenToSort.sort(new GroupByFirstClassEncounter<>(childrenToSort).thenComparing(byName));
    if (myGroupByConcept != null && myGroupByConcept.test(parent)) {
      int start = -1, stop = childrenToSort.size();
      for (int i = 0, x = childrenToSort.size(); i < x; i++) {
        if (childrenToSort.get(i) instanceof SNodeTreeNode) {
          if (start == -1) {
            start = i;
          }
        } else {
          if (start != -1) {
            stop = i;
            break;
          }
        }
      }
      if (start != -1) {
        assert start < stop;
        Comparator<MPSTreeNode> c2 = (o1, o2) -> {
          SNode tn1 = ((SNodeTreeNode) o1).getSNode();
          SNode tn2 = ((SNodeTreeNode) o2).getSNode();
          if (tn1 == tn2) {
            return 0;
          }
          if (tn1 == null) {
            // tn2 != null
            return -1;
          }
          if (tn2 == null) {
            return 1;
          }
          return tn1.getConcept().getName().compareTo(tn2.getConcept().getName());
        };
        childrenToSort.subList(start, stop).sort(c2.thenComparing(byName));
      }
    }
    return true;
  }


  /**
   * @param <T> there's no need in T except to ease comparator chaining (Comparator[Object].thenComparing(Comparator[NonObject]) does't compile)
   */
  public static final class GroupByFirstClassEncounter<T> implements Comparator<T> {
    private final TObjectIntHashMap<String> mySeenClasses = new TObjectIntHashMap<>();

    public GroupByFirstClassEncounter() {
    }

    public GroupByFirstClassEncounter(Collection<T> sample) {
      // ensure the order of the classes as in the sample
      sample.forEach(this::getOrRecord);
    }

    @Override
    public int compare(Object o1, Object o2) {
      if (o1 == o2) {
        return 0;
      }
      int v1 = getOrRecord(o1);
      int v2 = getOrRecord(o2);
      return v1 - v2;
    }

    private int getOrRecord(Object o) {
      String n = o.getClass().getName();
      if (mySeenClasses.containsKey(n)) {
        return mySeenClasses.get(n);
      } else {
        int v = mySeenClasses.size();
        mySeenClasses.put(n, v);
        return v;
      }
    }
  }
}
