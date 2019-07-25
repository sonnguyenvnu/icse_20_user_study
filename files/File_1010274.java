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

import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.FileSystem;
import jetbrains.mps.vfs.refresh.CachingFileSystem;
import jetbrains.mps.vfs.refresh.FileSystemEvent;
import jetbrains.mps.vfs.refresh.FileSystemListener;
import jetbrains.mps.vfs.IFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.persistence.Memento;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

/**
 * @deprecated use {@link FileBasedModelRoot} instead
 *             There are few instanceof checks with this class in MPS, but no subclasses for quite some time now.
 *             The class will be removed once corresponding code is wiped out and 2018.3 is out
 * evgeny, 11/9/12
 */
@Deprecated
@ToRemove(version = 2018.3)
public abstract class FolderModelRootBase extends ModelRootBase implements FileSystemListener {
  private String path;

  protected FolderModelRootBase() {
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    checkNotRegistered();

    this.path = path;
  }

  @Override
  public String getPresentation() {
    return (getPath() != null ? getPath() : "no path");
  }

  @Override
  public void save(@NotNull Memento memento) {
    memento.put("path", path);
  }

  @Override
  public void load(@NotNull Memento memento) {
    checkNotRegistered();

    path = memento.get("path");
  }

  @Override
  public void attach() {
    super.attach();
    SModule module = getModule();
    if (module instanceof AbstractModule) {
      jetbrains.mps.vfs.openapi.FileSystem fs = ((AbstractModule) module).getFileSystem();
      if (fs instanceof CachingFileSystem) {
        ((CachingFileSystem) fs).addListener(this);
      }
    }
  }

  @Override
  public void dispose() {
    SModule module = getModule();
    if (module instanceof AbstractModule) {
      jetbrains.mps.vfs.openapi.FileSystem fs = ((AbstractModule) module).getFileSystem();
      if (fs instanceof CachingFileSystem) {
        ((CachingFileSystem) fs).removeListener(this);
      }
    }
    super.dispose();
  }

  @NotNull
  @Override
  public IFile getFileToListen() {
    return FileSystem.getInstance().getFile(getPath());
  }

  @Override
  public void update(ProgressMonitor monitor, @NotNull FileSystemEvent event) {
    update();
  }

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);
}
