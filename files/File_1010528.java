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
package jetbrains.mps.project.structure;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.smodel.SModelStereotype;
import jetbrains.mps.util.containers.MultiMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelListener;
import org.jetbrains.mps.openapi.model.SModelListenerBase;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleListenerBase;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryAttachListener;
import org.jetbrains.mps.openapi.module.SRepositoryListenerBase;

/**
 * Contribute descriptor models to modules of a given repository.
 * For now, global {@link CoreComponent}, although shall become per-project/per-repository component.
 * OOH, it seems to be project-related, as it generally cares to contribute models to workbench modules only, OTOH we
 * don't have project always (e.g. when building/generating modules from Ant), but we still need these models in that scenario as well.
 * Either shall introduce Project everywhere, and dedicated layer of ProjectCoreComponents, or need a better solution to address lifecycle of
 * repository-specific components (there are others, e.g. FastNodeFinderManager)
 *
 * @author Artem Tikhomirov
 * @since 3.4
 */
public class DescriptorModelComponent implements CoreComponent {
  private final SRepository myRepository;
  private final ModuleTracker myListener;

  public DescriptorModelComponent(SRepository repository, DescriptorModelProvider... providers) {
    myRepository = repository;
    myListener = new ModuleTracker(providers);
  }

  @Override
  public void init() {
    myRepository.getModelAccess().runWriteAction(() -> myRepository.addRepositoryListener(myListener));
  }

  @Override
  public void dispose() {
    // it's vital to have exclusive access to a repository as we contribute/revoke models
    myRepository.getModelAccess().runWriteAction(() -> myRepository.removeRepositoryListener(myListener));
    myListener.disposeProviders();
  }

  private static class ModuleTracker extends SRepositoryListenerBase implements SRepositoryAttachListener {
    private final DescriptorModelProvider[] myProviders;
    private MultiMap<SModule, DescriptorModelProvider> myModule2DescriptorProviders = new MultiMap<>();

    // listener is attached to modules of interest only
    private final SModuleListenerBase myModuleListener = new SModuleListenerBase() {
      @Override
      public void modelAdded(SModule module, SModel model) {
        if (SModelStereotype.isDescriptorModel(model)) {
          // it's me who have added the model, no need to refresh
          return;
        }
        refresh(module);
        attachListeners(model);
      }

      @Override
      public void beforeModelRemoved(SModule module, SModel model) {
        detachListeners(model);
      }

      @Override
      public void modelRemoved(SModule module, SModelReference ref) {
        if (SModelStereotype.isDescriptorModelStereotype(ref.getName().getStereotype())) {
          return; // the only source of descriptor models is our provider, no need to refresh
        }
        // shall refresh once model is gone, not when it's yet about to be removed
        refresh(module);
      }

      @Override
      public void moduleChanged(SModule module) {
        refresh(module);
      }
    };

    // listener is attached to models in modules of interest only
    private final SModelListener myModelListener = new SModelListenerBase() {
      // FIXME would benefit of generic ModelChanged event here, to avoid tracking each distinct node change

      @Override
      public void modelReplaced(SModel model) {
        SModule module = model.getModule();
        if (module != null) {
          refresh(module);
        }
      }

      @Override
      public void modelSaved(SModel model) {
        SModule module = model.getModule();
        if (module != null) {
          refresh(module);
        }
      }
    };

    ModuleTracker(DescriptorModelProvider[] providers) {
      myProviders = providers;
    }

    /**
     * @param module not null
     * @return <code>true</code> if there's a provider interested in the module
     */
    boolean refresh(SModule module) {
      boolean rv = false;
      for (DescriptorModelProvider mp : myProviders) {
        if (mp.isApplicable(module)) {
          myModule2DescriptorProviders.putValue(module, mp);
          mp.refreshModule(module);
          rv = true;
        }
      }
      return rv;
    }

    /**
     * @param module not null
     * @return <code>true</code> if there's a provider interested in the module
     */
    boolean forget(SModule module) {
      boolean rv = false;
      for (DescriptorModelProvider mp : myModule2DescriptorProviders.get(module)) {
        mp.forgetModule(module);
        rv = true;
      }
      myModule2DescriptorProviders.remove(module);
      return rv;
    }


    @Override
    public void startListening(@NotNull SRepository repository) {
      for (SModule module : repository.getModules()) {
        if (refresh(module)) {
          attachListeners(module);
        }
      }
    }

    @Override
    public void stopListening(@NotNull SRepository repository) {
      for (SModule module : repository.getModules()) {
        if (forget(module)) {
          detachListeners(module);
        }
      }
    }

    @Override
    public void moduleAdded(@NotNull SModule module) {
      if (refresh(module)) {
        attachListeners(module);
      }
    }

    @Override
    public void beforeModuleRemoved(@NotNull SModule module) {
      // in fact, shall detach listeners before actual forget, as removal of the descriptor model
      // triggers model events we do not really care about. Shall rather keep set of SModuleReferences we've attached listeners to.
      if (forget(module)) {
        detachListeners(module);
      }
    }

    // module is of interest to one of providers
    private void attachListeners(SModule module) {
      // FIXME would benefit from SModuleAttachListener, like SRepositoryAttachListener, to get the code to attach to each model in a single place
      for (SModel m : module.getModels()) {
        attachListeners(m);
      }
      module.addModuleListener(myModuleListener);
    }

    private void detachListeners(SModule module) {
      for (SModel m : module.getModels()) {
        detachListeners(m);
      }
      module.removeModuleListener(myModuleListener);
    }

    // model comes from a module of interest to one of providers
    void attachListeners(SModel model) {
      if (!SModelStereotype.isDescriptorModel(model)) {
        model.addModelListener(myModelListener);
      }
    }

    void detachListeners(SModel model) {
      if (!SModelStereotype.isDescriptorModel(model)) {
        model.removeModelListener(myModelListener);
      }
    }

    /*package*/ void disposeProviders() {
      for (DescriptorModelProvider mp : myProviders) {
        mp.dispose();
      }
    }
  }
}
