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
package jetbrains.mps.ide.devkit.generator;

import com.intellij.openapi.actionSystem.ActionGroup;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.openapi.navigation.NavigationSupport;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModelReadRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.module.SRepository;

final class GenerationTracerTree extends MPSTree {
  private final GenerationTracerView myView;
  private TraceNodeUI myRootTracerNode;
  private Project myProject;
  private final SNodeId myUserFocus;

  public GenerationTracerTree(@NotNull GenerationTracerView view, @NotNull TraceNodeUI root, @NotNull Project project, @Nullable SNodeReference userFocus) {
    myView = view;
    myRootTracerNode = root;
    myProject = project;
    myUserFocus = userFocus == null ? null : userFocus.getNodeId();
  }

  @Override
  protected void runRebuildAction(Runnable rebuildAction, boolean saveExpansion) {
    super.runRebuildAction(new ModelReadRunnable(myProject.getModelAccess(), rebuildAction), saveExpansion);
  }

  void setRoot(@NotNull TraceNodeUI root) {
    myRootTracerNode = root;
  }

  protected MPSTreeNode rebuild() {
    return create(myRootTracerNode);
  }

  // expects model read
  private MPSTreeNode create(TraceNodeUI n) {
    MPSTreeNode treeNode = new MPSTreeNode(n);
    treeNode.setNodeIdentifier(n.getNodeIdentifier());
    // XXX once/if transient models move out of project repository, would need a mechanism to pass proper repository down here
    SRepository repository = myProject.getRepository();
    // if there are too many node resolves,, getText+getIcon may get inverted to populate smth like workbench.choose.ElementDescriptor
    treeNode.setText(n.getText(repository));
    final SNodeReference target = n.getNavigateTarget();
    if (target != null && target.getModelReference() != null) {
      treeNode.setAdditionalText(target.getModelReference().getModelName());
    }
    treeNode.setIcon(n.getIcon(repository));
    for (TraceNodeUI ch : n.getChildren()) {
      treeNode.add(create(ch));
    }
    treeNode.setToggleClickCount(-1);
    treeNode.setAutoExpandable(treeNode.getChildCount() == 1);
    return treeNode;
  }

  @Override
  protected ActionGroup createPopupActionGroup(final MPSTreeNode node) {
    final Object userObject = node.getUserObject();
    if (false == userObject instanceof TraceNodeUI) {
      return null;
    }
    return myView.getTraceActionGroup((TraceNodeUI) userObject);
  }

  // these actions runWriteInEDT even though they are purely read actions, is convention brought by MPS-15256 - NavigationSupport expects write lock

  @Override
  protected void autoscroll(@NotNull MPSTreeNode node) {
    TraceNodeUI traceNode = (TraceNodeUI) node.getUserObject();
    new Navigate(myProject, traceNode.getNavigateTarget(), myUserFocus).go();
  }

  @Override
  protected void doubleClick(@NotNull MPSTreeNode node) {
    TraceNodeUI traceNode = (TraceNodeUI) node.getUserObject();
    new Navigate(myProject, traceNode.getNavigateTarget(), myUserFocus).go();
  }

  private static final class Navigate implements Runnable {
    private final Project myProject;
    private final SNodeReference myNode;
    private final SNodeId myTrueTarget;

    Navigate(Project mpsProject, SNodeReference node, @Nullable SNodeId mostSpecificNode) {
      myProject = mpsProject;
      myNode = node;
      myTrueTarget = mostSpecificNode;
    }

    @Override
    public void run() {
      if (myNode == null) {
        return;
      }
      SNode node = myNode.resolve(myProject.getRepository());
      if (node == null) {
        return;
      }
      if (myTrueTarget != null) {
        // tracer could not report node of interest as it records input and output, while user might select any child of a node tracer keeps record for.
        // hence, we try to find node with id matching to the one of the node selected by user, in assumption tracer never goes down hierarchy, but rather
        // upwards, finding possible parent. Here, we walk the opposite direction trying to find matches for node of true user interest.
        for (SNode n : SNodeUtil.getDescendants(node)) {
          if (myTrueTarget.equals(n.getNodeId())) {
            node = n;
            break;
          }
        }
      }

      // do not select top-level nodes - don't know the reason, but this is the way it used to be
      NavigationSupport.getInstance().openNode(myProject, node, true, node.getModel() == null || node.getParent() != null);
    }

    /*package*/ void go() {
      myProject.getModelAccess().runReadInEDT(this);
    }
  }
}
