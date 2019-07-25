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

import jetbrains.mps.generator.ModelGenerationStatusListener;
import jetbrains.mps.generator.ModelGenerationStatusManager;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode;
import jetbrains.mps.smodel.RepoListenerRegistrar;
import jetbrains.mps.smodel.SModelAdapter;
import jetbrains.mps.smodel.SModelInternal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryContentAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Control listeners that track changes to a model node.
 * Invoke {@link #startListening(SRepository, ModelGenerationStatusManager)}/{@link #stopListening(SRepository, ModelGenerationStatusManager)} to
 * enable/disable listening, and {@link #attach(jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode)}/{@link #detach(jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode)}
 * to include/exclude selected model tree node from update.
 */
public class SModelNodeListeners {
  private final ModelChangeListener myModelChangeListener;
  private final SRepositoryContentAdapter myRepositoryListener;
  private final ModelGenerationStatusListener myGenStatusListener;

  /**
   * There might be more than one tree node for the same model (e.g. one under language, another under @descriptor),
   * we need to track all tree nodes to update them on model change
   */
  private final Map<SModelReference, Collection<SModelTreeNode>> myTreeNodes = new HashMap<>();
  private final ProjectPaneTreeHighlighter myTreeHighlighter;


  SModelNodeListeners(ProjectPaneTreeHighlighter treeHighlighter) {
    myTreeHighlighter = treeHighlighter;
    myModelChangeListener = new ModelChangeListener();
    myRepositoryListener = new SRepositoryContentAdapter() {
      @Override
      protected void startListening(SModel model) {
        model.addModelListener(this);
      }

      @Override
      protected void stopListening(SModel model) {
        model.removeModelListener(this);
      }

      @Override
      public void modelReplaced(SModel model) {
        refreshAffectedTreeNodes(model);
      }
    };
    myGenStatusListener = new ModelGenerationStatusListener() {
      @Override
      public void generatedFilesChanged(Collection<SModel> models) {
        myTreeHighlighter.refreshGenerationStatusForTreeNodes(findTreeNodes(models));
      }
    };
  }

  public void startListening(SRepository projectRepository, ModelGenerationStatusManager generationStatusManager) {
    new RepoListenerRegistrar(projectRepository, myRepositoryListener).attach();
    generationStatusManager.addGenerationStatusListener(myGenStatusListener);
  }

  public void stopListening(SRepository projectRepository, ModelGenerationStatusManager generationStatusManager) {
    generationStatusManager.removeGenerationStatusListener(myGenStatusListener);
    new RepoListenerRegistrar(projectRepository, myRepositoryListener).detach();
  }

  public void attach(@NotNull SModelTreeNode node) {
    final SModel model = node.getModel();
    if (model != null) {
      boolean modelSeenFirstTime = true;
      synchronized (myTreeNodes) {
        Collection<SModelTreeNode> knownNodes = myTreeNodes.get(model.getReference());
        if (knownNodes == null) {
          myTreeNodes.put(model.getReference(), knownNodes = new ArrayList<>(3));
        } else {
          modelSeenFirstTime = false;
        }
        knownNodes.add(node);
      }
      if (modelSeenFirstTime) {
        ((SModelInternal) model).addModelListener(myModelChangeListener);
      }
    }
    refreshTreeNodes(Collections.singleton(node));
  }

  public void detach(@NotNull SModelTreeNode node) {
    final SModel model = node.getModel();
    if (model != null) {
      boolean modelSeenLastTime = false;
      synchronized (myTreeNodes) {
        Collection<SModelTreeNode> knownNodes = myTreeNodes.get(model.getReference());
        if (knownNodes != null) {
          knownNodes.remove(node);
          if (knownNodes.isEmpty()) {
            myTreeNodes.remove(model.getReference());
            modelSeenLastTime = true;
          }
        }
      }
      if (modelSeenLastTime) {
        ((SModelInternal) model).removeModelListener(myModelChangeListener);
      }
    }
  }

  @SuppressWarnings("WeakerAccess")
  final void refreshAffectedTreeNodes(SModel changed) {
    refreshTreeNodes(findTreeNode(changed));
  }
  @SuppressWarnings("WeakerAccess")
  final void refreshTreeNodes(Collection<SModelTreeNode> treeNodes) {
    myTreeHighlighter.refreshModelTreeNodes(treeNodes);
  }

  Collection<SModelTreeNode> findTreeNode(SModel sm) {
    synchronized (myTreeNodes) {
      final Collection<SModelTreeNode> nodes = myTreeNodes.get(sm.getReference());
      return nodes == null ? Collections.emptyList() : new ArrayList<>(nodes);
    }
  }

  Collection<SModelTreeNode> findTreeNodes(Collection<SModel> models) {
    synchronized (myTreeNodes) {
      return models.stream().map(SModel::getReference).map(myTreeNodes::get).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
    }
  }

  private class ModelChangeListener extends SModelAdapter {
    @Override
    public void modelChangedDramatically(SModel model) {
      refreshAffectedTreeNodes(model);
    }

    @Override
    public void modelChanged(SModel model) {
      refreshAffectedTreeNodes(model);
    }

    @Override
    public void modelSaved(SModel sm) {
      refreshAffectedTreeNodes(sm);
    }
  }
}
