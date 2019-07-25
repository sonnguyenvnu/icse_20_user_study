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
package jetbrains.mps.ide.projectPane.logicalview.highlighting;

import jetbrains.mps.ide.projectPane.logicalview.ProjectPaneTree;
import jetbrains.mps.ide.projectPane.logicalview.highlighting.visitor.ErrorChecker;
import jetbrains.mps.ide.projectPane.logicalview.highlighting.visitor.GenStatusUpdater;
import jetbrains.mps.ide.projectPane.logicalview.highlighting.visitor.ModifiedMarker;
import jetbrains.mps.ide.projectPane.logicalview.highlighting.visitor.updates.TreeNodeUpdater;
import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.MPSTreeNodeListener;
import jetbrains.mps.ide.ui.tree.TreeElement;
import jetbrains.mps.ide.ui.tree.TreeNodeVisitor;
import jetbrains.mps.ide.ui.tree.module.ProjectModuleTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModelReadRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ProjectPaneTreeHighlighter {
  private final GenStatusUpdater myGenStatusVisitor;
  private final ErrorChecker myErrorVisitor;
  private final ModifiedMarker myModifiedMarker;
  // receives commands from node status analyzers (TreeUpdateVisitor instances, above) and re-dispatch tree update, batched, in EDT+Read
  private final TreeNodeUpdater myUpdater;
  // threads we'd like to use to analyze status of tree nodes
  private final ThreadPoolExecutor myExecutor = new ThreadPoolExecutor(0, 3, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new RescheduleExecutionHandler());

  private final MyMPSTreeNodeListener myNodeListener = new MyMPSTreeNodeListener();
  private final ProjectPaneTree myTree;
  // although could access one with myTree.getProject().getRepository, it seems safe to record the instance I listen to
  private final SRepository myProjectRepository;
  // containers that control listeners of module and model respectively
  private ModuleNodeListeners myModuleListeners;
  private SModelNodeListeners myModelListeners;
  private volatile boolean myIsPaused = false;

  public ProjectPaneTreeHighlighter(ProjectPaneTree tree, Project mpsProject) {
    myTree = tree;
    myProjectRepository = mpsProject.getRepository();
    myUpdater = new TreeNodeUpdater(mpsProject);
    myGenStatusVisitor = new GenStatusUpdater(mpsProject);
    myErrorVisitor = new ErrorChecker(mpsProject);
    myModifiedMarker = new ModifiedMarker();
  }

  public void init() {
    myGenStatusVisitor.setUpdater(myUpdater);
    myErrorVisitor.setUpdater(myUpdater);
    myModifiedMarker.setUpdater(myUpdater);

    myTree.addTreeNodeListener(myNodeListener);
  }

  public void dispose() {
    myTree.removeTreeNodeListener(myNodeListener);
    if (myModuleListeners != null) {
      myModuleListeners.stopListening();
      myModuleListeners = null;
    }
    if (myModelListeners != null) {
      myModelListeners.stopListening(myProjectRepository, myGenStatusVisitor.getStatusManager());
      myModelListeners = null;
    }
    myExecutor.shutdownNow();
    myGenStatusVisitor.setUpdater(null);
    myErrorVisitor.setUpdater(null);
    myModifiedMarker.setUpdater(null);
  }

  private SModelNodeListeners getModelListeners() {
    if (myModelListeners == null) {
      myModelListeners = new SModelNodeListeners(this);
      myModelListeners.startListening(myProjectRepository, myGenStatusVisitor.getStatusManager());
    }
    return myModelListeners;
  }

  private ModuleNodeListeners getModuleListeners() {
    if (myModuleListeners == null) {
      myModuleListeners = new ModuleNodeListeners(this);
      myModuleListeners.startListening();
    }
    return myModuleListeners;
  }
  @SuppressWarnings("WeakerAccess")
  /*package*/ void moduleNodeAdded(@NotNull ProjectModuleTreeNode node) {
    getModuleListeners().attach(node);
  }
  @SuppressWarnings("WeakerAccess")
  /*package*/ void moduleNodeRemoved(@NotNull ProjectModuleTreeNode node) {
    assert myModuleListeners != null;
    getModuleListeners().detach(node);
  }


  @SuppressWarnings("WeakerAccess")
  /*package*/ void modelNodeAdded(SModelTreeNode modelNode) {
    getModelListeners().attach(modelNode);

  }

  @SuppressWarnings("WeakerAccess")
  /*package*/ void modelNodeRemoved(SModelTreeNode modelNode) {
    assert myModelListeners != null;
    getModelListeners().detach(modelNode);
  }

  /**
   * Highlighter knows which visitor(s) shall run in dumb mode, while outer code controls dumb mode awareness
   */
  public void dumbUpdate() {
    if (myIsPaused) {
      return;
    }
    dispatchForHierarchy(myTree.getRootNode());
  }

  public void pause() {
    myIsPaused = true;
  }

  public void resume() {
    myIsPaused = false;
  }

  /*package*/ void refreshModuleTreeNodes(Collection<ProjectModuleTreeNode> treeNodes) {
    if (myIsPaused) {
      return;
    }
    for (ProjectModuleTreeNode tn : treeNodes) {
      schedule(tn, myErrorVisitor);
    }
  }

  /*package*/ void refreshModelTreeNodes(Collection<SModelTreeNode> treeNodes) {
    if (myIsPaused) {
      return;
    }
    // XXX don't need to keep instance of a visitor any more. Can instantiate here as needed, and then visitors could utilize their state.
    for (SModelTreeNode tn : treeNodes) {
      schedule(tn, myErrorVisitor);
      schedule(tn, myModifiedMarker);
      schedule(tn, myGenStatusVisitor);
    }
  }

  /*package*/ void refreshGenerationStatusForTreeNodes(Collection<SModelTreeNode> treeNodes) {
    if (myIsPaused) {
      return;
    }
    for (SModelTreeNode tn : treeNodes) {
      schedule(tn, myGenStatusVisitor);
    }
  }

  private <T extends MPSTreeNode & TreeElement> void schedule(final T node, final TreeNodeVisitor visitor) {
    myExecutor.execute(new ModelReadRunnable(myProjectRepository.getModelAccess(), () -> {
      boolean disposed = node.getTree() == null;
      if (disposed) {
        return;
      }
      node.accept(visitor);
    }));
  }

  private void dispatchForHierarchy(MPSTreeNode treeNode) {
    if (treeNode instanceof TreeElement) {
      schedule((MPSTreeNode & TreeElement) treeNode, myGenStatusVisitor);
    }
    for (MPSTreeNode node : treeNode.getChildren()) {
      dispatchForHierarchy(node);
    }
  }

  private class MyMPSTreeNodeListener implements MPSTreeNodeListener {

    @Override
    public void treeNodeAdded(MPSTreeNode treeNode, MPSTree tree) {
      if (treeNode instanceof SModelTreeNode) {
        SModelTreeNode modelNode = (SModelTreeNode) treeNode;
        if (modelNode.getModel() != null) {
          modelNodeAdded(modelNode);
        }
      } else if (treeNode instanceof ProjectModuleTreeNode) {
        moduleNodeAdded((ProjectModuleTreeNode) treeNode);
      }
    }

    @Override
    public void treeNodeRemoved(MPSTreeNode treeNode, MPSTree tree) {
      if (treeNode instanceof SModelTreeNode) {
        SModelTreeNode modelNode = (SModelTreeNode) treeNode;
        if (modelNode.getModel() != null) {
          modelNodeRemoved(modelNode);
        }
      } else if (treeNode instanceof ProjectModuleTreeNode) {
        moduleNodeRemoved((ProjectModuleTreeNode) treeNode);
      }
    }

    @Override
    public void treeNodeUpdated(MPSTreeNode treeNode, MPSTree tree) {
    }

    @Override
    public void beforeTreeDisposed(MPSTree tree) {
    }
  }

  /*
   * Policy that reschedules rejected tasks to be executed once tasks that employed available threads are over.
   * Re-scheduling happens from a separate thread to avoid dead-lock when executor.execute() is invoked from withing another
   * task being executed. Rescheduling thread dies after certain amount of inactivity not to consume resources.
   */
  private static class RescheduleExecutionHandler implements RejectedExecutionHandler, Runnable {
    private final LinkedBlockingQueue<Runnable> myQueue = new LinkedBlockingQueue<>();
    private volatile Thread myRescheduleThread;
    private ThreadPoolExecutor myExecutor;

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
      if (executor.isShutdown()) {
        return;
      }
      myQueue.add(r);
      if (myRescheduleThread == null) {
        synchronized (this) {
          if (myRescheduleThread == null) {
            myExecutor = executor;
            myRescheduleThread = new Thread(this);
            myRescheduleThread.start();
          }
        }
      }

    }

    @Override
    public void run() {
      do {
        try {
          Runnable r = myQueue.poll(3000, TimeUnit.MILLISECONDS);
          if (r == null) {
            // die, if there's no new element for 3 seconds
            break;
          }
          myExecutor.getQueue().put(r);
        } catch (InterruptedException ex) {
          // ignore, not too much of a trouble to loose tree status update
        }
      } while (true);
      // if queue is empty for quite a long time, stop the thread.
      synchronized (this) {
        myExecutor = null;
        myRescheduleThread = null;
      }
    }
  }
}
