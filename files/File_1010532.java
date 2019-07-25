/*
 * Copyright 2003-2015 JetBrains s.r.o.
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

import gnu.trove.THashMap;
import gnu.trove.THashSet;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.util.ConditionalIterable;
import jetbrains.mps.util.InternUtil;
import jetbrains.mps.util.JavaNameUtil;
import jetbrains.mps.util.NameUtil;
import jetbrains.mps.util.ReadUtil;
import jetbrains.mps.util.containers.EmptyIterable;
import org.jetbrains.mps.util.Condition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Kostik
 */
class FileClassPathItem extends RealClassPathItem {
  private final String myClassPath;
  private final Map<String, Set<String>> mySubpackagesCache = new THashMap<>();
  private final Map<String, Set<String>> myAvailableClassesCache = new THashMap<>();

  FileClassPathItem(String classPath) {
    myClassPath = classPath;
  }

  @Deprecated
  public String getClassPath() {
    return getPath();
  }

  @Override
  public String getPath() {
    return myClassPath;
  }

  @Override
  public boolean hasClass(String name) {
    String namespace = NameUtil.namespaceFromLongName(name);
    String shortname = NameUtil.shortNameFromLongName(name);

    if (!myAvailableClassesCache.containsKey(namespace)) {
      buildCacheFor(namespace);
    }

    Set<String> classes = myAvailableClassesCache.get(namespace);
    return classes != null && classes.contains(shortname);
  }

  @Override
  public synchronized ClassBytes getClassBytes(String name) {
    String namespace = NameUtil.namespaceFromLongName(name);
    String shortName = NameUtil.shortNameFromLongName(name);

    if (!myAvailableClassesCache.containsKey(namespace)) {
      buildCacheFor(namespace);
    }

    Set<String> classes = myAvailableClassesCache.get(namespace);
    if (classes == null || !classes.contains(shortName)) {
      return null;
    }

    String path = myClassPath + File.separatorChar + NameUtil.pathFromNamespace(name) + MPSExtentions.DOT_CLASSFILE;
    try {
      byte[] bytes = null;
      InputStream inp = null;
      try {
        inp = new FileInputStream(path);
        bytes = ReadUtil.read(inp);
      } finally {
        if (inp != null) {
          inp.close();
        }
      }

      return bytes == null ? null : new DefaultClassBytes(bytes, new File(path).toURI().toURL());
    } catch (IOException e) {
      return null;
    }
  }

  @Override
  public URL getResource(String name) {
    try {
      File resourceFile = new File(myClassPath + File.separator + name.replace('/', File.separatorChar));
      if (!resourceFile.exists()) return null;
      return resourceFile.toURI().toURL();
    } catch (MalformedURLException e) {
      return null;
    }
  }

  @Override
  public synchronized Iterable<String> getAvailableClasses(String namespace) {
    if (!myAvailableClassesCache.containsKey(namespace)) {
      buildCacheFor(namespace);
    }

    Set<String> start = myAvailableClassesCache.get(namespace);
    if (start == null) return new EmptyIterable<>();
    Condition<String> cond = className -> !JavaNameUtil.isAnonymous(className);
    return new ConditionalIterable<>(start, cond);
  }

  @Override
  public synchronized Iterable<String> getSubpackages(String namespace) {
    if (!mySubpackagesCache.containsKey(namespace)) {
      buildCacheFor(namespace);
    }

    Set<String> result = mySubpackagesCache.get(namespace);
    if (result == null) return new EmptyIterable<>();
    return Collections.unmodifiableSet(result);
  }

  private synchronized void buildCacheFor(String namespace) {
    namespace = InternUtil.intern(namespace);
    Set<String> subpacks = null;
    Set<String> classes = null;
    File dir = getModelDir(namespace);

    String[] files = dir.list();
    if (files != null) {
      for (String name : files) {
        if (name.endsWith(MPSExtentions.DOT_CLASSFILE)) { //isDirectory is quite expensive operation
          if (classes == null) {
            classes = new THashSet<>(files.length);
          }
          String classname = name.substring(0, name.length() - MPSExtentions.DOT_CLASSFILE.length());
          classes.add(InternUtil.intern(classname));
        } else {
          File file = new File(dir, name);
          if (file.isDirectory()) {
            if (subpacks == null) {
              subpacks = new THashSet<>();
            }
            String fqName = namespace.length() > 0 ? namespace + "." + name : name;
            subpacks.add(InternUtil.intern(fqName));
          }
        }
      }
    }

    mySubpackagesCache.put(namespace, subpacks);
    myAvailableClassesCache.put(namespace, classes);
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

  private File getModelDir(String namespace) {
    if (namespace == null) namespace = "";
    return new File(myClassPath + File.separatorChar + NameUtil.pathFromNamespace(namespace));
  }

  public String toString() {
    return "file-cp: " + myClassPath;
  }
}
