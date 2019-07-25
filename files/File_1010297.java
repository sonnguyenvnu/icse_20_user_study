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
package jetbrains.mps.persistence;

import jetbrains.mps.extapi.persistence.FileBasedModelRoot;
import jetbrains.mps.extapi.persistence.SourceRoot;
import jetbrains.mps.vfs.IFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.model.SModelName;

/**
 * Helps to calculates such options as java package, model name for the model creation procedure
 *
 * Created by apyshkin on 12/16/16.
 */
@Immutable
final class ParametersCalculator {
  @NotNull private final FileBasedModelRoot myModelRoot;

  public ParametersCalculator(@NotNull FileBasedModelRoot modelRoot) {
    myModelRoot = modelRoot;
  }

  @NotNull
  public ModelCreationOptions calculate(@NotNull IFile modelFile, @NotNull SourceRoot sourceRoot) {
    String modelName = new ModelNameCalculator(myModelRoot, sourceRoot, modelFile).calcModelFqName();
    return calculate(new SModelName(modelName));
  }

  @NotNull
  public ModelCreationOptions calculate() {
    return ModelCreationOptions.startBuilding()
                               .finishBuilding();
  }

  @NotNull
  public ModelCreationOptions calculate(@NotNull SModelName modelName) {
    return ModelCreationOptions.startBuilding()
                               .setModelName(modelName.getValue())
                               .finishBuilding();
  }
}
