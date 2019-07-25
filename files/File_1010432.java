/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.compiler;

import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.util.AbstractClassLoader;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.util.NameUtil;
import org.eclipse.jdt.internal.compiler.ClassFile;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.batch.CompilationUnit;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * MPS java compiler class, which relies on the eclipse compiler {@link Compiler} functionality.
 * Works by consequently adding java source files by calling the method {@link #addSource(String, String)}
 * and once the method {@link #compile} after that
 */
public class EclipseJavaCompiler {
  private Map<String, CompilationUnit> myCompilationUnits = new HashMap<>();
  private Map<String, byte[]> myClasses = new HashMap<>();

  @NotNull
  private static Map<String, String> addPresetCompilerOptions(@NotNull JavaCompilerOptions customCompilerOptions) {
    Map<String, String> compilerOptions = new HashMap<>();
    String actualJavaTargetVersion = customCompilerOptions.getTargetJavaVersion().getCompilerVersion();
    compilerOptions.put(CompilerOptions.OPTION_Source, actualJavaTargetVersion);
    compilerOptions.put(CompilerOptions.OPTION_Compliance, actualJavaTargetVersion);
    compilerOptions.put(CompilerOptions.OPTION_TargetPlatform, actualJavaTargetVersion);


    compilerOptions.put(CompilerOptions.OPTION_LocalVariableAttribute, CompilerOptions.GENERATE);
    compilerOptions.put(CompilerOptions.OPTION_LineNumberAttribute, CompilerOptions.GENERATE);
    compilerOptions.put(CompilerOptions.OPTION_SourceFileAttribute, CompilerOptions.GENERATE);
    return compilerOptions;
  }

  public void addSource(String classFqName, String text) {
    CompilationUnit compilationUnit = new CompilationUnit(text.toCharArray(), NameUtil.pathFromNamespace(classFqName) + MPSExtentions.DOT_JAVAFILE,
        FileUtil.DEFAULT_CHARSET_NAME);
    myCompilationUnits.put(classFqName, compilationUnit);
  }

  public void compile(Collection<String> classPath) {
    compile(classPath, JavaCompilerOptionsComponent.DEFAULT_JAVA_COMPILER_OPTIONS);
  }

  public void compile(Collection<String> classPath, @NotNull JavaCompilerOptions customCompilerOptions) {
    Map<String, String> compilerOptions = addPresetCompilerOptions(customCompilerOptions);

    CompilerOptions options = new CompilerOptions(compilerOptions);
    Compiler compiler = new Compiler(new JDKFileSystem(classPath, new String[0]), new ProceedingOnErrorsPolicy(), options, new RelayingRequestor(), new DefaultProblemFactory());
//    compiler.options.verbose = true;

    try {
      Collection<CompilationUnit> compilationUnits = myCompilationUnits.values();
      compiler.compile(compilationUnits.toArray(new CompilationUnit[0]));
    } catch (RuntimeException ex) {
      onFatalError(ex.getMessage());
    }
  }

  /**
   * The only usage is from evaluator module
   * this logic must be realized at the calling site
   */
  @Deprecated
  public ClassLoader getClassLoader(ClassLoader parent) {
    return new MapClassLoader(parent);
  }

  public Map<String, byte[]> getClasses() {
    return Collections.unmodifiableMap(myClasses);
  }

  private class MapClassLoader extends AbstractClassLoader {
    private MapClassLoader(ClassLoader parent) {
      super(parent);
    }

    @Override
    protected byte[] findClassBytes(String name) {
      return getClasses().get(name);
    }

    @Override
    protected boolean isExcluded(String name) {
      return false;
    }
  }

  private static class ProceedingOnErrorsPolicy implements IErrorHandlingPolicy {
    @Override
    public boolean proceedOnErrors() {
      return true;
    }

    @Override
    public boolean stopOnFirstError() {
      return false;
    }

    @Override
    public boolean ignoreAllErrors() {
      return false;
    }
  }

  public static String getClassName(ClassFile file) {
    StringBuilder sb = new StringBuilder(100);
    for (int i = 0; i < file.getCompoundName().length; i++) {
      sb.append(file.getCompoundName()[i]);
      if (i != file.getCompoundName().length - 1) {
        sb.append('.');
      }
    }

    return sb.toString();
  }

  private class RelayingRequestor implements ICompilerRequestor {
    @Override
    public void acceptResult(CompilationResult result) {
      for (ClassFile file : result.getClassFiles()) {
        onClass(file);
        myClasses.put(getClassName(file), file.getBytes());
      }

      onCompilationResult(result);
    }
  }

  //-----------event handling------------

  private void onCompilationResult(CompilationResult r) {
    for (CompilationResultListener l : myCompilationResultListeners) {
      l.onCompilationResult(r);
    }
  }

  private void onClass(ClassFile f) {
    for (CompilationResultListener l : myCompilationResultListeners) {
      l.onClass(f);
    }
  }

  private void onFatalError(String error) {
    for (CompilationResultListener l : myCompilationResultListeners) {
      l.onFatalError(error);
    }
  }

  private ArrayList<CompilationResultListener> myCompilationResultListeners = new ArrayList<>();

  public void addCompilationResultListener(@NotNull CompilationResultListener l) {
    myCompilationResultListeners.add(l);
  }

  public void removeCompilationResultListener(CompilationResultListener l) {
    myCompilationResultListeners.remove(l);
  }
}
