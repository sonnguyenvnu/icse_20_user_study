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
package jetbrains.mps.smodel;

import jetbrains.mps.extapi.model.EditableSModelBase;
import jetbrains.mps.smodel.loading.ModelLoadResult;
import jetbrains.mps.smodel.loading.ModelLoadingState;
import jetbrains.mps.smodel.loading.PartialModelDataSupport;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.persistence.DataSource;

/**
 * Model with data that could get gradually loaded in subsequent steps.
 * evgeny, 6/6/13
 */
public abstract class LazyEditableSModelBase extends EditableSModelBase {
  private final PartialModelDataSupport<SModel> myModel;

  public LazyEditableSModelBase(@NotNull SModelReference modelReference, @NotNull DataSource source) {
    super(modelReference, source);
    myModel = new PartialModelDataSupport<>(this, state -> {
      // FIXME need a new loadSModel(state) that return proper ModelLoadResult, and deprecate the old one.
      ModelLoadResult result = loadSModel(state);
      if (result.getModel() != null) {
        result.getModel().setModelDescriptor(LazyEditableSModelBase.this, getNodeEventDispatch());
      }
      return new jetbrains.mps.smodel.ModelLoadResult<>(result.getModel(), result.getState());
    });
  }

  @Override
  public final SModel getSModelInternal() {
    ModelLoadingState oldState = getLoadingState();
    SModel res = myModel.getModel(ModelLoadingState.INTERFACE_LOADED);
    if (res == null) {
      return null; // this is when we are in recursion
    }
    fireModelStateChanged(oldState, getLoadingState()); // doesn't dispatch if state is the same
    return res;
  }

  @Override
  public final void load() {
    myModel.getModel(ModelLoadingState.FULLY_LOADED);
  }

  @Override
  protected final SModel getCurrentModelInternal() {
    return myModel.getModel(null);
  }


  @Override
  protected void doUnload() {
    super.doUnload();
    myModel.replaceWith(null, ModelLoadingState.NOT_LOADED);
  }

  /**
   * loads model from the source, w/o changing state of SModelDescriptor
   */
  protected abstract ModelLoadResult loadSModel(ModelLoadingState state);

  void replaceModel(final SModel newModel, final ModelLoadingState state) {
    final boolean needToChangeReference = needToChangeReference(getReference(), newModel.getReference());
    if (newModel == getCurrentModelInternal() && !needToChangeReference) {
      return;
    }
    setChanged(false);
    final SModel oldModel = getCurrentModelInternal();
    myModel.replaceWith(newModel, state);
    // newModel to get modelDescriptor along with event firing
    replaceModelAndFireEvent(oldModel, newModel);

    // fixme AP since we have reference separately from the model data; will go away once the name as well as id is stored inside model data
    if (needToChangeReference) {
      changeModelReference(newModel.getReference());
    }
  }

  @ToRemove(version = 0)
  private boolean needToChangeReference(SModelReference oldRef, SModelReference newRef) {
    return !(oldRef.getModelId().equals(newRef.getModelId()) && oldRef.getName().equals(newRef.getName()));
  }

  @Override
  protected void reloadContents() {
    if (getLoadingState() == ModelLoadingState.NOT_LOADED) {
      return;
    }

    ModelLoadResult result = loadSModel(getLoadingState());
    replaceModel(result.getModel(), result.getState());
  }
}
