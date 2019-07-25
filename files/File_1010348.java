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

import jetbrains.mps.smodel.SModel.ImportElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModelReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The purpose of this class is to keep legacy code related to update of implicit imports.
 * We keep it for compatibility with older persistence versions which used to rely on implicit imports being tracked and updated attribute of a model.
 * With v9, we don't keep implicit imports and re-construct them before save as needed.
 */
public final class ImplicitImportsLegacyHolder {
  private List<ImportElement> myImplicitImports = new ArrayList<>();
  @NotNull
  private final SModel myModel;

  /*package*/ ImplicitImportsLegacyHolder(@NotNull SModel model) {
    myModel = model;
  }

  // create new implicit import list based on used models, explicit import and old implicit import list
  public void calculateImplicitImports() {
    // no-op - we don't save models in old persistence any more, no reason to bother recalculate implicit imports
  }

  public List<ImportElement> getAdditionalModelVersions() {
    return Collections.unmodifiableList(myImplicitImports);
  }

  public void addAdditionalModelVersion(@NotNull SModelReference modelReference, int usedVersion) {
    addAdditionalModelVersion(new ImportElement(modelReference, -1, usedVersion));
  }

  public void addAdditionalModelVersion(@NotNull ImportElement element) {
    myImplicitImports.add(element);
  }

  @Nullable
  public ImportElement find(SModelReference modelReference) {
    for (ImportElement importElement : myImplicitImports) {
      if (importElement.getModelReference().equals(modelReference)) {
        return importElement;
      }
    }
    return null;
  }
}
