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
package jetbrains.mps.scope;

import jetbrains.mps.project.dependency.GlobalModuleDependenciesManager;
import jetbrains.mps.project.dependency.GlobalModuleDependenciesManager.Deptype;
import jetbrains.mps.smodel.BaseScope;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.util.IterableUtil;
import jetbrains.mps.util.iterable.MergeIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link org.jetbrains.mps.openapi.module.SearchScope} implementation that uses
 * {@link jetbrains.mps.project.dependency.GlobalModuleDependenciesManager.Deptype#VISIBLE 'visible'} module dependencies to build a scope.
 * <p>
 * Since this class deals with {@code SModule} and {@code SModel}, appropriate model access (at least read) is expected from the caller.
 * </p>
 * <p>
 * Note, this class is stateful and doesn't track changes to initial modules, though there's no obligation it's a snapshot either.
 * At the moment, set of modules is fixed at init time, while set of models is dynamic. This is not given, implementation may change at any moment.
 * Likely, we will cache everything at init time (depending on observed performance).
 * </p>
 * {@implNote The reason I don't want to keep scope instance along with an AbstractModule is that there's little value in caching these scopes.
 * We need to invalidate them in bulk on any module change anyway. Most usage scenarios obtain scope instance, perform some activities and
 * abandon it afterwards, therefore stateful instance if perfectly fine.
 *
 * @author Artem Tikhomirov
 * @since 2018.2
 */
public class VisibleDepsSearchScope extends BaseScope {
  @Nullable
  private SRepository myRepository;
  private final Set<SModule> myModules;
  private final Set<SModel> myAccessoriesOfUsedLanguages;

  /**
   * @param repository repository to look up dependencies (dependant modules and accessory models of used languages) in. If {@code null},
   *                   scope consists of a module itself only.
   * @param module     source of dependencies to scan
   */
  public VisibleDepsSearchScope(@Nullable SRepository repository, @NotNull SModule module) {
    // XXX for a Generator module, is module.getUsedLanguages() sufficient? Shall we include its source language explicitly (just in case it's not
    // referenced from a template model. Likely, Generator.getUsedLanguages() shall not report anything not in real use, hence for empty template model
    // this scope may lack accessories of a source language, which is not nice).
    this(repository, Collections.singleton(module), module.getUsedLanguages());
  }

  public VisibleDepsSearchScope(@Nullable SRepository repository, Collection<? extends SModule> modules, Collection<SLanguage> usedLanguages) {
    myRepository = repository;
    // XXX not sure who's responsibility is to look at 'used devkits' and take their exported solutions. It depends whether we treat them 'visible' or not.
    myModules = IterableUtil.asSet(new GlobalModuleDependenciesManager(modules).getModules(Deptype.VISIBLE));

    // accessory models of a language module are part of that module scope
    Set<SModel> accessoryModels = new HashSet<>();
    for (SModule m : modules) {
      if (m instanceof Language) {
        accessoryModels.addAll(((Language) m).getAccessoryModels());
      }
    }

    if (repository != null) {
      for (SLanguage l : usedLanguages) {
        SModuleReference smr = l.getSourceModuleReference();
        if (smr == null) {
          continue;
        }
        SModule m = smr.resolve(repository);
        if (m instanceof Language) {
          accessoryModels.addAll(((Language) m).getAccessoryModels());
        }
      }
    }
    myAccessoriesOfUsedLanguages = accessoryModels;
  }

  @NotNull
  @Override
  public Iterable<SModule> getModules() {
    return myModules;
  }

  @NotNull
  @Override
  public Iterable<SModel> getModels() {
    return new MergeIterator<>(super.getModels(), myAccessoriesOfUsedLanguages);
  }

  @Override
  public SModule resolve(@NotNull SModuleReference reference) {
    if (myRepository == null) {
      return super.resolve(reference);
    }

    SModule resolved = reference.resolve(myRepository);
    return myModules.contains(resolved) ? resolved : null;
  }

  @Override
  public SModel resolve(@NotNull SModelReference reference) {
    if (myRepository == null) {
      return super.resolve(reference);
    }

    SModel resolved = reference.resolve(myRepository);
    if (resolved == null) {
      return null;
    }
    return myModules.contains(resolved.getModule()) || myAccessoriesOfUsedLanguages.contains(resolved) ? resolved : null;
  }
}
