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
package jetbrains.mps.vfs.iofs.jar;

import gnu.trove.THashMap;
import gnu.trove.THashSet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * This class represents a jar file abstraction
 * It stores cache with all subdirectories and all entries
 */
class JarFileData extends AbstractJarFileData {
  private static Logger LOG = LogManager.getLogger(JarFileData.class);

  private final Object myLock = new Object();
  private boolean isInitialized = false;
  private final ZipFileContainer myZipFileContainer = new ZipFileContainer(); // cleared up in the JarFileDataCache#removeGCedReferences
  private final Map<String, Set<String>> myFiles = new THashMap<>();
  private final Map<String, Set<String>> mySubDirectories = new THashMap<>();
  private final Map<String, ZipEntry> myEntries = new THashMap<>();

  JarFileData(File file) {
    super(file);
  }

  ZipFileContainer getZipFileContainer() {
    return myZipFileContainer;
  }

  @Override
  Set<String> getFiles(String dir) {
    ensureInitialized();

    return Collections.unmodifiableSet(myFiles.get(dir));
  }

  @Override
  Set<String> getSubdirectories(String dir) {
    ensureInitialized();

    return Collections.unmodifiableSet(mySubDirectories.get(dir));
  }

  @Override
  boolean exists(String path) {
    ensureInitialized();

    return (myEntries.get(path) != null) || (mySubDirectories.get(path) != null);
  }

  @Override
  boolean isDirectory(String path) {
    ensureInitialized();

    if (myEntries.get(path) != null) {
      return myEntries.get(path).isDirectory();
    }

    return myFiles.get(path) != null || mySubDirectories.get(path) != null;
  }

  @Override
  String getParentDirectory(String dir) {
    int lastSlash = dir.lastIndexOf('/');
    if (lastSlash == -1) {
      return "";
    }
    return dir.substring(0, lastSlash);
  }

  private Set<String> getDirectoriesFor(String dir) {
    mySubDirectories.putIfAbsent(dir, new THashSet<>());
    return mySubDirectories.get(dir);
  }

  private Set<String> getFilesFor(String dir) {
    myFiles.putIfAbsent(dir, new THashSet<>());
    return myFiles.get(dir);
  }

  @Override
  InputStream openStream(String path) throws IOException {
    ensureInitialized();

    ZipEntry entry = myEntries.get(path);
    return new MyInputStream(entry);
  }

  @Override
  long getLength(String path) {
    ensureInitialized();

    return myEntries.get(path).getSize();
  }

  private void ensureInitialized() {
    synchronized (myLock) {
      if (isInitialized) {
        return;
      }

      isInitialized = true;
      try {
        myZipFileContainer.zipFile = new ZipFile(getFile());
        Enumeration<? extends ZipEntry> entries = myZipFileContainer.zipFile.entries();

        while (entries.hasMoreElements()) {
          ZipEntry entry = entries.nextElement();
          if (entry.isDirectory()) {
            String name = entry.getName();
            while (name.endsWith("/")) {
              name = name.substring(0, name.length() - 1);
            }
            if (".".equals(name)) {
              // yes, I've faced jar files with "./" entry, and yes, it causes no good
              // jar tvf code/languages/com.mbeddr.mpsutil.inca/code/solutions/com.mbeddr.mpsutil.soot.runtime/lib/soot-trunk.jar | fgrep "./"
              // 0 Thu Jul 12 11:23:24 CEST 2012 ./
              // We faced dramatic slowdown in JavaClassStubsModelRoot, which goes ././././... over and over again (multiply number of folders in the jar).
              continue;
            }

            buildDirectoryCaches(name);
          } else {
            final String name = entry.getName();

            final int packEnd = name.lastIndexOf('/');
            final String dir;
            final String fileName;
            if (packEnd == -1) {
              dir = "";
              fileName = name;
            } else {
              // FIXME packEnd == 0 means name == "/something", and myEntries eventually maps "something/something". Is it right?
              // Either null instead of name or assert packEnd > 0
              dir = packEnd > 0 ? name.substring(0, packEnd) : name;
              fileName = name.substring(packEnd + 1);
            }

            buildDirectoryCaches(dir);
            getFilesFor(dir).add(fileName);

            // XXX seems that could use name
            if (dir.length() > 0) {
              myEntries.put(dir + '/' + fileName, entry);
            } else {
              myEntries.put(fileName, entry);
            }
          }
        }
      } catch (IOException e) {
        LOG.error(String.format("Bad jar '%s'", getFile()), e);
      }
    }
  }

  private void buildDirectoryCaches(String dir) {
    String parent = getParentDirectory(dir);

    getDirectoriesFor(dir);
    getFilesFor(dir);

    if (parent.equals(dir)) {
      return;
    }
    getDirectoriesFor(parent).add(dir);
    buildDirectoryCaches(parent);
  }

  // Let's be paranoid and have it non-static, because when the enclosing JarFileData is garbage collected
  // its ZipFile will be closed (see JarFileDataCache)
  // And this way the instance of this class will retain the enclosing instance from becoming garbage
  private class MyInputStream extends InputStream {
    private InputStream stream;

    public MyInputStream(ZipEntry entry) throws IOException {
      stream = myZipFileContainer.zipFile.getInputStream(entry);
    }

    @Override
    public int read() throws IOException {
      return stream.read();
    }

    @Override
    public int read(@NotNull byte[] b) throws IOException {
      return stream.read(b);
    }

    @Override
    public int read(@NotNull byte[] b, int off, int len) throws IOException {
      return stream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
      return stream.skip(n);
    }

    @Override
    public int available() throws IOException {
      return stream.available();
    }

    @Override
    public void close() throws IOException {
      try {
        super.close();
      } finally {
        stream.close();
      }
    }

    @Override
    public void mark(int readLimit) {
      stream.mark(readLimit);
    }

    @Override
    public void reset() throws IOException {
      stream.reset();
    }

    @Override
    public boolean markSupported() {
      return stream.markSupported();
    }
  }
}
