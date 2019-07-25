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
package jetbrains.mps.reloading;

import jetbrains.mps.util.SystemInfo;
import jetbrains.mps.vfs.Files;
import jetbrains.mps.vfs.IFileSystem;
import jetbrains.mps.vfs.QualifiedPath;
import jetbrains.mps.vfs.VFSManager;
import jetbrains.mps.vfs.iofs.jrt.JrtIoFileSystem;
import jetbrains.mps.vfs.util.PathUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * This code was copied from IDEA
 */
public class SDKDiscovery {
  public static List<QualifiedPath> discover() {
    return findJavaRuntimeClasses(new File(System.getProperty("java.home")));
  }

  //------------------------------

  @Nullable
  private static QualifiedPath getJDK_ToolsPath() {
    String toolsJarClass = "com.sun.jdi.Field";
    try {
      Class cls = Class.forName(toolsJarClass);
      String url = cls.getResource(cls.getSimpleName() + ".class").toString();
      url = URLDecoder.decode(url, Charset.defaultCharset().name()).replace('/', File.separatorChar);
      String prefix = "jar:";
      assert url.startsWith(prefix) : url;

      String jarPath = url.substring(prefix.length(), url.indexOf('!'));
      return new QualifiedPath(VFSManager.FILE_FS, Files.fromURL(new URL(jarPath)).getPath());
    } catch (ClassNotFoundException | MalformedURLException e) {
      LOG.warn("jar file for class " + toolsJarClass + " could not be found");
      return null;
    } catch (UnsupportedEncodingException e) {
      LOG.error("Exception when trying to find tools.jar: ",e);
      return null;
    }
  }

  //-------------------------------------copied from JavaSdkImpl----------------------------------

  private static final Logger LOG = LogManager.getLogger(SDKDiscovery.class);

  @NotNull
  private static List<QualifiedPath> findJavaRuntimeClasses(@NotNull File javaHome) {
    List<QualifiedPath> result = new ArrayList<>();

    if (isExplodedModularRuntime(javaHome.getPath())) {
      File[] exploded = new File(javaHome, "modules").listFiles();
      if (exploded != null) {
        for (File root : exploded) {
          result.add(new QualifiedPath(VFSManager.FILE_FS, getPath(root))); // == LocalFile.getQualifiedPath()
        }
      }
    } else if (isModularRuntime(javaHome)) {
      String jrtBase = getPath(javaHome) + JrtIoFileSystem.JDK_PATH_SEPARATOR + IFileSystem.SEPARATOR;
      List<String> modules = readModulesFromReleaseFile(javaHome);
      // XXX this seems to be dead code, isModularRuntime() here true iff isFile() == true (isExplodedModularRuntime branch is above)
      //     and there could be no "release" file under another File (in java.io)
      if (modules != null) {
        for (String module : modules) {
          result.add(new QualifiedPath(VFSManager.JRT_FS, jrtBase + module));
        }
      } else {
        // JrtIoFile#getChildren
        java.nio.file.FileSystem jrtfs = FileSystems.getFileSystem(URI.create("jrt:/"));
        Path jdkRoot = jrtfs.getPath("modules");
        if (java.nio.file.Files.isDirectory(jdkRoot)) {
          try (DirectoryStream<Path> ds = java.nio.file.Files.newDirectoryStream(jdkRoot)) {
            for (Path p : ds) {
              // see JrtIoFile#getPath.
              // JrtIoFile#getPathInJDK() doesn't include "modules" part, which is hardcoded in JrtIoFile#getRealFile, hence p.getFileName only
              result.add(new QualifiedPath(VFSManager.JRT_FS, jrtBase + p.getFileName().toString()));
            }
          } catch (IOException e) {
            LOG.warn(String.format("Can't read %s", jdkRoot), e);
          }
        }
      }
    } else {
      for (File root : getJdkClassesRoots(javaHome, !new File(javaHome, "jre").exists())) {
        String path = getPath(root);
        result.add(new QualifiedPath(VFSManager.FILE_FS, path));
      }
      QualifiedPath toolsJar = getJDK_ToolsPath();
      if (toolsJar != null) {
        result.add(toolsJar);
      }
    }

    result.sort((o1, o2) -> {
      int res1 = o1.getFsId().compareTo(o2.getFsId());
      if (res1 != 0) {
        return res1;
      }
      return o1.getPath().compareTo(o2.getPath());
    });
    return result;
  }


  /**
   * Tries to load the list of modules in the JDK from the 'release' file. Returns null if the 'release' file is not there
   * or doesn't contain the expected information.
   */
  @Nullable
  private static List<String> readModulesFromReleaseFile(File jrtBaseDir) {
    File releaseFile = new File(jrtBaseDir, "release");
    if (releaseFile.isFile()) {
      Properties p = new Properties();
      try (FileInputStream stream = new FileInputStream(releaseFile)) {
        p.load(stream);
        String modules = p.getProperty("MODULES");
        if (modules != null) {
          return split(unquoteString(modules), " ", true, true);
        }
      } catch (IOException | IllegalArgumentException e) {
        LOG.info(e);
      }
    }
    return null;
  }

  private static String getPath(File jarFile) {
    return PathUtil.toSystemIndependent(jarFile.getAbsolutePath());
  }

  //-------------------------------------copied from JdkUtil----------------------------------

  private static boolean isModularRuntime(@NotNull File homePath) {
    return new File(homePath, "lib/jrt-fs.jar").isFile() || isExplodedModularRuntime(homePath.getPath());
  }

  private static boolean isExplodedModularRuntime(@NotNull String homePath) {
    return new File(homePath, "modules/java.base").isDirectory();
  }

  //-------------------------------------copied from JavaSdkUtil----------------------------------

  public static List<File> getJdkClassesRoots(@NotNull File home, boolean isJre) {
    File[] jarDirs;
    if (SystemInfo.isMac && !home.getName().startsWith("mockJDK")) {
      File openJdkRtJar = new File(home, "jre/lib/rt.jar");
      if (openJdkRtJar.isFile()) {
        File libDir = new File(home, "lib");
        File classesDir = openJdkRtJar.getParentFile();
        File libExtDir = new File(openJdkRtJar.getParentFile(), "ext");
        File libEndorsedDir = new File(libDir, "endorsed");
        jarDirs = new File[]{libEndorsedDir, libDir, classesDir, libExtDir};
      } else {
        File libDir = new File(home, "lib");
        File classesDir = new File(home, "../Classes");
        File libExtDir = new File(libDir, "ext");
        File libEndorsedDir = new File(libDir, "endorsed");
        jarDirs = new File[]{libEndorsedDir, libDir, classesDir, libExtDir};
      }
    } else if (new File(home, "lib/jrt-fs.jar").exists()) {
      jarDirs = new File[0];
    } else {
      File libDir = new File(home, isJre ? "lib" : "jre/lib");
      File libExtDir = new File(libDir, "ext");
      File libEndorsedDir = new File(libDir, "endorsed");
      jarDirs = new File[]{libEndorsedDir, libDir, libExtDir};
    }

    Set<String> pathFilter = new HashSet<>();
    List<File> rootFiles = new ArrayList<>();
    for (File jarDir : jarDirs) {
      if (jarDir != null && jarDir.isDirectory()) {
        File[] jarFiles = listFiles(jarDir, pathname -> pathname.getName().endsWith(".jar"));
        for (File jarFile : jarFiles) {
          String jarFileName = jarFile.getName();
          if (jarFileName.equals("alt-rt.jar") || jarFileName.equals("alt-string.jar")) {
            continue;  // filter out alternative implementations
          }
          String canonicalPath = getCanonicalPath(jarFile);
          if (canonicalPath == null || !pathFilter.add(canonicalPath)) {
            continue;  // filter out duplicate (symbolically linked) .jar files commonly found in OS X JDK distributions
          }
          rootFiles.add(jarFile);
        }
      }
    }

    List<File> ibmJdkLookupDirs = new ArrayList<>();
    ibmJdkLookupDirs.add(new File(home, isJre ? "bin" : "jre/bin"));
    ibmJdkLookupDirs.addAll(Arrays.asList(listFiles(new File(home, isJre ? "lib" : "jre/lib"), pathname -> pathname.isDirectory())));
    for (File candidate : ibmJdkLookupDirs) {
      File[] vmJarDirs = listFiles(new File(candidate, "default"), f -> f.getName().startsWith("jclSC") && f.isDirectory());
      for (File dir : vmJarDirs) {
        File vmJar = new File(dir, "vm.jar");
        if (vmJar.isFile()) {
          rootFiles.add(vmJar);
        }
      }
    }

    File classesZip = new File(home, "lib/classes.zip");
    if (classesZip.isFile()) {
      rootFiles.add(classesZip);
    }

    if (rootFiles.isEmpty()) {
      File classesDir = new File(home, "classes");
      if (classesDir.isDirectory()) {
        rootFiles.add(classesDir);
      }
    }

    return rootFiles;
  }

  private static File[] listFiles(File dir, FileFilter filter) {
    File[] files = dir.listFiles(filter);
    return files == null ? new File[0] : files;
  }

  @Nullable
  private static String getCanonicalPath(File file) {
    try {
      return file.getCanonicalPath();
    } catch (IOException e) {
      return null;
    }
  }

  //-------------------------------------copied from StringUtil----------------------------------

  @NotNull
  @SuppressWarnings("unchecked")
  public static List<String> split(@NotNull String s, @NotNull String separator, boolean excludeSeparator, boolean excludeEmptyStrings) {
    return (List) split((CharSequence) s, separator, excludeSeparator, excludeEmptyStrings);
  }

  @NotNull
  public static List<CharSequence> split(@NotNull CharSequence s, @NotNull CharSequence separator, boolean excludeSeparator, boolean excludeEmptyStrings) {
    if (separator.length() == 0) {
      return Collections.singletonList(s);
    }
    List<CharSequence> result = new ArrayList<CharSequence>();
    int pos = 0;
    while (true) {
      int index = indexOf(s, separator, pos);
      if (index == -1) {
        break;
      }
      final int nextPos = index + separator.length();
      CharSequence token = s.subSequence(pos, excludeSeparator ? index : nextPos);
      if (token.length() != 0 || !excludeEmptyStrings) {
        result.add(token);
      }
      pos = nextPos;
    }
    if (pos < s.length() || !excludeEmptyStrings && pos == s.length()) {
      result.add(s.subSequence(pos, s.length()));
    }
    return result;
  }

  public static int indexOf(@NotNull CharSequence sequence, @NotNull CharSequence infix, int start) {
    return indexOf(sequence, infix, start, sequence.length());
  }

  public static int indexOf(@NotNull CharSequence sequence, @NotNull CharSequence infix, int start, int end) {
    for (int i = start; i <= end - infix.length(); i++) {
      if (startsWith(sequence, i, infix)) {
        return i;
      }
    }
    return -1;
  }

  public static boolean startsWith(@NotNull CharSequence text, int startIndex, @NotNull CharSequence prefix) {
    int tl = text.length();
    if (startIndex < 0 || startIndex > tl) {
      throw new IllegalArgumentException("Index is out of bounds: " + startIndex + ", length: " + tl);
    }
    int l1 = tl - startIndex;
    int l2 = prefix.length();
    if (l1 < l2) {
      return false;
    }

    for (int i = 0; i < l2; i++) {
      if (text.charAt(i + startIndex) != prefix.charAt(i)) {
        return false;
      }
    }

    return true;
  }

  //-------------------------------------copied from StringUtilRt----------------------------------

  public static boolean isQuotedString(@NotNull String s) {
    return s.length() > 1 && (s.charAt(0) == '\'' || s.charAt(0) == '\"') && s.charAt(0) == s.charAt(s.length() - 1);
  }

  @NotNull
  public static String unquoteString(@NotNull String s) {
    return isQuotedString(s) ? s.substring(1, s.length() - 1) : s;
  }
}
