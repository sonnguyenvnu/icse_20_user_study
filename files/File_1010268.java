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

import jetbrains.mps.vfs.path.Path;
import jetbrains.mps.extapi.module.EditableSModule;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.project.MementoWithFS;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.openapi.FileSystem;
import jetbrains.mps.vfs.IFile;
import jetbrains.mps.vfs.refresh.CachingFileSystem;
import jetbrains.mps.vfs.refresh.FileSystemEvent;
import jetbrains.mps.vfs.refresh.FileSystemListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.annotations.Internal;
import org.jetbrains.mps.openapi.persistence.Memento;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static jetbrains.mps.util.FileUtil.getAbsolutePath;
import static jetbrains.mps.util.FileUtil.getUnixPath;

/**
 * <code>FileBasedModelRoot</code> contains several {@link SourceRoot} which contain models.
 * The source roots might be marked with a {@link SourceRootKind} which determine how do we treat the model files/folders
 * we discover under those source roots.
 * <p>
 * The class is in the state of migration from <code>String</code> source roots to the interface {@link SourceRoot}
 * that is why it is such a mess.
 * <p>
 * Paths represented by string either must have a clear contract (absolute, relative) or (better)
 * replaced with some <code>Path</code> entities.
 * AP
 *
 * @author evgeny, 12/11/12
 * apyshkin, 15/12/16
 */
public abstract class FileBasedModelRoot extends ModelRootBase implements FileSystemListener {
  /**
   * @deprecated use {@link SourceRootKinds#SOURCES} instead
   */
  @Deprecated
  public static final String SOURCE_ROOTS = "sourceRoot";

  /**
   * @deprecated use {@link SourceRootKinds#EXCLUDED} instead
   */
  @Deprecated
  public static final String EXCLUDED = "excluded";

  private /*final*/ FileSystem myFileSystem = jetbrains.mps.vfs.FileSystem.getInstance(); // TODO not read from memento

  /**
   * This is a private model root persistence notation, ought to be concealed from the general public
   */
  @Internal
  public static final String CONTENT_PATH = "contentPath"; // TODO: 12/20/16 lower visibility
  @Internal
  public static final String LOCATION = "location"; // TODO: 12/20/16 lower visibility
  private static final String PATH = "path";

  @ToRemove(version = 3.5)
  @Deprecated
  @Nullable
  @Immutable
  private final List<String> mySupportedFileKinds; // null <=> default constructor is used

  /**
   * Ancestor for all the source paths
   * Commonly it is a module root folder and 'models' directory is its default source root
   *
   * @see SourceRoot
   */
  private IFile myContentDir; // might be null when just created

  private final SourcePaths mySourcePathStorage;
  private final List<PathListener> myListeners = new ArrayList<>();

  protected FileBasedModelRoot() {
    mySupportedFileKinds = null;
    mySourcePathStorage = new SourcePaths();
  }

  /**
   * @deprecated use {@link #FileBasedModelRoot()} instead and
   * define your own {@link #getSupportedFileKinds1()}
   */
  @Deprecated
  @ToRemove(version = 3.5)
  protected FileBasedModelRoot(String[] supportedFileKinds) {
    mySupportedFileKinds = supportedFileKinds != null ?
                           unmodifiableList(asList(supportedFileKinds)) : emptyList();
    mySourcePathStorage = new SourcePaths((sourceRootKind) -> getSupportedFileKinds1().contains(sourceRootKind));
  }

  /**
   * @deprecated use {@link #getContentDirectory()} instead
   */
  @Deprecated
  @Nullable
  public final String getContentRoot() {
    return myContentDir != null ? myContentDir.getPath() : null;
  }

  /**
   * @deprecated use {@link #setContentDirectory(IFile)} instead
   */
  @Deprecated
  public final void setContentRoot(@NotNull String path) {
    checkNotRegistered();

    myContentDir = myFileSystem.getFile(path);
  }

  @Nullable
  public final IFile getContentDirectory() {
    return myContentDir;
  }

  public final void setContentDirectory(@NotNull IFile contentDir) {
    checkNotRegistered();
    myContentDir = contentDir;
  }

  @NotNull
  public final FileSystem getFileSystem() {
    return myFileSystem;
  }

  /**
   * To become abstract in the 3.5
   */
  @NotNull
  @Immutable
  public /*abstract*/ List<SourceRootKind> getSupportedFileKinds1() {
    return unmodifiableList(asList(SourceRootKinds.values()));
  }

  /**
   * @return <code>SourceRoot</code>s of the specified kind
   * They might contain relative paths (unlike the legacy counterpart method!!).
   * FBModelRoot is going to store relative path, all we need is
   * some api to provide relative path instances.
   * Now we do not have such abstraction since <code>IFile</code>
   * is effectively absolute (just since the idea's <code>VirtualFile</code> is absolute as well).
   * <p>
   * AP
   */
  @NotNull
  @Immutable
  public final List<SourceRoot> getSourceRoots(@NotNull SourceRootKind kind) {
    assertCanRead();
    return mySourcePathStorage.getByKind(kind);
  }

  public final void addSourceRoot(@NotNull SourceRootKind kind, @NotNull SourceRoot root) {
    assertCanChange();
    mySourcePathStorage.addSourceRoot(kind, root);
    if (getModule() instanceof EditableSModule) {
      ((EditableSModule) getModule()).setChanged();
    }
  }

  /**
   * @return the <code>FileKind</code> of the removed <code>SourceRoot</code> if it was successfully removed.
   */
  @Nullable
  public final SourceRootKind removeSourceRoot(@NotNull SourceRoot root) {
    assertCanChange();
    final SourceRootKind rv = mySourcePathStorage.removeSourceRoot(root);
    if (rv != null && getModule() instanceof EditableSModule) {
      ((EditableSModule) getModule()).setChanged();
    }
    return rv;
  }

  /**
   * helper method (for legacy resolve)
   */
  @NotNull
  private SourceRootKind resolveKindByName(@NotNull String kindName) {
    for (SourceRootKind kind : getSupportedFileKinds1()) {
      if (kind.getName().equals(kindName)) {
        return kind;
      }
    }
    throw new FileKindIsNotAllowedException(kindName);
  }

  /**
   * @see SourcePaths
   * @deprecated <code>String</code> is not the best choice. Consider using {@link #getSupportedFileKinds1()}
   */
  @Deprecated
  @Immutable
  public final Collection<String> getSupportedFileKinds() {
    List<String> legacyFileKinds = mySupportedFileKinds;
    if (legacyFileKinds != null) {
      return legacyFileKinds;
    } else {
      return getSupportedFileKinds1().stream()
                                     .map(SourceRootKind::getName)
                                     .collect(Collectors.toList());
    }
  }

  /**
   * @deprecated use {@link #getSourceRoots(SourceRootKind)} instead
   */
  @NotNull
  @Deprecated
  public final Collection<String> getFiles(@NotNull String kind) {
    List<SourceRoot> roots = getSourceRoots(resolveKindByName(kind));
    return unmodifiableList(roots.stream()
                                 .map(SourceRoot::getAbsolutePath) // unfortunately I am sure that plenty clients rely on the absolute path here.
                                 .map(IFile::getPath)
                                 .collect(Collectors.toList()));
  }

  /**
   * @deprecated use {@link #getSourceRoots(SourceRootKind)} + {@link List#contains}.
   */
  @Deprecated
  @ToRemove(version = 0)
  public final boolean containsFile(String kind, String file) {
    Collection<String> sourceRoots = getFiles(kind);
    return sourceRoots.contains(file);
  }

  /**
   * @deprecated use {@link #addSourceRoot(SourceRootKind, SourceRoot)} instead
   */
  @Deprecated
  @ToRemove(version = 3.5)
  public final void addFile(String kind, String filePath) {
    SourceRootKind sourceRootKind = resolveKindByName(kind);
    addSourceRoot(sourceRootKind, new DefaultSourceRoot(filePath, myContentDir));
  }

  /**
   * @deprecated use {@link #removeSourceRoot(SourceRoot)} instead
   */
  @Deprecated
  @ToRemove(version = 3.5)
  public final void deleteFile(String kind, String file) {
    checkNotRegistered();

    if (!containsFile(kind, file)) {
      throw new FileKindIsNotAllowedException(kind, file);
    }


    removeSourceRoot(new DefaultSourceRoot(file, myContentDir));
  }

  @Override
  public final String getPresentation() {
    IFile contentDirectory = getContentDirectory();
    return contentDirectory == null ? "no content dir" : contentDirectory.getPath();
  }

  @Override
  public void save(@NotNull Memento memento) {
    if (myContentDir != null) {
      memento.put(CONTENT_PATH, myContentDir.getPath());
    }
    memento.put("type", getType());
    for (SourceRootKind kind : getSupportedFileKinds1()) {
      for (SourceRoot root : getSourceRoots(kind)) {
        Memento modelRootMemento = memento.createChild(kind.getName());
        String contentRootPath = root.getAbsolutePath().getPath(); // must go away as soon as we allow relative paths
        if (FileUtil.isAncestor(myContentDir.getPath(), contentRootPath)) {
          String relPath = relativize(contentRootPath, myContentDir);
          if (relPath.isEmpty()) {
            relPath = MPSExtentions.DOT;
          }
          modelRootMemento.put(LOCATION, relPath);
        } else {
          modelRootMemento.put(PATH, contentRootPath);
        }
      }
    }
  }

  @Override
  public void load(@NotNull Memento memento) {
    checkNotRegistered();

    mySourcePathStorage.clearAll(); // AP: I'd rather force a single invocation of the #load method

    if (memento instanceof MementoWithFS) {
      myFileSystem = ((MementoWithFS) memento).getFileSystem();
    }
    String path = memento.get(CONTENT_PATH);
    myContentDir = (path != null) ? myFileSystem.getFile(path) : null;
    for (SourceRootKind kind : getSupportedFileKinds1()) {
      for (Memento root : memento.getChildren(kind.getName())) {
        String relPath = root.get(LOCATION);
        if (relPath != null) {
          addSourceRoot(kind, new DefaultSourceRoot(relPath, myContentDir)); // relative
        } else {
          addSourceRoot(kind, new DefaultSourceRoot(root.get(PATH), myContentDir)); // absolute
        }
      }
    }
  }

  @Override
  public void attach() {
    super.attach();
    attachPathListenerForEachSourceRoot();
  }

  private void attachPathListenerForEachSourceRoot() { // fixme extract class
    getSupportedFileKinds1().stream().filter(kind -> !kind.isExcluded()).forEach(kind -> {
      for (SourceRoot sourceRoot : getSourceRoots(kind)) {
        IFile file = sourceRoot.getAbsolutePath();
        PathListener listener = new PathListener(file);
        myListeners.add(listener);
        if (myFileSystem instanceof CachingFileSystem) {
          ((CachingFileSystem) myFileSystem).addListener(listener);
        }
      }
    });
  }

  @Override
  public void dispose() {
    if (myFileSystem instanceof CachingFileSystem) {
      for (PathListener listener : myListeners) {
        ((CachingFileSystem) myFileSystem).removeListener(listener);
      }
    }
    myListeners.clear();
    super.dispose();
  }

  @Override
  public final IFile getFileToListen() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void update(ProgressMonitor monitor, @NotNull FileSystemEvent event) {
    if (!isRegistered()) {
      // XXX not sure there's any reason to update MR if it's not part of any accessible model structure
      return;
    }
    if (!event.getCreated().isEmpty() || !event.getRemoved().isEmpty()) {
      assert isRegistered();
      // indeed, it's not nice to have distinct model writes for each model root, still it's better
      // than global model write in a reload manager (which is not even FS aware, let alone FS-Model relation aware.
      getRepository().getModelAccess().runWriteAction(this::update);
    }
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FileBasedModelRoot that = (FileBasedModelRoot) o;

    return Objects.equals(myContentDir, that.myContentDir)
           && Objects.equals(mySourcePathStorage, that.mySourcePathStorage)
           && Objects.equals(myFileSystem, that.myFileSystem);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(myContentDir, mySourcePathStorage);
  }


  @NotNull
  public static String relativize(@NotNull String fullPath, @NotNull IFile contentHome) {
    return relativize(fullPath, contentHome.getPath());
  }

  @NotNull
  public static String relativize(@NotNull String fullPath, @NotNull String contentHomePath) {
    contentHomePath = independentAndAbsolute(contentHomePath);
    fullPath = independentAndAbsolute(fullPath);
    if (fullPath.equals(contentHomePath)) {
      return MPSExtentions.DOT;
    }
    return FileUtil.getRelativePath(fullPath, contentHomePath, Path.UNIX_SEPARATOR);
  }

  @NotNull
  private static String independentAndAbsolute(@NotNull String path) {
    return getUnixPath(getAbsolutePath(path));
  }

  private final class PathListener implements FileSystemListener {
    private final IFile myPath;

    private PathListener(@NotNull IFile path) {
      myPath = path;
    }

    @NotNull
    @Override
    public IFile getFileToListen() {
      return myPath;
    }

    @Override
    public void update(ProgressMonitor monitor, @NotNull FileSystemEvent event) {
      event.notify(FileBasedModelRoot.this);
    }

    @Override
    public String toString() {
      return "[PathListener: path: " + myPath + "; modelRoot: " + FileBasedModelRoot.this + "]";
    }
  }
}
