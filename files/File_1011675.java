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
package jetbrains.mps.ide.devkit.editorMenuTrace;

import jetbrains.mps.ide.devkit.cellExplorer.MPSTreeWithAction;
import jetbrains.mps.ide.devkit.editorMenuTrace.EditorMenuTraceNodeInitializer.PathChildMenuTraceNodeInitializer;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.NodeTargetProvider;
import jetbrains.mps.openapi.editor.menus.EditorMenuTraceInfo;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModelReadRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditorMenuTraceTree extends MPSTreeWithAction {

  private final Project myProject;
  private EditorMenuTraceInfo myRoot;
  private List<EditorMenuTraceInfo> myPathFromRoot;

  public EditorMenuTraceTree(Project project) {
    myProject = project;
  }

  @Override
  protected void doInit(MPSTreeNode node, Runnable nodeInitRunnable) {
    super.doInit(node, new ModelReadRunnable(myProject.getModelAccess(), nodeInitRunnable));
  }

  @Override
  protected void runRebuildAction(Runnable rebuildAction, boolean saveExpansion) {
    super.runRebuildAction(new ModelReadRunnable(myProject.getModelAccess(), rebuildAction), saveExpansion);
  }

  @Override
  protected MPSTreeNode rebuild() {
    EditorMenuTraceInfo current = myRoot;
    return new EditorMenuTraceNode(current, new PathChildMenuTraceNodeInitializer(myPathFromRoot), myProject);
  }

  void showItemInTree(EditorMenuTraceInfo traceInfo) {
    myPathFromRoot = getPathFromRoot(traceInfo);
    if (myPathFromRoot.isEmpty()) {
      myRoot = null;
    } else {
      myRoot = myPathFromRoot.get(0);
    }
    rebuildNow();
    MPSTreeNode current = null;

    for (EditorMenuTraceInfo editorMenuTraceInfo : myPathFromRoot) {
      if (current == null) {
        current = getRootNode();
      } else {
        current = current.findExactChildWith(editorMenuTraceInfo);
      }

      if (!current.isInitialized()) {
        current.init();
      }
    }
    selectNode(current);
  }

  private List<EditorMenuTraceInfo> getPathFromRoot(EditorMenuTraceInfo traceInfo) {
    EditorMenuTraceInfo current = traceInfo;
    if (current == null) {
      return Collections.emptyList();
    }
    List<EditorMenuTraceInfo> result = new ArrayList<>();
    result.add(current);
    EditorMenuTraceInfo parent = current.getParent();
    while (parent != null) {
      result.add(parent);
      current = parent;
      parent = current.getParent();
    }
    Collections.reverse(result);
    return result;
  }

  @Override
  protected void doubleClick(@NotNull MPSTreeNode nodeToClick) {
    // TODO: update navigation logic to avoid this copy/paste
    // Copied from jetbrains.mps.ide.projectPane.logicalview.ProjectPaneTree.doubleClick()
    if (nodeToClick instanceof NodeTargetProvider) {
      final SNodeReference navigationTarget = ((NodeTargetProvider) nodeToClick).getNavigationTarget();
      if (navigationTarget != null) {
        new EditorNavigator(myProject).shallFocus(true).selectIfChild().open(navigationTarget);
        return;
      }
      // fall-through
    }
    super.doubleClick(nodeToClick);
  }

}
