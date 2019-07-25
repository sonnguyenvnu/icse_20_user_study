/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
package jetbrains.mps.ide.projectPane.logicalview;

import jetbrains.mps.ide.ui.smodel.SModelEventsDispatcher;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.MPSTreeNodeListener;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.event.SModelEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.EditableSModel;
import org.jetbrains.mps.openapi.model.SModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks SModelTreeNodes and updates (with ModelStructureUpdate help) tree structure according to model changes.
 * @author Artem Tikhomirov
 */
class TreeStructureUpdate implements MPSTreeNodeListener {

  private final ProjectTree myProjectTree;
  private SModelEventsDispatcher myEventsListener;
  private final Map<SModelTreeNode, ModelChangeListener> myListeners = new HashMap<>();

  public TreeStructureUpdate(ProjectTree projectTree) {
    myProjectTree = projectTree;
  }

  public void init() {
    myEventsListener = new SModelEventsDispatcher(myProjectTree.getProject().getRepository());
    myProjectTree.addTreeNodeListener(this);
  }

  public void dispose() {
    myProjectTree.removeTreeNodeListener(this);
    myEventsListener.dispose();
  }

  @Override
  public void treeNodeAdded(MPSTreeNode treeNode, MPSTree tree) {
    if (treeNode instanceof SModelTreeNode) {
      final SModelTreeNode mn = (SModelTreeNode) treeNode;
      if (mn.getModel() instanceof EditableSModel) {
        assert !myListeners.containsKey(mn);
        final ModelChangeListener l = new ModelChangeListener(myProjectTree.getProject(), mn);
        myEventsListener.registerListener(l);
        myListeners.put(mn, l);
      }
    }
  }

  @Override
  public void treeNodeRemoved(MPSTreeNode treeNode, MPSTree tree) {
    if (treeNode instanceof SModelTreeNode) {
      final ModelChangeListener l = myListeners.remove(treeNode);
      // might be SModelTreeNode for a !EditableSModel
      if (l != null) {
        myEventsListener.unregisterListener(l);
      }
    }
  }

  @Override
  public void treeNodeUpdated(MPSTreeNode treeNode, MPSTree tree) {}

  @Override
  public void beforeTreeDisposed(MPSTree tree) {}

  private static class ModelChangeListener implements SModelEventsDispatcher.SModelEventsListener {
    private final Project myProject;
    private final SModelTreeNode myTreeNode;
    private ModelStructureUpdate myUpdater;

    public ModelChangeListener(@NotNull Project project, @NotNull SModelTreeNode treeNode) {
      myProject = project;
      myTreeNode = treeNode;
    }

    @NotNull
    @Override
    public SModel getModelDescriptor() {
      return myTreeNode.getModel();
    }

    @Override
    public void eventsHappened(List<SModelEvent> events) {
      if (myUpdater == null) {
        myUpdater = new ModelStructureUpdate(myProject, myTreeNode);
        myUpdater.setDependencyRecorder(myTreeNode.getDependencyRecorder());
      }
      myUpdater.eventsHappenedInCommand(events);
    }
  }
}
