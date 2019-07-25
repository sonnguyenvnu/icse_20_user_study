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
package jetbrains.mps.workbench.structureview.adds;

import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.ide.util.treeView.smartTree.ActionPresentation;
import com.intellij.ide.util.treeView.smartTree.ActionPresentationData;
import com.intellij.ide.util.treeView.smartTree.Group;
import com.intellij.ide.util.treeView.smartTree.Grouper;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import jetbrains.mps.icons.MPSIcons.Nodes.Models;
import jetbrains.mps.plugins.relations.RelationDescriptor;
import jetbrains.mps.workbench.structureview.nodes.AspectTreeElement;
import jetbrains.mps.workbench.structureview.nodes.MainNodeTreeElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AspectGrouper implements Grouper {

  @Override
  @NotNull
  public Collection<Group> group(@NotNull AbstractTreeNode parent, @NotNull final Collection<TreeElement> children) {
    final Object element = parent.getValue();
    if (!(element instanceof MainNodeTreeElement)) {
      return Collections.emptyList();
    }

    Map<RelationDescriptor, List<TreeElement>> groups = new HashMap<>();
    for (TreeElement te : children) {
      if (!(te instanceof AspectTreeElement)) {
        continue;
      }

      AspectTreeElement ate = (AspectTreeElement) te;
      RelationDescriptor d = ate.getAspectDescriptor();
      if (!groups.containsKey(d)) {
        groups.put(d, new ArrayList<>());
      }
      groups.get(d).add(ate);
    }

    Collection<Group> result = new ArrayList<>();
    for (Entry<RelationDescriptor, List<TreeElement>> e : groups.entrySet()) {
      result.add(new AspectGroup(e.getKey(), e.getValue()));
    }
    return result;
  }

  @Override
  @NotNull
  public ActionPresentation getPresentation() {
    return new ActionPresentationData("Group by Aspect", "", Models.AspectModel);
  }

  @Override
  @NotNull
  public String getName() {
    return "AspectGrouper";
  }
}
