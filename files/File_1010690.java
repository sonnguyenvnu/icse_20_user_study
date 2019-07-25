/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.vfs.iofs.jrt;

import jetbrains.mps.util.IFileUtil;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.FileSystem;
import jetbrains.mps.vfs.IFile;
import jetbrains.mps.vfs.IFileSystem;
import jetbrains.mps.vfs.QualifiedPath;
import jetbrains.mps.vfs.VFSManager;
import jetbrains.mps.vfs.util.PathAssert;
import jetbrains.mps.vfs.util.PathUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JrtIoFile implements IFile {
  private static final Logger LOG = LogManager.getLogger(JrtIoFile.class);

  //todo: not yet supported to be different from startup JDK, however, introducing support is a simple task since we have it here already
  private final String myJdkPath;
  private final String myModule;
  private final String myPathInModule;
  private final JrtIoFileSystem myFS;

  //must be used only by filesystems
  @Internal
  public JrtIoFile(
      @NotNull String jdkPath, @Nullable String module, @Nullable String pathInJDK, @NotNull JrtIoFileSystem fs) {
    myJdkPath = jdkPath;
    myModule = module;
    myPathInModule = pathInJDK;
    myFS = fs;
  }

  @NotNull
  @Override
  public FileSystem getFileSystem() {
    throw new UnsupportedOperationException("Switch to using IFileSystem instead of FileSystem. FileSystem is not JRT-capable");
  }

  @NotNull
  @Override
  public IFileSystem getFS() {
    return myFS;
  }

  @NotNull
  @Override
  public String getName() {
    String pathinJDK = getPathInJDK();
    int index = pathinJDK.lastIndexOf(IFileSystem.SEPARATOR);
    return index >= 0 ? pathinJDK.substring(index + 1) : pathinJDK;
  }

  @NotNull
  @Override
  public String getPath() {
    return myJdkPath + JrtIoFileSystem.JDK_PATH_SEPARATOR + getPathInJDK();
  }

  @Nullable
  @Override
  @Deprecated
  @ToRemove(version = 2019.1)
  public URL getUrl() {
    return null;
  }

  @Override
  public QualifiedPath getQualifiedPath() {
    return new QualifiedPath(VFSManager.JRT_FS, getPath());
  }

  @Override
  //todo [MM] check this. Initially made as in JarEntryFile, though it doesn't comply with doc
  public boolean isArchive() {
    return true;
  }

  @Override
  public boolean isInArchive() {
    return true;
  }

  @Override
  public IFile getBundleHome() {
    return null;
  }

  @Override
  public boolean createNewFile() {
    return false;
  }

  @Override
  public boolean mkdirs() {
    return false;
  }

  @Override
  public boolean delete() {
    return false;
  }

  @Override
  public boolean rename(@NotNull String newName) {
    return false;
  }

  @Override
  public boolean move(@NotNull IFile newParent) {
    return false;
  }

  @Override
  public OutputStream openOutputStream() {
    throw new UnsupportedOperationException("Can't write to JRT file");
  }

  @NotNull
  public String getPathInJDK() {
    //we may cache the result in case of performance issues
    StringBuilder sb = new StringBuilder(IFileSystem.SEPARATOR);
    if (myModule != null) {
      sb.append(myModule);
      if (myPathInModule != null) {
        sb.append(IFileSystem.SEPARATOR);
        sb.append(myPathInModule);
      }
    }
    return sb.toString();
  }

  @Override
  public boolean setTimeStamp(long time) {
    return false;
  }

  @Override
  public boolean isReadOnly() {
    return true;
  }

  @NotNull
  @Override
  public IFile getDescendant(@NotNull String suffix) {
    IFile result = IFileUtil.getDescendant(this, PathUtil.toSystemIndependent(suffix));
    if (result == null) {
      throw new IllegalStateException("Can't find descendant " + suffix + " of file " + getPath());
    }
    return result;
  }

  @NotNull
  @Override
  public IFile findChild(@NotNull String name) {
    new PathAssert(name).nonEmpty().noSeparators();
    String path = getPath();
    //the following is because there's one file that path ends with slash: JDK_MODE!/
    String fullPath = path.endsWith("!" + IFileSystem.SEPARATOR) ? path + name : path + IFileSystem.SEPARATOR + name;
    return myFS.getFile(fullPath);
  }

  @Nullable
  @Override
  public IFile getParent() {
    if (myPathInModule == null) {
      if (myModule == null) {
        return null;
      }
      return myFS.getFile(myJdkPath, null, null);
    }
    int index = myPathInModule.lastIndexOf(IFileSystem.SEPARATOR);
    if (index < 0) {
      return myFS.getFile(myJdkPath, myModule, null);
    }

    return myFS.getFile(myJdkPath, myModule, myPathInModule.substring(index));
  }

  //----------------- pure fs-delegating methods ----------------------

  @Override
  public boolean isDirectory() {
    return Files.isDirectory(getRealFile(this));
  }

  @Nullable
  @Override
  public List<IFile> getChildren() {
    if (!isDirectory()) {
      return Collections.emptyList();
    }

    ArrayList<IFile> result = new ArrayList<>();
    try (DirectoryStream<Path> ds = Files.newDirectoryStream(getRealFile(this))) {
      // expect DirectoryStream not to report entries like '.' or '..', otherwise findChild might fail
      ds.forEach(it -> result.add(findChild(it.getFileName().toString())));
      return result;
    } catch (IOException e) {
      LOG.error(e);
      return Collections.emptyList();
    }
  }

  @Override
  public long lastModified() {
    try {
      return Files.getLastModifiedTime(getRealFile(this)).toMillis();
    } catch (IOException e) {
      LOG.error(e);
      return 0;
    }
  }

  @Override
  public long length() {
    try {
      return Files.size(getRealFile(this));
    } catch (IOException e) {
      LOG.error(e);
      return 0;
    }
  }

  @Override
  public boolean exists() {
    return Files.exists(getRealFile(this));
  }

  @Override
  public InputStream openInputStream() throws IOException {
    return Files.newInputStream(getRealFile(this));
  }

  private static Path getRealFile(JrtIoFile file) {
    java.nio.file.FileSystem jrtfs = FileSystems.getFileSystem(URI.create("jrt:/"));
    return jrtfs.getPath("modules", file.getPathInJDK().split(IFileSystem.SEPARATOR));
  }
}
