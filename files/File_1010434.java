/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.make;

import com.intellij.compiler.instrumentation.FailSafeClassReader;
import com.intellij.compiler.instrumentation.InstrumentationClassFinder;
import com.intellij.compiler.instrumentation.InstrumenterClassWriter;
import com.intellij.compiler.notNullVerification.NotNullVerifyingInstrumenter;
import jetbrains.mps.make.CompilationErrorsHandler.ClassesErrorsTracker;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.util.NameUtil;
import jetbrains.mps.vfs.IFile;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.org.objectweb.asm.ClassWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static jetbrains.mps.project.SModuleOperations.getJavaFacet;

/**
 * Write compiled java classes to disk, also instruments the notnull annotations
 * <p>
 * fixme use bundle for this package
 * Created by apyshkin on 5/26/16.
 */
public class ClassFileWriter {
  private final static String OUTPUT_DIR_CANNOT_BE_CREATED = "Can't create %s directory";
  private final static String MODULE_FOR_CLASS_NOT_FOUND = "It cannot be calculated in which module's output path the class file for %s must be placed";
  private final static String OUTPUT_DIR_IS_NOT_WRITEABLE = "Can't write to %s";
  private final static String OUTPUT_CANNOT_BE_DELETED = "Can't delete %s";

  private final ModulesContainer myModulesContainer;
  private final MessageSender mySender;
  private final ChangedModulesTracker myChangedModulesTracker = new ChangedModulesTracker();
  private final InstrumentationClassFinder myFinder;
  private final Map<String, InputStream> myClassFile2Bytes = new LinkedHashMap<>();

  // fixme think about class path
  public ClassFileWriter(ModulesContainer modulesContainer, CompositeTracer tracer, Collection<String> classPath) {
    myModulesContainer = modulesContainer;
    mySender = tracer.getSender();
    myFinder = createInstrumentationClassFinder(classPath);
  }

  @NotNull
  private InstrumentationClassFinder createInstrumentationClassFinder(final Collection<String> classPath) {
    final URL[] urlsArr = convertClassPathToUrls(classPath);
    return new InstrumentationClassFinder(urlsArr) { // fixme separate platform cp from usual cp
      @Override
      protected InputStream lookupClassBeforeClasspath(String internalClassName) {
        return myClassFile2Bytes.get(internalClassName);
      }
    };
  }

  @NotNull
  private static URL[] convertClassPathToUrls(Collection<String> classPath) {
    final List<URL> urls = new ArrayList<>();
    for (String cp : classPath) {
      assert !(cp.startsWith("file://") || cp.startsWith("jar://") || cp.startsWith("jrt://")) : "change the following code after migrating to URLPaths";
      try {
        if (!cp.endsWith(".jar") && !cp.endsWith("/") && !cp.endsWith("\\")) {
          cp = cp + "/";
        }
        urls.add(new File(cp).toURI().toURL());
        //urls.add(new URL(cp)); - enable this after migrating to URLs
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    }
    return urls.toArray(new URL[0]);
  }

  private void updateClassFile2BytesMap(List<CompilationResult> results) {
    for (CompilationResult result : results) {
      for (ClassFile classFile : result.getClassFiles()) {
        String path = convertCompoundToPath(classFile.getCompoundName());
        myClassFile2Bytes.put(path, new ByteArrayInputStream(classFile.getBytes()));
      }
    }
  }

  /**
   * @return a set of changed modules
   */
  @NotNull
  public Set<SModule> write(List<CompilationResult> results, ClassesErrorsTracker errorsTracker) {
    updateClassFile2BytesMap(results);
    for (CompilationResult result : results) {
      for (ClassFile cf : result.getClassFiles()) {
        writeClassFile(cf, errorsTracker);
      }
    }
    return myChangedModulesTracker.getModules();
  }

  private void writeClassFile(@NotNull ClassFile cf, ClassesErrorsTracker errorsTracker) {
    String fqName = convertCompoundToFqName(cf.getCompoundName());
    String containerClassName = getContainerClassName(fqName); // the name up to dollar sign
    SModule moduleForClass = myModulesContainer.getModuleContainingClass(containerClassName);
    if (moduleForClass == null) {
      mySender.error(String.format(MODULE_FOR_CLASS_NOT_FOUND, fqName));
    } else {
      myChangedModulesTracker.addChanged(moduleForClass);
      File outputDir = createOutputDir(fqName, moduleForClass);
      String className = NameUtil.shortNameFromLongName(fqName);
      File output = new File(outputDir, className + MPSExtentions.DOT_CLASSFILE);
      if (!errorsTracker.hasError(containerClassName)) {
        writeClassFile(cf, output);
      } else {
        if (output.exists() && !output.delete()) {
          String errMsg = String.format(OUTPUT_CANNOT_BE_DELETED, output.getPath());
          mySender.error(errMsg);
        }
      }
    }
  }

  private void writeClassFile(ClassFile classFile, File output) {
    FileOutputStream os = null;
    try {
      os = new FileOutputStream(output);
      byte[] classContent = instrumentNotNull(classFile.getBytes());
      os.write(classContent);
    } catch (IOException e) {
      mySender.error(String.format(OUTPUT_DIR_IS_NOT_WRITEABLE, output.getAbsolutePath()));
    } finally {
      assert os != null;
      try {
        os.close();
      } catch (IOException e) {
        mySender.error("IOException: ", e);
      }
    }
  }

  @NotNull
  private File createOutputDir(String fqName, SModule m) {
    File classesGen = getClassesGen(m);
    String packageName = NameUtil.namespaceFromLongName(fqName);
    File outputDir = new File(classesGen, NameUtil.pathFromNamespace(packageName));
    if (!outputDir.exists() && !outputDir.mkdirs()) {
      throw new RuntimeException(String.format(OUTPUT_DIR_CANNOT_BE_CREATED, outputDir.getPath()));
    }
    return outputDir;
  }

  @NotNull
  private File getClassesGen(@NotNull SModule m) {
    IFile classesGen = getJavaFacet(m).getClassesGen();
    assert classesGen != null;
    return new File(classesGen.getPath());
  }

  /**
   * cuts the name up to the $ sign
   */
  @NotNull
  private static String getContainerClassName(String fqName) {
    String containerClassName = fqName;
    if (containerClassName.contains("$")) {
      int index = containerClassName.indexOf('$');
      containerClassName = containerClassName.substring(0, index);
    }
    return containerClassName;
  }

  // FIXME
  @NotNull
  private byte[] instrumentNotNull(@NotNull byte[] classContent) {
    FailSafeClassReader reader = new FailSafeClassReader(classContent, 0, classContent.length);
    ClassWriter writer = new InstrumenterClassWriter(reader, ClassWriter.COMPUTE_FRAMES, myFinder);
    // To understand why last parameter was added - see commits 250331a & 490d4e6 in IDEA Community
    try {
      NotNullVerifyingInstrumenter.processClassFile(reader, writer, new String[]{NotNull.class.getName()});
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return writer.toByteArray();
//    return classContent;
  }

  @NotNull
  public static String convertCompoundToFqName(char[][] compoundName) {
    return convertCompoundToStringWithSep(compoundName, '.');
  }

  private static String convertCompoundToPath(char[][] compoundName) {
    return convertCompoundToStringWithSep(compoundName, '/');
  }

  private static String convertCompoundToStringWithSep(char[][] compoundName, char separator) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < compoundName.length; i++) {
      char[] part = compoundName[i];
      result.append(part);
      if (i != compoundName.length - 1) {
        result.append(separator);
      }
    }
    return result.toString();
  }

  private static class ChangedModulesTracker {
    private final Set<SModule> myModules = new HashSet<>();

    public void addChanged(@NotNull SModule module) {
      myModules.add(module);
    }

    public Set<SModule> getModules() {
      return myModules;
    }
  }
}
