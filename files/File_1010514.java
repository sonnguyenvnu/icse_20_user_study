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
package jetbrains.mps.project.dependency;

import jetbrains.mps.smodel.Language;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.module.SDependency;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class helps extracting all dependencies of a given type for a given set of modules.
 * E.g. we can give it a set of modules and ask, which modules are needed to compile the given set:
 * new GlobalModuleDependenciesManager(startSet).getModules(Deptype.COMPILE)
 * Note that if we have M modules and N dependencies, and want to know something about a set of S modules,
 * this will work O(M+N) in the worst case, regardless of S
 */
public class GlobalModuleDependenciesManager {
  final static Logger LOG = LogManager.getLogger(GlobalModuleDependenciesManager.class);

  private final Set<SModule> myModules;
  @NotNull private final ErrorHandler myHandler;
  private final UsedModulesCollector myUsedModulesCollector = new UsedModulesCollector();

  public GlobalModuleDependenciesManager(Collection<? extends SModule> modules, @NotNull ErrorHandler handler) {
    myModules = new HashSet<>(modules);
    myHandler = handler;
  }

  public GlobalModuleDependenciesManager(Collection<? extends SModule> modules) {
    this(modules, DEFAULT_HANDLER);
  }

  public GlobalModuleDependenciesManager(@NotNull SModule module, ErrorHandler handler) {
    this(Collections.singletonList(module), handler);
  }

  public GlobalModuleDependenciesManager(@NotNull SModule module) {
    this(module, DEFAULT_HANDLER);
  }

  /**
   * @return all languages used by the given modules
   * @deprecated Use {@link org.jetbrains.mps.openapi.module.SModule#getUsedLanguages()} directly.
   */
  @Deprecated
  @ToRemove(version = 3.3)
  public Collection<Language> getUsedLanguages() {
    Set<Language> result = new HashSet<>();
    for (SModule module : myModules) {
      result.addAll(directlyUsedLanguages(module));
    }
    return result;
  }

  /**
   * Return only modules with 'reexport' mark in the dependents subtree
   * @deprecated one usage does not justify method's existence
   */
  @Deprecated
  @ToRemove(version = 3.4)
  public Collection<SModule> getOnlyReexportModules() {
    Set<SModule> result = new HashSet<>();
    for (SModule module : myModules) {
      collect(module, result, Deptype.VISIBLE);
    }
    return result;
  }

  /**
   * Return all modules of a given dependency type in scope of given
   * <p/>
   * RUNTIMES:
   * If we need runtimes, this only adds additional edges to our graph. M -uses> L -runtime> R is equivalent
   * to M -non-reexp> R in this case
   * <p/>
   * REEXPORT:
   * If we need dependencies with respect to reexport flag, we should first collect all neighbours of the
   * given nodes in graph, and then, considering the graph with "reexport" edges only, collect all nodes
   * accessible from (start set+neighbours) in this graph
   * If we don't respect reexport flag, we should collect all accessible nodes from the given set in a
   * dependencies graph. The "neighbours scheme" works in this case, too.
   *
   * @param depType determines the type of dependencies we want to get
   * @return all modules in scope of given
   */
  @NotNull
  public Collection<SModule> getModules(Deptype depType) {
    Set<SModule> neighbours = collectNeighbours(depType);

    Set<SModule> result = new HashSet<>();
    for (SModule neighbour : neighbours) {
      collect(neighbour, result, depType);
    }

    return result;
  }

  private Set<SModule> collectNeighbours(Deptype depType) {
    HashSet<SModule> result = new HashSet<>();
    for (SModule module : myModules) {
      result.addAll(myUsedModulesCollector.directlyUsedModules(module, myHandler, true, depType.runtimes));
    }
    result.addAll(myModules);
    return result;
  }

  private void collect(SModule current, Set<SModule> result, Deptype depType) {
    if (!result.contains(current)) {
      result.add(current);
      for (SModule m : myUsedModulesCollector.directlyUsedModules(current, myHandler, depType.reexportAll, depType.runtimes)) {
        collect(m, result, depType);
      }
    }
  }

  private static Collection<Language> directlyUsedLanguages(@NotNull SModule module) {
    Set<Language> result = new HashSet<>();
    for (SLanguage language : module.getUsedLanguages()) {
      final SModule sourceModule = language.getSourceModule();
      // respect sourceModule may be null
      if (sourceModule instanceof Language) {
        result.add((Language) sourceModule);
      }
    }
    return result;
  }

  public enum Deptype {
    /**
     * All modules visible from given modules
     * This includes modules from dependencies, transitive, respecting reexports
     * Including initial modules
     */
    VISIBLE(false, false),

    /**
     * All modules required for compilation of given modules
     * This includes visible modules and used language runtimes, respecting reexports
     * Including languages with runtime stub paths
     * Including initial modules
     */
    COMPILE(true, false),

    /**
     * All modules required for execution of given modules
     * This includes transitive closure of visible modules, with no respect for reexports,
     * and runtimes of used languages, not respecting reexports
     * Including languages with runtime stub paths
     * Including initial modules
     */
    EXECUTE(true, true);

    public boolean runtimes;

    public boolean reexportAll;

    Deptype(boolean runtimes, boolean reexportAll) {
      this.runtimes = runtimes;
      this.reexportAll = reexportAll;
    }
  }

  public interface ErrorHandler {
    void depCannotBeResolved(@NotNull SModule module, @NotNull SDependency unresolvableDep);

    void langSourceModuleCannotBeResolved(@NotNull SLanguage languageWithoutSource);

    // FIXME shall we drop this method, it's not invoked from anywhere
    void runtimeDependencyCannotBeFound(@NotNull SLanguage usedLang, @NotNull SModuleReference runtimeRef);

    void runtimeDependencyCannotBeFound(@NotNull SModuleReference runtimeRef);
  }

  public final static ErrorHandler DEFAULT_HANDLER = new PostingWarningsErrorHandler();
}
