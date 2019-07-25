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
package jetbrains.mps.reloading;

import gnu.trove.THashSet;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.util.ConditionalIterable;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.util.InternUtil;
import jetbrains.mps.util.JavaNameUtil;
import jetbrains.mps.util.ReadUtil;
import jetbrains.mps.vfs.FileSystem;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.util.Condition;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarFileClassPathItem extends RealClassPathItem {
  private static final Logger LOG = LogManager.getLogger(JarFileClassPathItem.class);

  //computed during init
  private boolean myIsInitialized = false;
  private String myPrefix;
  private File myFile;

  private final MyCache myCache = new MyCache();
  private final String myPath;

  JarFileClassPathItem(FileSystem fileSystem, String path) {
    myPath = path;
    if (path.endsWith("!/")) {
      path = path.substring(0, path.length() - 2);
    }
    try {
      myFile = transformFile(fileSystem.getFile(path));
      myPrefix = "jar:" + myFile.toURI().toURL() + "!/";
    } catch (IOException e) {
      LOG.error("invalid class path: " + path, e);
    }
  }

  @Override
  public String getPath() {
    return myPath;
  }

  public String getAbsolutePath() {
    return myFile.getAbsolutePath();
  }

  public File getFile() {
    return myFile;
  }

  @Override
  public boolean hasClass(String qualifiedClassName) {
    ensureInitialized();
    final int ix = qualifiedClassName.lastIndexOf('.');
    String packageName = ix == -1 ? "" : qualifiedClassName.substring(0, ix);
    String className = qualifiedClassName.substring(ix + 1);
    return myCache.hasClass(packageName, className);
  }

  @Override
  public boolean hasPackage(@NotNull String packageName) {
    ensureInitialized();
    return myCache.hasPackage(packageName);
  }

  @Override
  public synchronized ClassBytes getClassBytes(String qualifiedClassName) {
    ensureInitialized();
    InputStream inp = null;
    ZipFile zf = null;
    if (!myFile.exists()) {
      LOG.warn("Classbytes file '" + myFile + "' does not exist");
      return null;
    }
    try {
      zf = new ZipFile(myFile);
      String entryName = toClassEntry(qualifiedClassName);
      ZipEntry entry = zf.getEntry(entryName);
      if (entry == null) {
        return null;
      }
      inp = zf.getInputStream(entry);
      if (inp == null) {
        return null;
      }
      // safe to assume int as class files have size limit way lower than 2^31
      byte[] bytes = ReadUtil.read(inp, (int) entry.getSize());
      return bytes == null ? null : new DefaultClassBytes(bytes, myFile.toURI().toURL());
    } catch (IOException e) {
      LOG.error(getClass().getName(), e);
      return null;
    } finally {
      FileUtil.closeFileSafe(inp);
      closeZipFile(zf);
    }
  }

  private static String toClassEntry(String classQualifiedName) {
    StringBuilder sb = new StringBuilder(classQualifiedName);
    for (int i = 0; i < classQualifiedName.length(); i++) {
      if (sb.charAt(i) == '.') {
        sb.setCharAt(i, '/');
      }
    }
    sb.append(MPSExtentions.DOT_CLASSFILE);
    return sb.toString();
  }

  private static void closeZipFile(ZipFile zf) {
    if (zf != null) {
      try {
        zf.close();
      } catch (IOException e) {
        LOG.error(JarFileClassPathItem.class.getName(), e);
      }
    }
  }

  @Override
  public URL getResource(String name) {
    ZipFile zf = null;
    try {
      zf = new ZipFile(myFile);
      if (zf.getEntry(name) == null) return null;
      return new URL(myPrefix + name);
    } catch (IOException e) {
      LOG.error(String.format("Failed to get resource '%s'", name), e);
      return null;
    } finally {
      FileUtil.closeFileSafe(zf);
    }
  }

  @Override
  public synchronized Iterable<String> getAvailableClasses(String namespace) {
    ensureInitialized();
    Collection<String> start = myCache.getClassesSetFor(namespace);
    Condition<String> cond = className -> !JavaNameUtil.isAnonymous(className);
    return new ConditionalIterable<>(start, cond);
  }

  @Override
  public synchronized Iterable<String> getSubpackages(String namespace) {
    ensureInitialized();
    return myCache.getSubpackagesSetFor(namespace);
  }

  @Override
  public List<RealClassPathItem> flatten() {
    List<RealClassPathItem> result = new ArrayList<>();
    result.add(this);
    return result;
  }

  @Override
  public void accept(IClassPathItemVisitor visitor) {
    visitor.visit(this);
  }

  public String toString() {
    return "jar-cp: " + myFile;
  }

  private void ensureInitialized() {
    if (myIsInitialized) return;
    myIsInitialized = true;
    buildCaches();
  }

  private synchronized void buildCaches() {
    ZipFile zf = null;
    try {
      zf = new ZipFile(myFile);
      Enumeration<? extends ZipEntry> entries = zf.entries();

      while (entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
        if (!entry.isDirectory()) {
          String name = entry.getName();

          if (!name.endsWith(MPSExtentions.DOT_CLASSFILE)) continue;

          int packEnd = name.lastIndexOf('/');
          String pack;
          String className;
          if (packEnd == -1) {
            pack = "";
            className = name.substring(0, name.length() - MPSExtentions.DOT_CLASSFILE.length());
          } else {
            pack = packEnd > 0 ? name.substring(0, packEnd).replace('/', '.') : name;
            className = name.substring(packEnd + 1, name.length() - MPSExtentions.DOT_CLASSFILE.length());
          }

          myCache.addClass(pack, InternUtil.intern(className));
        }
      }
    } catch (IOException e) {
      LOG.error(String.format("Path %s (%s) \nFile exists: %s", myFile.getPath(), myFile.getAbsolutePath(), myFile.exists()), e);
    } finally {
      FileUtil.closeFileSafe(zf);
    }
  }

  private static File transformFile(IFile f) throws IOException {
    if (!f.isInArchive()) {
      return new File(f.getPath());
    }

    File tmpFile = File.createTempFile(f.getName(), "tmp");
    tmpFile.deleteOnExit();

    OutputStream os = null;
    InputStream is = null;
    try {
      is = new BufferedInputStream(f.openInputStream());
      os = new BufferedOutputStream(new FileOutputStream(tmpFile));
      int result;
      while ((result = is.read()) != -1) {
        os.write(result);
      }
    } finally {
      if (is != null) {
        is.close();
      }
      if (os != null) {
        os.close();
      }
    }

    return tmpFile;
  }

  //do not touch this class if you are not sure in your changes - this can lead to excess memory consumption (see #53513)
  private static class MyCache {
    private final Entry myTopPackage = new Entry("");

    private Entry getEntry(String pack) {
      Entry e = myTopPackage;
      PackageNameIterator it = new PackageNameIterator(pack);
      while (it.hasNext() && e != null) {
        e = e.getSubPackage(it.next());
      }
      return e;
    }
    public Collection<String> getClassesSetFor(String pack) {
      Entry e = getEntry(pack);
      return e == null ? Collections.emptyList() : e.getClasses();
    }

    public boolean hasClass(String pack, String className) {
      Entry e = getEntry(pack);
      return e != null && e.hasClass(className);
    }

    public boolean hasPackage(String pack) {
      return getEntry(pack) != null;
    }

    public Collection<String> getSubpackagesSetFor(String pack) {
      Entry e = getEntry(pack);
      return e == null ? Collections.emptyList() : e.getImmediateSubPackages(pack);
    }

    public void addClass(String pack, String className) {
      //namespace is never null;
      Entry e = myTopPackage;
      PackageNameIterator it = new PackageNameIterator(pack);
      while (it.hasNext()) {
        e = e.createSubPackage(it.next());
      }
      e.addClass(className);
    }
  }

  /**
   * PackageNameIterator("").hasNext() == false
   *  PackageNameIterator("a").hasNext() == true
   *  PackageNameIterator("a").hasNext().hasNext() == false
   */
  private static class PackageNameIterator implements Iterator<String> {
    private final String myPackageName;
    private int start = 0;
    private int dotIndex;

    public PackageNameIterator(String packageName) {
      myPackageName = packageName;
      advance();
    }

    @Override
    public boolean hasNext() {
      return dotIndex > 0 && dotIndex <= myPackageName.length();
    }

    @Override
    public String next() {
      String rv = myPackageName.substring(start, dotIndex);
      start = dotIndex + 1;
      advance();
      return rv;
    }

    private void advance() {
      dotIndex = myPackageName.indexOf('.', start);
      if (dotIndex == -1 && start < myPackageName.length()) {
        dotIndex = myPackageName.length();
      }
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private static class Entry {
    private final String myPackageName;
    private ArrayList<Entry> mySubpackages;
    private THashSet<String> myClassNames;

    public Entry(String packageName) {
      myPackageName = packageName;
    }

    public Entry createSubPackage(String packageNamePart) {
      if (mySubpackages == null) {
        mySubpackages = new ArrayList<>(4);
        final Entry rv = new Entry(packageNamePart);
        mySubpackages.add(rv);
        return rv;
      }
      final int ix = indexOf(packageNamePart);
      if (ix < 0) {
        final Entry rv = new Entry(packageNamePart);
        mySubpackages.add(-ix - 1, rv);
        return rv;
      } else {
        return mySubpackages.get(ix);
      }
    }

    public Entry getSubPackage(String packageNamePart) {
      final int ix = indexOf(packageNamePart);
      if (ix < 0) {
        return null;
      }
      return mySubpackages.get(ix);
    }

    public void addClass(String className) {
      if (myClassNames == null) {
        myClassNames = new THashSet<>();
      }
      myClassNames.add(className);
    }

    public boolean hasClass(String className) {
      return myClassNames != null && myClassNames.contains(className);
    }

    public Collection<String> getImmediateSubPackages(String parent) {
      if (mySubpackages == null) {
        return Collections.emptyList();
      }
      ArrayList<String> rv = new ArrayList<>(mySubpackages.size());
      for (Entry e : mySubpackages) {
        if (parent == null || parent.isEmpty()) {
          rv.add(e.myPackageName);
        } else {
          rv.add(parent + '.' + e.myPackageName);
        }
      }
      return rv;
    }

    public Collection<String> getClasses() {
      return myClassNames == null ? Collections.emptyList() : myClassNames;
    }

    @Override
    public String toString() {
      return String.format("%s - %d;%d", myPackageName, mySubpackages == null ? 0 : mySubpackages.size(), myClassNames == null ? 0 : myClassNames.size());
    }

    private int indexOf(String packageName) {
      if (mySubpackages == null) {
        return -1;
      }
      int low = 0;
      int high = mySubpackages.size() - 1;
      while (low <= high) {
        int mid = (low + high) >>> 1;
        Entry c = mySubpackages.get(mid);
        int cmp = packageName.compareTo(c.myPackageName);
        if (cmp < 0) {
          high = mid - 1;
        } else if (cmp > 0) {
          low = mid + 1;
        } else {
          return mid;
        }
      }
      return -(low + 1);
    }
  }
}
