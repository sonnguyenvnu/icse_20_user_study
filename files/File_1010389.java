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
package jetbrains.mps.util;

import jetbrains.mps.project.PathMacros;
import jetbrains.mps.vfs.IFile;
import jetbrains.mps.vfs.IFileSystem;
import jetbrains.mps.vfs.util.PathAssert;
import jetbrains.mps.vfs.util.PathAssert;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

class Macros {
  private final PathMacros myComponent;

  protected Macros(@NotNull PathMacros component) {
    myComponent = component;
  }

  protected String expand(String path, @Nullable IFile anchorFile) {
    new PathAssert(path).osIndependentPath();

    if (!MacrosFactory.containsMacro(path)) {
      return path;
    }
    int macroEnd = path.indexOf('}');
    String macro = path.substring(2, macroEnd);
    String macroValue = myComponent.getValue(macro);
    if (macroValue == null) {
      myComponent.report("Please define path variable in path variables section of settings", macro);
      return path;
    }
    String expanded = macroValue + path.substring(macroEnd + 1);
    return FileUtil.resolveParentDirs(expanded);
  }

  protected String shrink(String absolutePath, IFile anchorFile) {
    new PathAssert(absolutePath).osIndependentPath().noDots().absolute();

    String fileName;
    Set<String> macroNames = myComponent.getNames();
    for (String macro : macroNames) {
      String path = myComponent.getValue(macro);
      if (path != null) {
        path = FileUtil.normalize(path);//hack for 19.1, replace with assertion in 19.2
        if (pathStartsWith(absolutePath, path)) {
          String relationalPath = shrink(absolutePath, path);
          fileName = "${" + macro + "}" + relationalPath;
          return fileName;
        }
      }
    }
    fileName = absolutePath;
    return fileName;
  }

  protected static String shrink(String path, String prefix) {
    if (path.equals(prefix)) {
      return "";
    }
    assert path.length() >= prefix.length() : "path: " + path + "; prefix: " + prefix;
    return IFileSystem.SEPARATOR + FileUtil.getRelativePath(path, prefix, IFileSystem.SEPARATOR);
  }

  static boolean pathStartsWith(String absolutePath, @NotNull String with) {
    new PathAssert(absolutePath).absolute();

    if (absolutePath.equals(with)) {
      return true;
    }

    String fullPart = with + (with.endsWith(IFileSystem.SEPARATOR) ? "" : IFileSystem.SEPARATOR);
    if (!absolutePath.toLowerCase().startsWith(fullPart.toLowerCase())) {
      return false;
    }

    String pathReplaced = with + absolutePath.substring(with.length());
    return absolutePath.equals(pathReplaced);
  }
}
