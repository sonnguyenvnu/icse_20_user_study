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
package jetbrains.mps.make;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static jetbrains.mps.project.SModuleOperations.isCompileInMps;

/**
 * sources saving and other utility methods are here
 */
final class ModulesContainer {
  private final Set<SModule> myModules;
  private final Dependencies myDependencies;
  private final Map<SModule, ModuleSources> myModuleSources;
  private final Map<String, SModule> myClassName2ModuleMap = new HashMap<>();

  public ModulesContainer(Set<SModule> modules, Dependencies dependencies) {
    this(modules, dependencies, new HashMap<>());
  }

  private ModulesContainer(Set<SModule> modules, Dependencies dependencies, Map<SModule, ModuleSources> moduleSources) {
    myModules = modules;
    myDependencies = dependencies;
    myModuleSources = moduleSources;
  }

  public ModuleSources getSources(@NotNull SModule module) {
    ModuleSources moduleSources = myModuleSources.get(module);
    if (moduleSources == null) {
      moduleSources = new ModuleSources(module, myModuleSources, myDependencies);
      myModuleSources.put(module, moduleSources);
    }
    return moduleSources;
  }

  public boolean hasModuleToCompile() {
    return myModules.stream().anyMatch(module -> !isExcluded(module));
  }

  /**
   * @return collection of module and their sources, subset of {@link #getModules()}, only those that are stale.
   */
  public Collection<ModuleSources> getDirtyModuleSources() {
    ArrayList<ModuleSources> rv = new ArrayList<>(myModules.size());
    for (SModule m : myModules) {
      if (isDirty(m)) {
        rv.add(getSources(m));
      }
    }
    return rv;
  }

  /**
   * @return {@code true} if module got source files and these are stale
   */
  private boolean isDirty(@NotNull SModule m) {
    if (isExcluded(m)) {
      return false;
    }
    return !getSources(m).isUpToDate();
  }

  public Set<SModule> getModules() {
    return myModules;
  }

  /**
   * @param modules have to be a subset of {@link #getModules()}
   * @return an instance restricted to set of speficied modules
   */
  public ModulesContainer restricted(@NotNull Set<SModule> modules) {
    if (!myModules.containsAll(modules)) {
      // well, technically, #getSources() in its present state would deal nice with the situation.
      // however, I'd like to see my assumptions to fail fast.
      throw new IllegalArgumentException("Attempt to restrict a container to modules that are not part of it");
    }
    // intention is to share ModuleSource instance and any files/data it has collected so far
    return new ModulesContainer(modules, myDependencies, myModuleSources);
  }

  void putClassForModule(@NotNull String className, @NotNull SModule module) {
    myClassName2ModuleMap.put(className, module);
  }

  SModule getModuleContainingClass(@NotNull String containerClassName) {
    return myClassName2ModuleMap.get(containerClassName);
  }

  public static boolean isExcluded(@NotNull SModule m) {
    return m.isReadOnly() || !isCompileInMps(m);
  }
}
