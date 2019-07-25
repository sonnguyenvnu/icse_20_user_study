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
package jetbrains.mps.smodel;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.extapi.module.SRepositoryExt;
import jetbrains.mps.library.ModulesMiner.ModuleHandle;
import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.project.DevKit;
import jetbrains.mps.project.Project;
import jetbrains.mps.project.Solution;
import jetbrains.mps.project.structure.modules.DevkitDescriptor;
import jetbrains.mps.project.structure.modules.GeneratorDescriptor;
import jetbrains.mps.project.structure.modules.LanguageDescriptor;
import jetbrains.mps.project.structure.modules.ModuleDescriptor;
import jetbrains.mps.project.structure.modules.SolutionDescriptor;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.ComputeRunnable;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Mediator between API aspects of an SRepository and our implementation aspects, like SRepositoryExt.
 * Use this class to avoid casts to SRepositoryExt
 * IMPORTANT: lifespan of a facade instance shall not last longer than single model read. Implementation
 * may cache values (e.g. model name to model instance) to answer subsequent queries faster, and may not reflect
 * changes made to a repository.
 */
public final class ModuleRepositoryFacade implements CoreComponent {
  private static final Logger LOG = LogManager.getLogger(ModuleRepositoryFacade.class);
  private static ModuleRepositoryFacade INSTANCE;

  // never null, use for all SRepository API methods.
  private final SRepository myRepo;
  // may be null. use only when extended method of SRepositoryExt are needed.
  private final SRepositoryExt myRepoExt;

  /**
   * @deprecated  This class shall cease to be CoreComponent and singleton. Instead, shall be
   * instantiated directly with {@link #ModuleRepositoryFacade(SRepository)} when our implementation code need to deal with repository internals
   * @param repo
   */
  @Deprecated
  public ModuleRepositoryFacade(MPSModuleRepository repo) {
    myRepo = repo;
    myRepoExt = repo;
  }

  public ModuleRepositoryFacade(@NotNull Project mpsProject) {
    this(mpsProject.getRepository());
  }

  /**
   * Some methods of this facade are bound to implementation-specific {@link SRepositoryExt} and {@link MPSModuleOwner} interfaces
   * Unless you use them, you're safe to pass any {@link SRepository} instance here.
   * If, however, you need to register/unregister modules, make sure repository you pass is instance of {@link SRepositoryExt}
   * @param repository container for modules as described above
   */
  // FIXME need to distinguish between uses where regular SRepository is sufficient (like getAllModules(Class)) vs uses
  //       where SRepositoryExt is needed (like register/unregister a module)
  public ModuleRepositoryFacade(@NotNull SRepository repository) {
    myRepo = repository;
    myRepoExt = repository instanceof SRepositoryExt ? ((SRepositoryExt) repository) : null;
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }

    INSTANCE = this;
  }

  @Override
  public void dispose() {
    INSTANCE = null;
  }

  /**
   * Please use one of the constructors instead
   */
  @Deprecated
  public static ModuleRepositoryFacade getInstance() {
    return INSTANCE;
  }

  /**
   * @return repository this facade has been initialized with, never {@code null}
   * @since 2017.2
   */
  public SRepository getRepository() {
    return myRepo;
  }

  public SModule getModule(@NotNull final SModuleReference ref) {
    Computable<SModule> c = () -> myRepo.getModule(ref.getModuleId());
    if (myRepo.getModelAccess().canRead()) {
      return c.compute();
    }
    ComputeRunnable<SModule> r = new ComputeRunnable<>(c);
    myRepo.getModelAccess().runReadAction(r);
    return r.getResult();
  }

  public <T extends SModule> T getModule(SModuleReference ref, Class<T> cls) {
    SModule m = getModule(ref);
    if (!cls.isInstance(m)) {
      return null;
    }
    return (T) m;
  }

  public <T extends SModule> Collection<T> getAllModules(Class<T> cls) {
    List<T> result = new ArrayList<>();
    for (SModule module : myRepo.getModules()) {
      if (cls.isInstance(module)) {
        result.add((T) module);
      }
    }
    return result;
  }

  public <T extends SModule> Collection<T> getModules(MPSModuleOwner moduleOwner, @Nullable Class<T> cls) {
    Set<SModule> modules = myRepoExt.getModules(moduleOwner);
    if (modules == null) {
      return Collections.emptyList();
    }
    if (cls == null || cls == SModule.class) {
//      return new LinkedList<T>().getClass().cast(modules)
      return ((Collection<T>) modules);
    }

    return modules.stream().filter(cls::isInstance).map(cls::cast).collect(Collectors.toList());
  }

  /**
   * @deprecated there could be more than 1 model with the same name, use {@link #getModelsByName(SModelName)} and pick the one you need.
   * This is provisional API to keep all uses of SModelRepository.getModelDescriptor(String) in a single, controlled place.
   * I could had had created ModelRepositoryFacade, similar to this class, however, it seems just too much for a single method that we shall drop anyway.
   * @param modelQualifiedName
   * @return named model
   */
  @Nullable
  @Deprecated
  @ToRemove(version = 2017.3)
  public SModel getModelByName(@Nullable String modelQualifiedName) {
    if (modelQualifiedName == null) {
      return null;
    }
    try {
      Collection<SModel> models = getModelsByName(new SModelName(modelQualifiedName));
      return models.isEmpty() ? null : models.iterator().next();
    } catch (IllegalArgumentException ex) {
      // bad model name, just pretend we didn't find anything
      return null;
    }
  }

  /**
   * Replacement for {@link #getModelByName(String)} that respects the case when models with the same name present (e.g. in different modules).
   * Generally, accessing models by name is nad idea, you shall prefer {@link org.jetbrains.mps.openapi.model.SModelReference} to access specific model.
   * There are certain scenarios, though, when we need to access by name, therefore we keep this utility method in a facade class.
   *
   * Note, implementation of this method is ineffective now, as it iterates over all modules and models of a repository.
   *
   * @param modelName exact name (qualified, with stereotype, if any) of the model to match
   * @return all models with the same exact name, or empty collection if none found
   */
  @NotNull
  public Collection<SModel> getModelsByName(@Nullable SModelName modelName) {
    if (modelName == null) {
      return Collections.emptyList();
    }
    // With parallel streams, beware of model read lock necessary to perform most operations over module/model (but not #getName())
    // module.models needs read lock, hence no parallel streams here
    Stream<SModule> moduleStream = StreamSupport.stream(myRepo.getModules().spliterator(), false);
    Stream<SModel> modelStream = moduleStream.flatMap(m -> StreamSupport.stream(m.getModels().spliterator(), false));
    return modelStream.filter(m -> modelName.equals(m.getName())).collect(Collectors.toList());
  }

  /**
   * <p>
   * Use <b>only</b> if there is no way to use {@link ModuleRepositoryFacade#getModule(org.jetbrains.mps.openapi.module.SModuleReference)}
   * or {@link SRepository#getModule(org.jetbrains.mps.openapi.module.SModuleId)}.<br/>
   * For example:
   * <ul>
   * <li>check for existing modules with the same name on new module creation</li>
   * <li>search module by name in user search dialog/popup</li>
   * </ul>
   * </p>
   *
   * @return collection of modules which names are equal to the given module name.
   * A repository is able to have several modules for a given module name.
   * Empty collection is returned iff there are no such modules in the repository.
   * @since 2017.2
   */
  @NotNull
  public Collection<SModule> getModulesByName(@NotNull String moduleName) {
    // parallel == true as we are going to check module name, which doesn't require model read.
    return StreamSupport.stream(myRepo.getModules().spliterator(), true)
                        .filter(module -> moduleName.equals(module.getModuleName()))
                        .collect(Collectors.toList());
  }

  /**
   * Provisional API while we migrate from singleton SModelRepository.
   * Likely, SModelRepository would become a view of an SRepository, giving access to models and bulk operations for the set of models.
   * @return snapshot state of the models available in the repository
   */
  public Collection<SModel> getAllModels() {
    return SModelRepository.getInstance().getModelDescriptors();
  }

  /**
   * Find language modules directly <em>extending</em> the one supplied.
   * There's {@link Language#getAllExtendedLanguages()} <em>extended</em> languages.
   * Though it's stupid to keep two locations, this method didn't relocate next to it as there are no uses in MPS and it shall cease to exist
   * @deprecated If there's need for extending language, shall add <code>Language.getDirectlyExtendingLanguage</code>.
   * There's single use in mbeddr.
   */
  @Deprecated
  @ToRemove(version = 3.4)
  public Collection<Language> getAllExtendingLanguages(Language l) {
    final SModuleReference lRef = l.getModuleReference();
    List<Language> result = new LinkedList<>();
    for (Language lang : getAllModules(Language.class)) {
      if (lang.getExtendedLanguageRefs().contains(lRef)) {
        result.add(lang);
      }
    }
    return result;
  }

  public void unregisterModules(MPSModuleOwner owner) {
    // if unregistering modules one by one is not fast enough, we shall come up with appropriate SRepositoryExt API
    final ArrayList<SModule> modules = new ArrayList<>(myRepoExt.getModules(owner));
    // XXX Here comes code complimentary to ModuleRepositoryFacade.newGeneratorInstance.
    //     I.e. we unregister not directly owned generators only (getOwnedGenerators()), but all generators associated with the language.
    //     Once we have FCC generator modules, we would switch to getOwnedGenerators here
    final ArrayList<Generator> associatedGenerators = new ArrayList<>();
    for (SModule m : modules) {
      if (m instanceof Language && myRepoExt.getOwners(m).size() == 1) {
        // Language module with single owner that is about to be unregistered drags all available generators with it.
        // As long as there's another owner for the language, generators may stay (Language object would persist, only owner association would get unlinked)
        associatedGenerators.addAll(((Language) m).getGenerators());
      }
    }
    // Besides, we have to be careful not to unregister same module twice (i.e. if both language and its generator are owned by same owner, they
    // both are in 'modules' list and are disposed in the main loop.
    associatedGenerators.removeAll(modules);
    // Modules in associatedGenerators have owners different from the one supplied
    // Given no mechanism to have generators w/o a language, we have to dispose all of them from any owner
    for (Generator g : associatedGenerators) {
      unregisterModule(g);
    }

    for (SModule m : modules) {
      // XXX perhaps, shall check m is still registered just in case any module unregisters its related modules (e.g. Language may unregister its Generators)
      //     though, not clear how to do it gracefully, m.getRepo == null? I wonder to see if we can obtain disposed modules through myRepoExt.getModules()
      //     I assume earlier unregister have removed the module from the list and we don't need to care about unregistered modules here.
      myRepoExt.unregisterModule(m, owner);
    }
  }

  //intended to use only when module is removed physically
  //AP: why?
  /**
   * unregisters module from all its owners
   */
  public void unregisterModule(@NotNull SModule module) {
    for (MPSModuleOwner owner : getModuleOwners(module)) {
      myRepoExt.unregisterModule(module, owner);
    }
  }

  public Set<MPSModuleOwner> getModuleOwners(SModule module) {
    return new HashSet<>(myRepoExt.getOwners(module));
  }

  /**
   * Instantiate a new module according to description and register it with the facade's repository.
   * If there's module already (expected scenario), just updates its relation to another {@linkplain MPSModuleOwner module owner}
   *   (same module could get published with few owners)
   * @return instance of a module, either new one or existing from the facade's repository.
   * @throws IllegalArgumentException if handle describes unknown module kind.
   */
  @NotNull
  public SModule instantiateModule(@NotNull ModuleHandle handle, @NotNull MPSModuleOwner owner) {
    LOG.debug("Creating a module " + handle);
    ModuleDescriptor moduleDescriptor = handle.getDescriptor();
    AbstractModule instance;
    // XXX left distinct one-liner newXXXInstance methods as a hint for future API (e.g. protected; separate module factory and
    //     registration, for use e.g. in tests). Besides, there's little reason to propagate ModuleHandle there (in fact, it's too much even here - why
    //     do I care modules are instantiated with the help of ModulesMiner). Check TestLanguage for sample case.
    if (moduleDescriptor instanceof LanguageDescriptor) {
      instance = newLanguageInstance((LanguageDescriptor) moduleDescriptor, handle.getFile());
    } else if (moduleDescriptor instanceof SolutionDescriptor) {
      instance = newSolutionInstance((SolutionDescriptor) moduleDescriptor, handle.getFile());
    } else if (moduleDescriptor instanceof DevkitDescriptor) {
      instance = newDevKitInstance((DevkitDescriptor) moduleDescriptor, handle.getFile());
    } else if (moduleDescriptor instanceof GeneratorDescriptor) {
      instance = newGeneratorInstance((GeneratorDescriptor) moduleDescriptor, handle.getFile());
    } else {
      throw new IllegalArgumentException("Unknown module " + handle.getFile().getName());
    }
    AbstractModule actualRepoModule = registerModule(instance, owner);
    return actualRepoModule;
  }

  /**
   * @deprecated use instance counterpart {@link #instantiateModule(ModuleHandle, MPSModuleOwner)} instead.
   */
  @Deprecated
  @ToRemove(version = 3.5)
  public static SModule createModule(ModuleHandle handle, MPSModuleOwner owner) {
    // 2 uses in mbeddr.
    // need to introduce alternative with ModuleDescriptor only, not ModuleHandler/IFile
    return INSTANCE.instantiateModule(handle, owner);
  }

  @NotNull
  private Language newLanguageInstance(@NotNull LanguageDescriptor descriptor, IFile descriptorFile) {
    assert descriptor.getId() != null;
    return new Language(descriptor, descriptorFile);
  }

  @NotNull
  private Solution newSolutionInstance(@NotNull SolutionDescriptor descriptor, IFile descriptorFile) {
    assert descriptor.getId() != null;
    return new Solution(descriptor, descriptorFile);
  }

  @NotNull
  private DevKit newDevKitInstance(@NotNull DevkitDescriptor descriptor, IFile descriptorFile) {
    assert descriptor.getId() != null;
    return new DevKit(descriptor, descriptorFile);
  }

  @NotNull
  private Generator newGeneratorInstance(@NotNull GeneratorDescriptor descriptor, IFile descriptorFile) {
    SModule module = myRepo.getModule(descriptor.getSourceLanguage().getModuleId());
    if (module == null) {
      // XXX for the time being, we register generator modules only *after* respective source language module, although
      //     generally we shall not insist on the ordering (generator could obtain source language lazily, not at construction time,
      //     or we can make up a proxy Language instance, and replace it with real once proper module comes to the repository).
      // XXX FWIW, MPSModuleRepository.unregisterModule and unregisterModules keep symmetric knowledge what generator modules to remove along with the language
      //     i.e. here we assume there could be no generator module w/o source language, there we remove all generators with the given source language (not
      //     'directly owned' only).
      String msg =
          String.format("Can't register generator %s for not yet known language module %s", descriptor.getNamespace(), descriptor.getSourceLanguage());
      throw new IllegalStateException(msg);
    }
    if (false == module instanceof Language) {
      String msg = String.format("Module %s specified as source language of generator %s in not a Language module", descriptor.getSourceLanguage(), descriptor.getNamespace());
      throw new IllegalStateException(msg);
    }
    return new Generator(MetaAdapterFactory.getLanguage(descriptor.getSourceLanguage()), descriptor, descriptorFile, (Language) module);
  }

  private <T extends AbstractModule> T registerModule(T module, MPSModuleOwner moduleOwner) {
    return myRepoExt.registerModule(module, moduleOwner);
  }
}
