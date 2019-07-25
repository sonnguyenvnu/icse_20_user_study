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
package jetbrains.mps.generator.impl.dependencies;

import jetbrains.mps.extapi.model.GeneratableSModel;
import jetbrains.mps.generator.GenerationParametersProvider;
import jetbrains.mps.vfs.IFile;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Evgeny Gryaznov, May 14, 2010
 */
public class GenerationDependencies {

  private static final int LEGACY_VERSION = 2; // "dependencies" root
  private static final int ACTUAL_VERSION = 3;

  private static final String ROOT = "product";
  private static final String ATTR_MODEL_HASH = "modelHash";
  private static final String ATTR_PARAMS_HASH = "parametersHash";
  private static final String ATTR_VERSION = "version";

  private final String myModelHash;
  private final String myParametersHash;

  // I don't expect too much distinct locations per model, generally, there'd be just the default one
  private final SortedArraySet<FilesEntry> myFiles = new SortedArraySet<>(2);

  public GenerationDependencies(@NotNull SModel model, @Nullable GenerationParametersProvider parametersProvider) {
    this(model instanceof GeneratableSModel ? ((GeneratableSModel) model).getModelHash() : null, parametersProvider == null ? null : parametersProvider.getParametersHash(model));
    // DefaultNonIncrementalStrategy used to get model hash for isGeneratable()==true models only, but I don't care that much - if we get to generate
    // a non-generatable model, why can't we use its model hash?
  }

  /*package*/ GenerationDependencies(String modelHash, String parametersHash) {
    this.myModelHash = modelHash;
    this.myParametersHash = parametersHash;
  }

  public String getModelHash() {
    return myModelHash;
  }

  public String getParametersHash() {
    return myParametersHash;
  }

  public List<GenerationRootDependencies> getRootDependencies() {
    // FIXME drop uses and remove
    return Collections.emptyList();
  }

  public void update(@Nullable String path, @NotNull String fileName) {
    myFiles.getOrAdd(new FilesEntry(path)).addFile(fileName);
  }

  public void reportGeneratedFiles(IFile rootDir, IFile modelDefaultDir, Consumer<IFile> generatedFiles) {
    for (FilesEntry fe : myFiles) {
      IFile loc;
      if (fe.getDir() == null) {
        loc = modelDefaultDir;
      } else {
        loc = rootDir.getDescendant(fe.getDir());
      }
      for (String fname : fe.getFiles()) {
        generatedFiles.accept(loc.getDescendant(fname));
      }
    }
  }

  public Element toXml() {
    Element root = new Element(ROOT);
    root.setAttribute(ATTR_VERSION, Integer.toString(ACTUAL_VERSION));
    if (myModelHash != null) {
      root.setAttribute(ATTR_MODEL_HASH, myModelHash);
    }
    if (myParametersHash != null) {
      root.setAttribute(ATTR_PARAMS_HASH, myParametersHash);
    }
    for (FilesEntry fe : myFiles) {
      Element filesEntry = new Element("files");
      if (fe.getDir() != null) {
        filesEntry.setAttribute("dir", fe.getDir());
      }
      filesEntry.setAttribute("names", fe.getFilesAsAttribute());
      root.addContent(filesEntry);
    }
    return root;
  }

  public static GenerationDependencies fromXml(Element root) {
    String version = GenerationRootDependencies.getValue(root, ATTR_VERSION);
    if (!Integer.toString(LEGACY_VERSION).equals(version) && !Integer.toString(ACTUAL_VERSION).equals(version)) {
      /* regenerate all */
      return null;
    }
    // for older version, don't read any legacy data except actually recognized
    String modelHash = GenerationRootDependencies.getValue(root, ATTR_MODEL_HASH);
    String paramsHash = GenerationRootDependencies.getValue(root, ATTR_PARAMS_HASH);
    final GenerationDependencies rv = new GenerationDependencies(modelHash, paramsHash);
    if (Integer.toString(ACTUAL_VERSION).equals(version)) {
      for (Element child : root.getChildren("files")) {
        final String dir = child.getAttributeValue("dir");
        final String files = child.getAttributeValue("names");
        rv.myFiles.add(new FilesEntry(dir, files));
      }
    }
    return rv;
  }
}
