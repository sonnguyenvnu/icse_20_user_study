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

import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Configuration mechanism to plug in different sorting/re-arranging approaches for logical view trees.
 * @since 2019.1
 * @author Artem Tikhomirov
 */
public class TreeNodeSortService {
  private final Collection<ChildComparatorProvider> myProviders = new ArrayList<>(2);

  public TreeNodeSortService() {
  }

  public void add(@NotNull ChildComparatorProvider provider) {
    myProviders.add(provider);
  }

  public void remove(@NotNull ChildComparatorProvider provider) {
    myProviders.remove(provider);
  }

  /*package*/ boolean reorder(@NotNull AbstractProjectViewPane viewPane, @NotNull MPSTreeNode parent, @NotNull List<MPSTreeNode> childrenToSort) {
    ChildComparatorProvider neo = null; // 'he is the one'
    int neoWeight = Integer.MIN_VALUE;
    for (ChildComparatorProvider p : myProviders) {
      if (!p.isApplicable(viewPane.getId(), viewPane.getSubId())) {
        continue;
      }
      // just in case there are few one.
      // perhaps, we shall chain them in order of 'weight' value, rather than pick the only one.
      final int w = p.weightFor(parent);
      if (neoWeight < w) {
        neo = p;
        neoWeight = w;
      }
    }
    if (neo == null) {
      return false;
    }
    final Comparator<? super MPSTreeNode> comparator = neo.comparator(parent);
    Objects.requireNonNull(comparator, String.format("Provider %s claimed priority %d for tree node %s but didn't supply any comparator", neo, neoWeight, parent.getClass()));
    childrenToSort.sort(comparator);
    return true;
  }


  // ChildComparatorProvider instances come from a reloadable code, make sure they are not retained by anyone except the service impl.
  // XXX perhaps, shall not limit functionality to sorting, allow filtering children out or adding new one?
  public interface ChildComparatorProvider {
    boolean isApplicable(@NotNull String viewId, @Nullable String viewSubId);

    /**
     * @param parentNode node than requests its children to get sorted
     * @return priority of a comparator this provider may supply for the given parent node; bigger value get higher priority, use {@code Integer.MIN_VALUE} to
     *         indicate no interest in the tree node
     */
    int weightFor(@NotNull MPSTreeNode parentNode);

    /**
     * @param parentNode same node comparator just have had provided {@linkplain #weightFor(MPSTreeNode) priority} for.
     * @return {@code null} return value is treated as error.
     */
    Comparator<? super MPSTreeNode> comparator(@NotNull MPSTreeNode parentNode);
  }
}
