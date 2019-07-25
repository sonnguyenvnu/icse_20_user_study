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
package jetbrains.mps.project;

import jetbrains.mps.components.ComponentHost;
import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.extapi.module.SRepositoryExt;
import jetbrains.mps.extapi.module.SRepositoryRegistry;
import jetbrains.mps.project.structure.project.ModulePath;
import jetbrains.mps.project.structure.project.ProjectDescriptor;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.MPSModuleRepository;
import jetbrains.mps.util.annotation.ToRemove;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.ImmutableReturn;
import org.jetbrains.mps.annotations.Internal;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleListenerBase;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * MPS Project basic implementation.
 * Stores a set of modules.
 * Set of modules coincide with the modules in the underlying repository.
 * Supported always by a {@link ProjectDescriptor} which stores paths to the module descriptors
 * Doesn't manage lifecycle of a module descriptors other than "{@linkplain #update() update} 'em all" on demand.
 * Check {@code ModuleFileChangeListener} of [mps-platform] for change tracking.
 * However, tracks module renames (albeit in a bit weird way) to keep inner structures fit.
 *
 * This project is tied to MPS platform and gives access to MPS core platform and components it comprises.
 *
 * FIXME
 * poor architecture results in the intertwined control flow between ProjectBase, ModuleLoader and ProjectDescriptor
 * I guess that removing virtual folders solves all the problem
 *
 * @see ProjectDescriptor
 */
public abstract class ProjectBase extends Project {
  private static final Logger LOG = LogManager.getLogger(ProjectBase.class);
  private final ProjectManager myProjectManager = ProjectManager.getInstance();

  private final Map<SModuleReference, SModuleListenerBase> myModulesListeners = new HashMap<>();
  protected final ComponentHost myPlatform;

  // AP fixme must be final, however StandaloneMpsProject exposes it (a client can publicly reset the project descriptor)
  protected ProjectDescriptor myProjectDescriptor;
  // contract : each project module must have a corresponding ModulePath in this map
  private final Map<SModuleReference, ModulePath> myModuleToPathMap = new LinkedHashMap<>();
  private final ProjectModuleLoader myModuleLoader;

  protected ProjectBase(@NotNull ProjectDescriptor projectDescriptor, @NotNull ComponentHost mpsPlatform) {
    this(projectDescriptor, mpsPlatform, false);
    ProjectRepository r = new ProjectRepository(this, mpsPlatform.findComponent(MPSModuleRepository.class), mpsPlatform.findComponent(SRepositoryRegistry.class));
    r.init();
    initRepository(r);
  }

  // FIXME refactor other subclasses and pass boolean initDefaultRepo == true|false
  protected ProjectBase(@NotNull ProjectDescriptor projectDescriptor, @NotNull ComponentHost mpsPlatform, boolean unusedJustIndicatorOfNoRepository) {
    super(projectDescriptor.getName());
    myProjectDescriptor = projectDescriptor;
    myModuleLoader = new ProjectModuleLoader(this); // fixme: avoid
    myPlatform = mpsPlatform;
  }

  @NotNull
  public String getErrors() {
    return myModuleLoader.getErrors();
  }

  @Nullable
  protected final ModulePath getPath(@NotNull SModule module) {
    return getPath(module.getModuleReference());
  }

  @Nullable
  final ModulePath getPath(@NotNull SModuleReference mRef) {
    return myModuleToPathMap.get(mRef);
  }

  final boolean containsPath(@NotNull ModulePath modulePath) {
    return myModuleToPathMap.containsValue(modulePath);
  }

  /**
   * This is auxiliary method to update ProjectBase internal state. When a new module is added to a project,
   * use {@code {@link #addModule(SModule)}}, which records the module into persistent project descriptor as well.
   * There is no change in the descriptor and interaction with the loading/saving descriptor logic (ModuleLoader)
   *
   * @deprecated there is an intention to deduce virtual folders from the file system directly
   */
  @ToRemove(version = 3.5)
  @Deprecated
  @Internal
  /*package*/ final void addModule0(@NotNull ModulePath path, @NotNull SModule module) {
    if (myModuleToPathMap.containsKey(module.getModuleReference())) {
//      throw new IllegalArgumentException(module + " is already in the " + this); todo enable after MPS-24400
      LOG.warn(module + " is already in " + this);
      return;
    }
    SRepositoryExt repository = (SRepositoryExt) getRepository();
    repository.getModelAccess().runWriteAction(() -> repository.registerModule(module, this));
    myModuleToPathMap.put(module.getModuleReference(), path);
    addRenameListener(module);
  }

  @Override
  public final void addModule(@NotNull SModule module) {
    IFile descriptorFile = getDescriptorFileChecked(module);
    if (descriptorFile != null) {
      ModulePath path = new ModulePath(descriptorFile.getPath(), null);
      addModule0(path, module);
      myProjectDescriptor.addModulePath(path);
      myModuleLoader.fireModuleLoaded(path, module);
    }
  }

  private void addRenameListener(@NotNull SModule module) {
    if (module instanceof AbstractModule) {
      // ModuleRenameListener doesn't tolerate anything but AbstractModule. Not well-mannered, imo.
      ModuleRenameListener listener = new ModuleRenameListener();
      myModulesListeners.put(module.getModuleReference(), listener);
      module.addModuleListener(listener);
    }
  }

  /**
   * force removal of the module from the project
   */
  @Override
  public final void removeModule(@NotNull SModule module) {
    if (!myModuleToPathMap.containsKey(module.getModuleReference())) {
      LOG.warn("Module has not been registered in the project: " + module);
      return;
    }
    final ModulePath modulePath = removeModule0(module);
    myModuleLoader.fireModuleRemoved(modulePath, module);
    myProjectDescriptor.removeModulePath(modulePath);
  }

  /**
   * Method which intent is to update only the module <-> virtual path map
   * and remove the module from the repository but not to touch the project descriptor
   *
   * @see #addModule0(ModulePath, SModule)
   */
  @Internal
  /*package*/ final ModulePath removeModule0(@NotNull SModule module) {
    final ModulePath modulePath = myModuleToPathMap.remove(module.getModuleReference());
    assert modulePath != null;
    SModuleListenerBase remove = myModulesListeners.remove(module.getModuleReference());
    module.removeModuleListener(remove);
    SRepositoryExt repository = (SRepositoryExt) getRepository();
    repository.getModelAccess().runWriteAction(() -> {
      if (module instanceof Language) {
        // At the moment, project doesn't notice Generator modules, and expects them to be part of Language
        // E.g. ProjectModulesFiller doesn't tell project to addModule(Generator). However, with Language no longer owner for its Generatorsm
        // we have to unregister them explicitly (like ModuleRepositoryFacade does)
        // For reasons why we unregister all generators, not owned only, see ModuleRepositoryFacade.
        Collection<Generator> allKnownLangGenerators = ((Language) module).getGenerators();
        for (Generator g : allKnownLangGenerators) {
          repository.unregisterModule(g, this);
        }
      }
      repository.unregisterModule(module, this);
    });
    return modulePath;
  }

  @Nullable
  private IFile getDescriptorFileChecked(SModule module) {
    IFile descriptorFile = ((AbstractModule) module).getDescriptorFile();
    if (descriptorFile == null) {
      LOG.warn("Descriptor file path is null in the module " + module);
      return null;
    }
    return descriptorFile;
  }

  @NotNull
  @ImmutableReturn
  public final List<SModule> getProjectModules() {
    List<SModule> result = new ArrayList<>();
    SRepository repository = getRepository();
    repository.getModelAccess().runReadAction(() -> {
      for (SModuleReference mRef : myModuleToPathMap.keySet()) {
        SModule resolved = mRef.resolve(repository);
        if (resolved != null) {
          result.add(resolved);
        } else {
          LOG.error("Module " + mRef + " is not found in the project repository", new Throwable());
        }
      }
    });
    return Collections.unmodifiableList(result);
  }

  /**
   * persists the state of the project to the disk
   */
  public abstract void save();

  // AP: todo make final
  protected void update() {
    getModelAccess().runWriteAction(() -> {
      loadModules();
      fireModulesLoaded();
    });
  }

  /**
   * AP todo : this logic must be redone alongside with filling the SLibraries with modules.
   * filling libraries and projects with modules externally seems to me the best solution
   */
  private void loadModules() {
    getModelAccess().checkWriteAccess();
    myModuleLoader.updatePathsInProject(myProjectDescriptor.getModulePaths());
  }

  private void fireModulesLoaded() {
    getModelAccess().checkWriteAccess();
    //  TODO FIXME get rid of onModuleLoad
    for (SModule m : getProjectModulesWithGenerators()) {
      if (m instanceof AbstractModule) {
        ((AbstractModule) m).onModuleLoad();
      }
    }
  }

  /**
   * these are our own project opened/closed events.
   * in the case of idea platform presence they are triggered from the corresponding idea project opened/closed events.
   * in the other case they are triggered at the init/dispose methods
   */
  public void projectOpened() {
    LOG.info("Project '" + getName() + "' is opened");
    myProjectManager.projectOpened(this);
  }

  public void projectClosed() {
    checkNotDisposed();
    LOG.info("Project '" + getName() + "' is closing");
    myProjectManager.projectClosed(this);
    getRepository().getModelAccess().runWriteAction(() -> getProjectModules().forEach(this::removeModule));
  }

  @Override
  public boolean isOpened() {
    return ProjectManager.getInstance().getOpenedProjects().contains(this);
  }

  @NotNull
  public String toString() {
    return "MPS Project [" + getName() + (isDisposed() ? ", disposed]" : "]");
  }

  /**
   * Access components that constitute core of MPS platform.
   */
  public final ComponentHost getPlatform() {
    return myPlatform;
  }

  @Override
  public <T> T getComponent(Class<T> cls) {
    if (CoreComponent.class.isAssignableFrom(cls)) {
      return cls.cast(myPlatform.findComponent(cls.asSubclass(CoreComponent.class)));
    }
    return null;
  }

  /**
   * calls {@link ProjectDataSource#loadDescriptor()} and set the new project descriptor
   * makes sense to use this method with the {@link #update()} together
   * to avoid the inconsistency between the project modules and the descriptor state.
   */
  protected final void loadDescriptor(@NotNull ProjectDataSource dataSource) {
    checkNotDisposed();
    myProjectDescriptor = dataSource.loadDescriptor();
  }

  // Used to live in StandaloneMPSProject. I don't see why it's restricted to that one, provided any
  // ProjectBase derivative knows about ModulePath and its virtual folder.
  protected void setVirtualFolder(@NotNull SModule module, String newFolder) {
    // TODO: remove duplication of ModulePath in ProjectBase.myModuleToPathMap to avoid handling both lists
    ModulePath modulePath = getPath(module);
    if (modulePath != null) {
      ModulePath newPath = modulePath.withVirtualFolder(newFolder);
      myProjectDescriptor.replacePath(modulePath, newPath);
      myModuleToPathMap.put(module.getModuleReference(), newPath);
    } else {
      LOG.warn("Could not set virtual folder for the module " + module + ", module could not be found");
    }
  }

  public final void addListener(@NotNull ProjectModuleLoadingListener listener) {
    myModuleLoader.addListener(listener);
  }

  public final void removeListener(@NotNull ProjectModuleLoadingListener listener) {
    myModuleLoader.removeListener(listener);
  }

  // XXX use of SModule listener to detect renames smells wrong. I'd say Project shall deal with files, on a lower level than SRepository.
  //     Perhaps, this comes along missing file rename event from FileListener?
  // AP I think that there must be no rename listening at all. It is a pure UI clients' desire to have the *opened* projects
  // updated to the renaming of the module
  private class ModuleRenameListener extends SModuleListenerBase {
    @Override
    public void moduleRenamed(@NotNull SModule module, @NotNull SModuleReference oldRef) {
      // why exceptions, why so intolerable? Just because we added the listener to a module with file?
      if (!(module instanceof AbstractModule)) {
        throw new IllegalArgumentException("Support only abstract module here " + module);
      }
      ModulePath oldPath = myModuleToPathMap.remove(module.getModuleReference());
      IFile descriptorFile = ((AbstractModule) module).getDescriptorFile();
      if (descriptorFile == null) {
        throw new IllegalArgumentException("The descriptor file is null " + module);
      }
      ModulePath newPath = new ModulePath(descriptorFile.getPath(), oldPath.getVirtualFolder());
      myProjectDescriptor.replacePath(oldPath, newPath);
      myModuleToPathMap.put(module.getModuleReference(), newPath);
    }
  }
}
