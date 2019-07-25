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
package jetbrains.mps.smodel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SearchScope;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseScope implements SearchScope {
  @NotNull
  @Override
  public abstract Iterable<SModule> getModules();

  @NotNull
  @Override
  public Iterable<SModel> getModels() {
    List<SModel> models = new ArrayList<>();
    for (SModule module : getModules()) {
      for (SModel model : module.getModels()) {
        models.add(model);
      }
    }
    return models;
  }

  @Override
  public SModule resolve(@NotNull SModuleReference reference) {
    // here used to be a code that matched modules by id or by name, likely to handle missing/outdated names in references.
    // Now we left this matching decision to SModuleReference implementation which in fact now checks module id only and ignores module name.
    for (SModule module : getModules()) {
      if (module.getModuleReference().equals(reference)) {
        return module;
      }
    }
    return null;
  }

  @Override
  public SModel resolve(@NotNull org.jetbrains.mps.openapi.model.SModelReference reference) {
    for (SModel model : getModels()) {
      if (model.getReference().equals(reference)) {
        return model;
      }
    }
    return null;
  }
}
