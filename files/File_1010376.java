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

import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.project.facets.JavaModuleFacet;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.persistence.Memento;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * A simple java facet provided with two locations of source_gen and classes_gen
 *
 * Created by apyshkin on 12/7/17.
 */
public final class NaiveJavaModuleFacet implements JavaModuleFacet {
  private final static Logger LOG = LogManager.getLogger(TempModule.class);

  @NotNull
  private final AbstractModule myOwningModule;
  private final IFile mySourceGen;
  private final IFile myClassesGen;

  @NotNull
  @Override
  public String getFacetType() {
    return FACET_TYPE;
  }

  public NaiveJavaModuleFacet(@NotNull AbstractModule owningModule, @Nullable String sourceGen, @NotNull String classesGen) {
    myOwningModule = owningModule;
    mySourceGen = sourceGen == null ? null : createTempDirectory("TempModule_source_gen");
    myClassesGen = createTempDirectory("TempModule_classes_gen");
  }

  @Override
  public boolean isCompileInMps() {
    return true;
  }

  @Nullable
  @Override
  public IFile getOutputRoot() {
    return mySourceGen;
  }

  @NotNull
  @Override
  public IFile getClassesGen() {
    return myClassesGen;
  }

  @Override
  public Set<String> getLibraryClassPath() {
    return Collections.emptySet();
  }

  @Override
  public Set<String> getClassPath() {
    return Collections.singleton(getClassesGen().getPath());
  }

  @Override
  public Set<String> getAdditionalSourcePaths() {
    return Collections.emptySet();
  }

  @NotNull
  @Override
  public SModule getModule() {
    return myOwningModule;
  }

  @Override
  public void save(Memento memento) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void load(Memento memento) {
    throw new UnsupportedOperationException();
  }

  private IFile createTempDirectory(@NotNull String prefix) {
    try {
      final File temp;

      temp = File.createTempFile(prefix, "");

      if (!(temp.delete())) {
        throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
      }

      if (!(temp.mkdir())) {
        throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
      }

      return myOwningModule.getFileSystem().getFile(temp.getAbsolutePath());
    } catch (IOException e) {
      LOG.error(e.toString(), e);
      return null;
    }
  }
}
