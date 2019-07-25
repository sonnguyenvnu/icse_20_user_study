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

import jetbrains.mps.extapi.persistence.SourceRoot;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.util.StringUtil;
import jetbrains.mps.vfs.IFile;
import jetbrains.mps.vfs.path.Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.module.SModule;

/**
 * Calculates model file from model name, enclosing source root and module and
 * the extension of the model file
 *
 * @see MPSExtentions#DOT_MODEL_HEADER
 * @see MPSExtentions#DOT_MODEL
 */
@Immutable
public final class ModelFileCalculator {
  @NotNull private final SModelName myModelName;
  @NotNull private final SourceRoot mySourceRoot;
  @NotNull private final String myFileExtension;
  private final boolean myDirectoryBased;

  /**
   * @param directoryBased -- format is directory-based, if true the file must look like
   *                       sourceRoot/[model-name-dir]/.[modelFileExtension]
   *                       otherwise
   *                       sourceRoot/[model-name + DOT + modelFileExtension] is fine
   */
  ModelFileCalculator(@NotNull SModelName modelName,
                      @NotNull SourceRoot sourceRoot,
                      @NotNull String modelFileExtension,
                      boolean directoryBased) {
    myModelName = modelName;
    mySourceRoot = sourceRoot;
    myFileExtension = modelFileExtension;
    myDirectoryBased = directoryBased;
  }

  ModelFileCalculator(@NotNull SModelName modelName,
                      @NotNull SourceRoot sourceRoot,
                      @NotNull String modelFileExtension) {
    this(modelName, sourceRoot, modelFileExtension, false);
  }

  @NotNull
  public IFile calculate() {
    String fileName = calcFileName();
    IFile sourceRootFile = mySourceRoot.getAbsolutePath();
    return sourceRootFile.getDescendant(fileName);
  }

  @NotNull
  private String calcFileName() {
    String modelFileName = myModelName.getValue();
    if (myDirectoryBased) {
      modelFileName = modelFileName + Path.UNIX_SEPARATOR;
    }
    return modelFileName + MPSExtentions.DOT + getFileExtension();
  }

  private String getFileExtension() {
    return StringUtil.emptyIfNull(myFileExtension);
  }

//  @NotNull
//  private String cutRedundantModuleFqNamePrefix(String filenameSuffix, String moduleFqName) {
//    if (filenameSuffix.startsWith(moduleFqName + MPSExtentions.DOT)) {
//      filenameSuffix = filenameSuffix.substring(moduleFqName.length() + 1);
//    }
//    return filenameSuffix;
//  }
}
