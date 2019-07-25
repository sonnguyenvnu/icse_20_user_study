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

import jetbrains.mps.vfs.openapi.FileSystem;
import jetbrains.mps.vfs.refresh.CachingFileSystem;
import jetbrains.mps.vfs.refresh.FileSystemEvent;
import jetbrains.mps.vfs.refresh.FileSystemListener;
import jetbrains.mps.vfs.IFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.persistence.ModelRoot;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

/**
 * This data source allows to track a backup file along with the main file.
 * The name of the backup file is formed by adding a tilde (~) to the name of the original file.
 * The backup file stores the same data, but in a generic and reliable format. It becomes the main
 * storage when content doesn't fit into the original file because of the format restrictions.
 */
public class FileWithBackupDataSource extends FileDataSource {
  private BackupFileListener myBackupFileListener;

  @Deprecated
  public FileWithBackupDataSource(@NotNull IFile file, ModelRoot modelRoot) {
    super(file, modelRoot);
  }

  public FileWithBackupDataSource(@NotNull IFile file) {
    super(file);
  }

  @NotNull
  public IFile getBackupFile() {
    IFile file = getFile();
    return file.getFileSystem().getFile(file.getPath() + "~");
  }

  @Override
  public long getTimestamp() {
    IFile backupFile = getBackupFile();
    long backupTimestamp = backupFile.exists() ? backupFile.lastModified() : -1L;
    return Math.max(super.getTimestamp(), backupTimestamp);
  }

  @Override
  public void refresh() {
    super.refresh();
    getBackupFile().refresh();
  }

  @Override
  public void update(ProgressMonitor monitor, @NotNull FileSystemEvent event) {
    IFile mainFile = getFile();
    IFile backupFile = getBackupFile();
    boolean isChanged = false;

    for (IFile file : event.getChanged()) {
      if (file.equals(mainFile) || file.equals(backupFile)) {
        isChanged = true;
        break;
      }
    }
    for (IFile file : event.getRemoved()) {
      // main file deletion is handled by model roots
      if (file.equals(backupFile)) {
        isChanged = true;
        break;
      }
    }
    if (isChanged) {
      fireChanged(monitor);
    }
  }

  @Override
  protected void startListening() {
    super.startListening();
    IFile backupFile = getBackupFile();
    myBackupFileListener = new BackupFileListener(backupFile);
    FileSystem fs = backupFile.getFileSystem();
    if (fs instanceof CachingFileSystem){
      ((CachingFileSystem) fs).addListener(myBackupFileListener);
    }
  }

  @Override
  protected void stopListening() {
    FileSystem fs = getBackupFile().getFileSystem();
    if (fs instanceof CachingFileSystem) {
      ((CachingFileSystem) fs).removeListener(myBackupFileListener);
    }
    myBackupFileListener = null;
    super.stopListening();
  }

  private class BackupFileListener implements FileSystemListener {
    private IFile path;

    private BackupFileListener(@NotNull IFile path) {
      this.path = path;
    }

    @NotNull
    @Override
    public IFile getFileToListen() {
      return path;
    }

    @Override
    public void update(ProgressMonitor monitor, @NotNull FileSystemEvent event) {
      event.notify(FileWithBackupDataSource.this);
    }
  }

  public static FileWithBackupDataSource create(FileDataSource source) {
    return new FileWithBackupDataSource(source.getFile());
  }
}
