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
package jetbrains.mps.ide.projectPane.logicalview.highlighting;

import com.intellij.openapi.application.ApplicationManager;
import jetbrains.mps.classloading.ClassLoaderManager;
import jetbrains.mps.classloading.DeployListener;
import jetbrains.mps.ide.MPSCoreComponents;
import jetbrains.mps.ide.ui.tree.module.ProjectModuleTreeNode;
import jetbrains.mps.module.ReloadableModule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Control listeners that track changes to module node.
 * Use {@link #startListening()}/{@link #startListening()} to turn change tracking on and off.
 * Use {@link #attach(jetbrains.mps.ide.ui.tree.module.ProjectModuleTreeNode)} and {@link #detach(jetbrains.mps.ide.ui.tree.module.ProjectModuleTreeNode)}
 * to include/exclude given node from update.
 */
public final class ModuleNodeListeners {
  private final List<ProjectModuleTreeNode> myNodes = new ArrayList<>();
  private final Semaphore myListAccess;
  private final MyReloadAdapter myHandler = new MyReloadAdapter();
  private final ProjectPaneTreeHighlighter myTreeHighlighter;

  ModuleNodeListeners(@NotNull ProjectPaneTreeHighlighter treeHighlighter) {
    myTreeHighlighter = treeHighlighter;
    myListAccess = new Semaphore(1);
  }

  private ClassLoaderManager getClassLoaderManager() {
    MPSCoreComponents core = ApplicationManager.getApplication().getComponent(MPSCoreComponents.class);
    return core.getClassLoaderManager();
  }

  public void startListening() {
    getClassLoaderManager().addListener(myHandler);
  }

  public void stopListening() {
    getClassLoaderManager().removeListener(myHandler);
  }

  public void attach(@NotNull ProjectModuleTreeNode node) {
    myListAccess.acquireUninterruptibly();
    try {
      myNodes.add(node);
    } finally {
      myListAccess.release();
    }
    myTreeHighlighter.refreshModuleTreeNodes(Collections.singleton(node));
  }

  public void detach(@NotNull ProjectModuleTreeNode node) {
    myListAccess.acquireUninterruptibly();
    try {
      myNodes.remove(node);
    } finally {
      myListAccess.release();
    }
  }

  void refreshTreeNodes() {
    List<ProjectModuleTreeNode> a;
    myListAccess.acquireUninterruptibly();
    try {
      a = new ArrayList<>(myNodes);
    } finally {
      myListAccess.release();
    }
    myTreeHighlighter.refreshModuleTreeNodes(a);
  }

  private class MyReloadAdapter implements DeployListener {
    @Override
    public void onLoaded(@NotNull Set<ReloadableModule> loadedModules, @NotNull ProgressMonitor monitor) {
      refreshTreeNodes();
    }
  }
}
