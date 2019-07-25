/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.ide.findusages.view.treeholder.tree;

import jetbrains.mps.smodel.CommandListenerAdapter;
import jetbrains.mps.smodel.SNodePointer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.event.SNodeRemoveEvent;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryContentAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Despite the name, not specific to DataTree any longer. Generic change notifier for elements supplied.
 * Use {@link #subscribeTo(SRepository)}/{@link #unsubscribeFrom(SRepository)} to activate/deactivate change tracking
 */
public class DataTreeChangesNotifier extends SRepositoryContentAdapter {
  private final MyCommandListener myChangeDispatch = new MyCommandListener();

  private final Map<IChangeListener, Set<SNodeReference>> myNodeListeners = new HashMap<>();
  private final Map<IChangeListener, Set<SModelReference>> myModelListeners = new HashMap<>();
  private final Map<IChangeListener, Set<SModuleReference>> myModuleListeners = new HashMap<>();

  public DataTreeChangesNotifier() {
  }

  /**
   * Tells to notify given listener once any node from the supplied set is changed.
   * Sets of nodes do not add up, two subsequent trackNodes() calls for the same listener would
   * result in first registration being void.
   */
  public void trackNodes(@NotNull IChangeListener l, @Nullable Set<SNodeReference> nodes) {
    if (nodes == null || nodes.isEmpty()) {
      myNodeListeners.remove(l);
    } else {
      myNodeListeners.put(l, nodes);
    }
  }

  public void trackModels(@NotNull IChangeListener l, @Nullable Set<SModelReference> models) {
    if (models == null || models.isEmpty()) {
      myModelListeners.remove(l);
    } else {
      myModelListeners.put(l, models);
    }
  }

  public void trackModules(@NotNull IChangeListener l, @Nullable Set<SModuleReference> modules) {
    if (modules == null || modules.isEmpty()) {
      myModuleListeners.remove(l);
    } else {
      myModuleListeners.put(l, modules);
    }
  }

  public void unregister(@NotNull IChangeListener l) {
    myNodeListeners.remove(l);
    myModelListeners.remove(l);
    myModuleListeners.remove(l);
  }


  @Override
  public void startListening(@NotNull SRepository repository) {
    super.startListening(repository);
    repository.getModelAccess().addCommandListener(myChangeDispatch);
  }

  @Override
  public void stopListening(@NotNull SRepository repository) {
    repository.getModelAccess().removeCommandListener(myChangeDispatch);
    super.stopListening(repository);
  }

  @Override
  protected boolean isIncluded(SModule module) {
    return !module.isReadOnly();
  }

  @Override
  protected void startListening(SModel model) {
    model.addChangeListener(this);
  }

  @Override
  protected void stopListening(SModel model) {
    model.removeChangeListener(this);
  }

  @Override
  public void nodeRemoved(@NotNull SNodeRemoveEvent event) {
    // SNode.getReference() for deleted node produces invalid pointer
    final SNodeReference ptr = new SNodePointer(event.getModel().getReference(), event.getChild().getNodeId());
    ArrayList<IChangeListener> toNotify = new ArrayList<>();
    for (IChangeListener l : myNodeListeners.keySet()) {
      if (myNodeListeners.get(l).contains(ptr)) {
        toNotify.add(l);
      }
    }
    myChangeDispatch.changed(toNotify);
  }

  @Override
  public void modelRemoved(SModule module, SModelReference ref) {
    super.modelRemoved(module, ref);
    ArrayList<IChangeListener> toNotify = new ArrayList<>();
    for (IChangeListener l : myModelListeners.keySet()) {
      if (myModelListeners.get(l).contains(ref)) {
        toNotify.add(l);
      }
    }
    myChangeDispatch.changed(toNotify);
  }

  @Override
  public void moduleRemoved(@NotNull SModuleReference module) {
    super.moduleRemoved(module);
    ArrayList<IChangeListener> toNotify = new ArrayList<>();
    for (IChangeListener l : myModuleListeners.keySet()) {
      if (myModuleListeners.get(l).contains(module)) {
        toNotify.add(l);
      }
    }
    myChangeDispatch.changed(toNotify);
  }

  private static class MyCommandListener extends CommandListenerAdapter {
    private final Set<IChangeListener> myListeners2Notify = new HashSet<>();

    public void changed(Collection<IChangeListener> toNotify) {
      if (toNotify.isEmpty()) {
        return;
      }
      synchronized (this) {
        myListeners2Notify.addAll(toNotify);
      }
    }

    @Override
    public void commandFinished() {
      ArrayList<IChangeListener> toNotify = new ArrayList<>();
      synchronized (this) {
        toNotify.addAll(myListeners2Notify);
        myListeners2Notify.clear();
      }
      for (IChangeListener l : toNotify) {
        l.changed();
      }
    }
  }
}
