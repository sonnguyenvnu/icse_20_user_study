/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.smodel;

import jetbrains.mps.smodel.impl.StructureAspectChangeTracker;
import jetbrains.mps.smodel.impl.StructureAspectChangeTracker.ModuleListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryContentAdapter;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Entry point to access {@link jetbrains.mps.smodel.FastNodeFinder} instance for a class.
 *
 * Likely to become {@link jetbrains.mps.components.CoreComponent}, then static fields would become instance fields
 * and it would be caller's responsibility to obtain instance of this class.
 *
 * XXX lives in kernel as it depends now from classes that require kernel classes. Consider refactoring and relocating to smodel module.
 * @author Artem Tikhomirov
 */
public class FastNodeFinderManager {
  private static final ConcurrentHashMap<SModelReference, FastNodeFinder> ourFinders = new ConcurrentHashMap<>();
  private static final ConcurrentHashMap<SRepository, StructureAspectChangeTracker> ourStructureChangeTrackers = new ConcurrentHashMap<>();
  private static final ConcurrentHashMap<SRepository, ModelLifecycleTracker> ourLifecycleTrackers = new ConcurrentHashMap<>();
  private static final ModuleListener ourStructureAspectListener = changedModules -> {
    // forget all finders, as it seems cheaper to re-create than to figure out their inter-dependencies
    ArrayList<FastNodeFinder> finders = new ArrayList<>(ourFinders.values());
    ourFinders.clear();
    for (FastNodeFinder finder : finders) {
      finder.dispose();
    }
  };

  @NotNull
  public static FastNodeFinder get(SModel model) {
    SModelReference mr = model.getReference();
    FastNodeFinder finder = ourFinders.get(mr);
    if (finder == null) {
      track(model.getRepository());
      if (model instanceof FastNodeFinder.Factory) {
        finder = ((FastNodeFinder.Factory) model).createNodeFinder(model);
      }
      if (finder == null) {
        // use FNF implementation that doesn't track changes
        finder = new BaseFastNodeFinder(model);
      }
      final FastNodeFinder existing = ourFinders.putIfAbsent(mr, finder);
      if (existing != null) {
        finder = existing;
      }
    }
    return finder;
  }

  public static void dispose(SModel model) {
    SModelReference mr = model.getReference();
    FastNodeFinder finder = ourFinders.remove(mr);
    if (finder != null) {
      finder.dispose();
    }
  }

  /*
   * Repository tracking is superfluous in end-user environment with fixed languages (their structure aspects never change),
   * but there's no way to tell this kind of environment.
   */
  private static void track(SRepository repo) {
    if (repo == null) {
      return;
    }
    if (!ourStructureChangeTrackers.containsKey(repo)) {
      StructureAspectChangeTracker tracker1 = new StructureAspectChangeTracker(null, ourStructureAspectListener);
      ourStructureChangeTrackers.putIfAbsent(repo, tracker1);
      if (tracker1 == ourStructureChangeTrackers.get(repo)) {
        // if another thread got luck, we don't need to attach tracker1 to the repo
        repo.addRepositoryListener(tracker1);
      }
    }
    if (!ourLifecycleTrackers.containsKey(repo)) {
      ModelLifecycleTracker tracker2 = new ModelLifecycleTracker();
      ourLifecycleTrackers.putIfAbsent(repo, tracker2);
      if (tracker2 == ourLifecycleTrackers.get(repo)) {
        repo.addRepositoryListener(tracker2);
      }
    }
  }

  private static class ModelLifecycleTracker extends SRepositoryContentAdapter {
    @Override
    protected void startListening(SModel model) {
      super.startListening(model);
      model.addModelListener(this);
    }

    @Override
    protected void stopListening(SModel model) {
      model.removeModelListener(this);
      super.stopListening(model);
    }

    @Override
    public void beforeModelRemoved(SModule module, SModel model) {
      super.beforeModelRemoved(module, model);
      FastNodeFinderManager.dispose(model);
    }

    @Override
    public void modelReplaced(SModel model) {
      FastNodeFinderManager.dispose(model);
    }

    @Override
    public void modelRenamed(SModule module, SModel model, SModelReference oldRef) {
      FastNodeFinderManager.dispose(model);
    }

    @Override
    public void modelLoaded(SModel model, boolean partially) {
      FastNodeFinderManager.dispose(model);
    }

    @Override
    public void modelUnloaded(SModel model) {
      FastNodeFinderManager.dispose(model);
    }
  }
}
