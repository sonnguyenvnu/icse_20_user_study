/*
 * Copyright 2003-2017 JetBrains s.r.o.
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

import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.util.NameUtil;
import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CompilationErrorsHandler {
  private final static int MAX_ERRORS = 100;
  private final static String MODULES_CLASSPATH_STR = "Modules: %s;\nClasspath: %s\n"; // FIXME: transfer
  private final static String FATAL_ERROR_MSG = "Fatal error during eclipse compilation: %s";
  private final static String ERROR_FORMAT_STRING = "%s (line: %d)";
  private final static String COMPILATION_PROBLEMS = "Compilation problems";

  private final ModulesContainer myModulesContainer;
  private final MessageSender mySender;
  private final ClassesErrorsTracker myErrorTracker = new ClassesErrorsTracker();
  private Collection<String> myClassPath;

  public CompilationErrorsHandler(@NotNull ModulesContainer modulesContainer, @NotNull MessageSender sender,
                                  Collection<String> classPath) {
    myModulesContainer = modulesContainer;
    myClassPath = classPath;
    mySender = new MessageSender(sender, this);
  }

  /**
   * parses compilation result for errors and prints them out
   *
   */
  public ClassesErrorsTracker handle(org.eclipse.jdt.internal.compiler.CompilationResult result) {
    if (result.getErrors().length > 0) {
      mySender.error(COMPILATION_PROBLEMS);
      mySender.info(String.format(MODULES_CLASSPATH_STR, myModulesContainer.getModules(), myClassPath));
    }
    for (final CategorizedProblem problem : result.getErrors()) {
      String fileName = new String(problem.getOriginatingFileName());
      final String fqName = NameUtil.namespaceFromPath(fileName.substring(0, fileName.length() - MPSExtentions.DOT_JAVAFILE.length()));
      myErrorTracker.add(fqName);

      SModule containingModule = myModulesContainer.getModuleContainingClass(fqName);
      assert containingModule != null;
      JavaFile javaFile = myModulesContainer.getSources(containingModule).getJavaFile(fqName);

      String messageString = String.valueOf(problem.getOriginatingFileName()) + " : " + problem.getMessage();
      //final SNode nodeToShow = getNodeByLine(problem, fqName);

      Object hintObject = new FileWithPosition(javaFile.getFile(), problem.getSourceStart());

      String errMsg = String.format(ERROR_FORMAT_STRING, messageString, problem.getSourceLineNumber());
      if (problem.isWarning()) {
        mySender.warn(errMsg, hintObject);
      } else if (myErrorTracker.errorsBelowLimit()) {
        myErrorTracker.incErrCnt();
        mySender.error(errMsg, hintObject);
      }
    }
    return myErrorTracker;
  }

  public void handleFatal(@NotNull String msg) {
    mySender.error(String.format(FATAL_ERROR_MSG, msg), null);
    mySender.info(String.format(MODULES_CLASSPATH_STR, myModulesContainer.getModules(), myClassPath));
    myErrorTracker.incErrCnt();
  }

  public int getErrorsCount() {
    return myErrorTracker.getErrorsCount();
  }

  /**
   * a set of classes fqNames which contain errors and an error counter
   */
  public final static class ClassesErrorsTracker {
    private final Set<String> myFqNamesWithErrors = new HashSet<>();
    private int myErrorsCount = 0;

    public int getErrorsCount() {
      return myErrorsCount;
    }

    public void add(@NotNull String classWithError) {
      myFqNamesWithErrors.add(classWithError);
    }

    public boolean hasError(@NotNull String classFqName) {
      return myFqNamesWithErrors.contains(classFqName);
    }

    public void incErrCnt() {
      myErrorsCount++;
    }

    public boolean errorsBelowLimit() {
      return myErrorsCount < MAX_ERRORS;
    }
  }
}
