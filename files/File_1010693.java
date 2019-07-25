/*
 *
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.vfs.refresh;

import jetbrains.mps.util.IFileUtil;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility to perform recursive refresh.
 * Used to be part of ModulesMiner, but is not specific to it, hence was extracted.
 * @author Artem Tikhomirov
 */
public final class FileRefresh implements Runnable {
  private final static Logger LOG = LogManager.getLogger(FileRefresh.class);

  @NotNull private final List<IFile> myFiles;
  private final DefaultCachingContext myDefaultCachingContext = new DefaultCachingContext(true, false);

  public FileRefresh(@NotNull IFile file) {
    this(Collections.singletonList(file));
  }

  public FileRefresh(@NotNull List<IFile> list) {
    myFiles = list;
  }

  @Override
  public void run() {
    LOG.info("Refreshing " + myFiles.size() + " file(s)");
    refreshRecursivelyIntoJars(myFiles);
    LOG.info("Refreshing is done");
  }

  // not allowing nulls
  private void refreshRecursivelyIntoJars(List<IFile> files) {
    List<CachingFile> cachingFiles = files.stream().
        filter(file -> file instanceof CachingFile).
        map(file -> (CachingFile) file).distinct().collect(Collectors.toList());
    if (!cachingFiles.isEmpty()) {
      CachingFile firstFile = cachingFiles.get(0);
      CachingFileSystem fs = firstFile.getFileSystem();
      while (!cachingFiles.isEmpty()) {
        fs.refresh(myDefaultCachingContext, cachingFiles);
        cachingFiles = cachingFiles.stream().flatMap(
            file -> {
              if (!file.isInArchive() && file.isDirectory()) {
                List<IFile> children = file.getChildren();
                return children != null ? children.stream().map(iFile -> (CachingFile) iFile) : Stream.empty();
              } else if (IFileUtil.isJarFile(file)) {
                return Stream.of(((CachingFile) IFileUtil.stepIntoJar(file)));
              }
              return Stream.empty();
            }).distinct().collect(Collectors.toList());
      }
    }
  }
}
