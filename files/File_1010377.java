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
package jetbrains.mps.smodel.tempmodel;

import jetbrains.mps.smodel.EditableModelDescriptor;
import jetbrains.mps.smodel.ModelLoadResult;
import jetbrains.mps.smodel.SModelId;
import jetbrains.mps.smodel.SNodeUndoableAction;
import jetbrains.mps.smodel.loading.ModelLoadingState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.EditableSModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.NullDataSource;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

class TempModel extends EditableModelDescriptor implements EditableSModel {
  private final boolean myReadOnly;
  private final boolean myTrackUndo;

  protected TempModel(boolean readOnly, boolean trackUndo, @NotNull String namePrefix, SModuleReference moduleReference) {
    super(createModelRef(namePrefix + "_" + System.nanoTime(), moduleReference), new NullDataSource());
    myReadOnly = readOnly;
    myTrackUndo = trackUndo;
  }

  @Override
  public void updateTimestamp() {
    // no-op
  }

  @Override
  public boolean needsReloading() {
    return false;
  }

  @NotNull
  @Override
  protected ModelLoadResult<jetbrains.mps.smodel.SModel> createModel() {
    jetbrains.mps.smodel.SModel smodel = new jetbrains.mps.smodel.SModel(getReference()) {
      @Override
      protected void performUndoableAction(@NotNull SNodeUndoableAction action) {
        if (myTrackUndo) {
          super.performUndoableAction(action);
        }
      }
    };
    return new ModelLoadResult<>(smodel, ModelLoadingState.FULLY_LOADED);
  }

  @Override
  public boolean isChanged() {
    // TODO move TempModels outside of the default repository; false here prevents model from saving
    // FIXME save() is no-op here now, do we still need isChanged() == false?
    return false;
  }

  @Override
  public void save() {
    // no-op, this is in-memory model
  }

  @Override
  public void rename(String newModelName, boolean changeFile) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isReadOnly() {
    return myReadOnly;
  }

  @Override
  public void reloadFromSource() {
    throw new UnsupportedOperationException();
  }

  private static SModelReference createModelRef(String modelName, SModuleReference moduleReference) {
    // todo: make TempModel name customizable? like prefix for temporary file
    SModelId id = SModelId.generate();
    return PersistenceFacade.getInstance().createModelReference(moduleReference, id, modelName);
  }
}
