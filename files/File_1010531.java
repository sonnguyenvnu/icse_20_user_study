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

import jetbrains.mps.util.FlattenIterable;
import jetbrains.mps.util.iterable.IterableEnumeration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CompositeClassPathItem extends AbstractClassPathItem {
  private List<IClassPathItem> myChildren = new ArrayList<>();

  public void add(IClassPathItem item) {
    assert item != null;
    myChildren.add(item);
  }

  @Override
  public boolean hasClass(String name) {
    for (IClassPathItem item : myChildren) {
      if (item.hasClass(name)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean hasPackage(@NotNull String name) {
    for (IClassPathItem item : myChildren) {
      if (item.hasPackage(name)) {
        return true;
      }
    }
    return false;
  }

  @Nullable
  @Override
  public ClassBytes getClassBytes(String name) {
    for (IClassPathItem item : myChildren) {
      ClassBytes result = item.getClassBytes(name);
      if (result != null) return result;
    }
    return null;
  }

  @Override
  public URL getResource(String name) {
    for (IClassPathItem item : myChildren) {
      URL resource = item.getResource(name);
      if (resource != null) {
        return resource;
      }
    }
    return null;
  }

  @Override
  public Enumeration<URL> getResources(String name) {
    List<URL> result = new ArrayList<>();
    for (IClassPathItem item : myChildren) {
      Enumeration<URL> resources = item.getResources(name);
      while (resources.hasMoreElements()) {
        result.add(resources.nextElement());
      }
    }
    return new IterableEnumeration<>(result);
  }

  @Override
  public Iterable<String> getAvailableClasses(String namespace) {
    FlattenIterable<String> result = new FlattenIterable<>();
    for (IClassPathItem item : myChildren) {
      //todo rewrite using mapping iterable
      result.add(item.getAvailableClasses(namespace));
    }
    return result;
  }

  @Override
  public Iterable<String> getSubpackages(String namespace) {
    FlattenIterable<String> result = new FlattenIterable<>();
    for (IClassPathItem item : myChildren) {
      //todo rewrite using mapping iterable
      result.add(item.getSubpackages(namespace));
    }
    return result;
  }

  public List<IClassPathItem> getChildren() {
    return new ArrayList<>(myChildren);
  }

  @Override
  public List<RealClassPathItem> flatten() {
    List<RealClassPathItem> result = new ArrayList<>();

    for (IClassPathItem child : myChildren) {
      result.addAll(child.flatten());
    }

    return result;
  }

  @Override
  public CompositeClassPathItem optimize() {
    List<RealClassPathItem> flattenedItems = flatten();
    Iterator<RealClassPathItem> it = flattenedItems.iterator();

    Set<String> alreadyVisited = new HashSet<>();

    while (it.hasNext()) {
      IClassPathItem item = it.next();
      if (item instanceof FileClassPathItem) {
        FileClassPathItem fcp = (FileClassPathItem) item;
        if (alreadyVisited.contains(fcp.getPath())) {
          it.remove();
        } else {
          alreadyVisited.add(fcp.getPath());
        }
      }

      if (item instanceof JarFileClassPathItem) {
        JarFileClassPathItem jfcp = (JarFileClassPathItem) item;
        String path = jfcp.getFile().getAbsolutePath();
        if (alreadyVisited.contains(path)) {
          it.remove();
        } else {
          alreadyVisited.add(path);
        }
      }
    }

    CompositeClassPathItem result = new CompositeClassPathItem();
    for (IClassPathItem item : flattenedItems) {
      result.add(item);
    }

    return result;
  }

  @Override
  public void accept(IClassPathItemVisitor visitor) {
    visitor.visit(this);
  }

  public String toString() {
    StringBuilder result = new StringBuilder("classpath {\n");

    for (IClassPathItem child : myChildren) {
      for (String s : child.toString().split("/[\n]/")) {
        result.append('\t').append(s).append('\n');
      }
    }

    result.append('}');
    return result.toString();
  }
}
