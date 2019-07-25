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
package jetbrains.mps.extapi.persistence;

import jetbrains.mps.extapi.model.EditableSModelBase;
import jetbrains.mps.extapi.model.SModelBase;
import jetbrains.mps.extapi.module.SModuleBase;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelId;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleListenerBase;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.ModelRoot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * Base model root implementation which relies on module. Note that the model root might be not attached to module.
 * FIXME a module ought to be passed in constructor
 *
 * evgeny, 10/23/12
 */
public abstract class ModelRootBase implements ModelRoot {
  private static final Logger LOG = Logger.getLogger(ModelRootBase.class);

  @Nullable private SModuleBase myModule;
  @Nullable private volatile SRepository myRepository;
  private final Set<SModel> myModels = new LinkedHashSet<>();
  private final SyncModuleListener myModuleListener = new SyncModuleListener();

  /*@NotNull*/
  @Override
  public final SModule getModule() {
    return myModule;
  }

  public void setModule(@NotNull SModuleBase module) {
    if (myModule != null) {
      throw new IllegalStateException("Already attached to the module " + myModule);
    }
    checkNotRegistered();
    myModule = module;
  }

  protected final void assertCanRead() {
    final SRepository repository = myRepository;
    if (repository != null) {
      repository.getModelAccess().checkReadAccess();
    }
  }

  protected final void assertCanChange() {
    final SRepository repo = myRepository;
    if (repo != null) {
      repo.getModelAccess().checkWriteAccess();
    }
  }

  @NotNull
  @Override
  //todo [MM] make it return collection instead of list, do not copy anything inside (time waste, mem fragmentation)
  //todo [MM] this will be possible when stub node ids do not contain return values
  public final List<SModel> getModels() {
    assertCanRead();
    return Collections.unmodifiableList(new ArrayList<>(myModels));
  }

  /**
   * returns all models under the model root
   * if some model is already loaded and registered, it is recommended to return the loaded one instead of loading another time
   * @return a sequence of models
   */
  @NotNull
  public abstract Iterable<SModel> loadModels();

  @Override
  public boolean canCreateModels() {
    SModule module = getModule();
    return module != null && !module.isReadOnly();
  }

  public void attach() {
    if (myModule == null) {
      throw new IllegalStateException("Module is null");
    }
    myRepository = myModule.getRepository();
    myModule.addModuleListener(myModuleListener);
    update();
  }

  public void dispose() {
    if (myModule != null) {
      Collection<SModel> models = new ArrayList<>(getModels());
      for (SModel model : models) {
        myModule.unregisterModel((SModelBase) model);
      }
    }
    if (isRegistered()) {
      assert myModule != null;
      myModule.removeModuleListener(myModuleListener);
    }
    assert myModels.isEmpty();
    myRepository = null;
  }

  void checkNotRegistered() {
    if (isRegistered()) {
      throw new IllegalStateException("cannot change properties of the registered root");
    }
  }

  public final boolean isRegistered() {
    return myRepository != null;
  }

  @Nullable
  protected final SRepository getRepository() {
    return myRepository;
  }

  /**
   * @param model is the model to be registered here as well as in the enclosing module.
   */
  protected final void registerModel(@NotNull SModel model) {
    SModuleBase module = (SModuleBase) getModule();
    assert module != null;
    assert module.getModel(model.getModelId()) == null;

    if (model instanceof SModelBase) {
      module.registerModel((SModelBase) model);
      ((SModelBase) model).setModelRoot(this);
    }
    myModels.add(model);
  }

  /**
   * note that the model will be removed from our models collection eventually
   * since we subscribed to our model removing events via {@link SyncModuleListener}.
   *
   * FIXME Faulty code is written here, we must not listen to the module events rather invoke this method right in the module class
   */
  private void unregisterModel(@NotNull SModel model) {
    SModuleBase module = (SModuleBase) getModule();
    assert module != null;
    assert module.getModel(model.getModelId()) != null;
    assert myModels.contains(model);
    if (model instanceof SModelBase) {
      ((SModelBase) model).setModelRoot(null);
    }
    if (model instanceof EditableSModelBase && ((EditableSModelBase) model).isChanged()) {
      ((EditableSModelBase) model).resolveDiskConflict();
    } else {
      if (model instanceof SModelBase) {
        module.unregisterModel((SModelBase) model);
      }
    }
  }

  /**
   * IMPORTANT API METHOD
   *
   * Tricky logic which is forced onto all of subclasses.
   * This method represents a caching mechanism which does not reload the models which are already loaded
   * but looks only at the difference between what we had and what we get now
   *
   * Strangely enough this logic is not in API (not added to the API #loadModels implementation) so
   * the client of this class (and its subclasses) has to cast his <code>ModelRoot</code> to <code>ModelRootBase</code>
   * every time he wants to reload the models from their data sources.
   *
   * TODO the right thing
   */
  public void update() {
    assertCanChange();
    SModuleBase module = (SModuleBase) getModule();
    assert module != null;

    Set<SModelId> loaded = new HashSet<>();
    Iterable<SModel> allModels = loadModels();
    for (SModel model : allModels) {
      SModel oldModel = module.getModel(model.getModelId());
      if (oldModel == model) {
        //do nothing
      } else if (oldModel != null && oldModel.getModelRoot() != model.getModelRoot()) {
        LOG.warn("Trying to load model `" + model + "' which is already loaded by another model root");
      } else if (loaded.contains(model.getModelId())) {
        LOG.warn("loadModels() returned model `" + model + "' twice");
      } else {
        if (oldModel != null) {
          LOG.warn("loadModels() loaded model `" + model + "' which id is already present.");
          unregisterModel(oldModel);
        }
        registerModel(model);
      }
      loaded.add(model.getModelId());
    }
    Collection<SModel> models = new ArrayList<>(getModels());
    for (SModel model : models) {
      if (!loaded.contains(model.getModelId())) {
        unregisterModel(model);
      }
    }
  }

  @Override
  public String toString() {
    return "(" + getType() + ") " + getPresentation();
  }

  private final class SyncModuleListener extends SModuleListenerBase {
    @Override
    public void beforeModelRemoved(@NotNull SModule module, @NotNull SModel model) {
      assert myModule == module;
      myModels.remove(model);
    }
  }
}
