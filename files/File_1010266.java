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
package jetbrains.mps.extapi.persistence;

import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.IFile;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Default source root which lives until the <code>Path</code> api appears
 *
 * @author apyshkin
 * @since 3.5
 */
@ToRemove(version = 3.6)
public final class DefaultSourceRoot implements SourceRoot {
  private final String myPath;
  private final IFile myAbsolutePath;

  public DefaultSourceRoot(@NotNull String path, @NotNull IFile contentRootDirectory) {
    myPath = canonicalize(path);
    if (FileUtil.isAbsolute(myPath)) {
      myAbsolutePath = contentRootDirectory.getFileSystem().getFile(myPath);
    } else {
      myAbsolutePath = contentRootDirectory.getDescendant(myPath);
    }
  }

  @NotNull
  private String canonicalize(@NotNull String path) {
    path = FileUtil.stripLastSlashes(FileUtil.getUnixPath(path));
    if (path.equals(MPSExtentions.DOT)) {
      path = "";
    }
    return path;
  }

  @NotNull
  @Override
  public IFile getAbsolutePath() {
    return myAbsolutePath;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(myPath);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DefaultSourceRoot) {
      return Objects.equals(myPath, ((DefaultSourceRoot) obj).getPath());
    }
    return false;
  }

  @NotNull
  @Override
  public String getPath() {
    return myPath;
  }

  @Override
  public String toString() {
    return "Path [" + getAbsolutePath() + "]";
  }
}
