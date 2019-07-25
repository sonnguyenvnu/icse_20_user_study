/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.classloading;

import jetbrains.mps.classloading.ModuleUpdater.SearchError;
import jetbrains.mps.module.ReloadableModule;
import jetbrains.mps.util.annotation.Hack;
import jetbrains.mps.util.annotation.ToRemove;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;
import org.jetbrains.mps.openapi.module.SDependency;
import org.jetbrains.mps.openapi.module.SDependencyScope;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.util.Condition;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static jetbrains.mps.classloading.ModulesWatcher.DefaultStatuses.INVALID_NOT_LOADABLE;
import static jetbrains.mps.classloading.ModulesWatcher.DefaultStatuses.INVALID_NO_RECORD;
import static jetbrains.mps.classloading.ModulesWatcher.DefaultStatuses.SIMPLY_INVALID;
import static jetbrains.mps.classloading.ModulesWatcher.DefaultStatuses.VALID;

/**
 * This class watches all the reloadable modules, which satisfy #myWatchableCondition in the repository and dependencies between them.
 * It aims to store a status for each tracked module
 * @see jetbrains.mps.classloading.ModulesWatcher.ClassLoadingStatus
 * and to return all compile depedencies of module within repository
 * @see #getDependencies(Iterable)
 * Also it keeps a dependency graph to be able to calculate back dependencies for any module
 * @see #getBackDependencies(Iterable)
 *
 * Note: due to the lazy implementation of module unloading, there is a possible situation,
 * when there are some disposed modules in ModulesWatcher.
 * We may be asked about their dependencies etc. Therefore <code>ModulesWatcher</code> tracks references to modules not modules themselves.
 * The add/remove/update module methods are triggered from above. This class updates its state accordingly.
 *
 * A lazy mechanism is used here: when the state is 'dirty', refresh happens at any request.
 * @see #recountStatus()
 *
 * Notice, that read action is required on every update.
 *
 * @see ClassLoaderManager#myLoadableCondition
 * @see ClassLoaderManager#myWatchableCondition
 */
public class ModulesWatcher {
  private static final Logger LOG = LogManager.getLogger(ModulesWatcher.class);

  private final Object myStatusMapLock = new Object();

  private final SRepository myRepository;
  private final Map<SModuleReference, ClassLoadingStatus> myStatusMap = new HashMap<>();
  private final ReferenceStorage<ReloadableModule> myRefStorage = new ReferenceStorage<>();
  private final ModuleUpdater myModuleUpdater;

  public ModulesWatcher(SRepository repository, final Condition<ReloadableModule> watchableCondition) {
    myRepository = repository;
    myModuleUpdater = new ModuleUpdater(repository, watchableCondition, myRefStorage);
  }

  private void update() {
    myRepository.getModelAccess().checkReadAccess();
    if (isChanged()) {
      recountStatus();
    }
  }

  /**
   * @param mRef is a module reference. <code>ModulesWatcher</code> maintains references, not modules themselves.
   * @return the status for the given module reference
   * @see jetbrains.mps.classloading.ModulesWatcher.ClassLoadingStatus
   */
  @NotNull
  public ClassLoadingStatus getStatus(@NotNull SModuleReference mRef) {
    if (isChanged()) {
      LOG.debug("The class loading status info might be outdated");
    }
    if (!myModuleUpdater.contains(mRef)) {
      return INVALID_NOT_LOADABLE;
    } else {
      synchronized (myStatusMapLock) {
        if (!myStatusMap.containsKey(mRef)) {
          LOG.warn("No classloading status is found for the module " + mRef);
          return INVALID_NO_RECORD;
        } else {
          return myStatusMap.get(mRef);
        }
      }
    }
  }

  public void updateModules(@NotNull Collection<? extends ReloadableModule> modules) {
    if (modules.isEmpty()) return;
    myModuleUpdater.updateModules(modules);
    update();
  }

  public void addModules(@NotNull Collection<? extends ReloadableModule> modules) {
    if (modules.isEmpty()) return;
    myModuleUpdater.addModules(modules);
    update();
  }

  public void removeModules(@NotNull Collection<? extends SModuleReference> mRefs) {
    if (mRefs.isEmpty()) return;
    myModuleUpdater.removeModules(mRefs);
    update();
  }

  /**
   * recounting the status map
   * @see #isChanged()
   */
  private void recountStatus() {
    LOG.debug("Recount status map for modules");
    myModuleUpdater.refreshGraph();
    refillStatusMap(findInvalidModules());
    LOG.debug("Finished recounting");
  }

  /**
   * costly because of backDeps request
   */
  private void refillStatusMap(Collection<? extends SModuleReference> invalidModules) {
    synchronized (myStatusMapLock) {
      myStatusMap.clear();
      for (SModuleReference mRef : getAllModules()) {
        myStatusMap.put(mRef, VALID);
      }
      Collection<? extends SModuleReference> allInvalidModules = getBackDependencies(invalidModules);
      for (SModuleReference mRef : allInvalidModules) {
        myStatusMap.put(mRef, SIMPLY_INVALID);
        if (LOG.isTraceEnabled()) {
          Collection<SModuleReference> dependencies = getDependencies(Collections.singleton(mRef));
          for (SModuleReference depRef : dependencies) {
            if (invalidModules.contains(depRef)) {
              LOG.trace("The module " + mRef + " is invalid since it has a transitive dependency on the module " + depRef);
            }
          }
        }
      }
      String message = invalidModules.size() + " modules are marked as invalid roots for class loading out of " + getAllModules().size() +
                       " modules [totally in the repository]:";
      if (invalidModules.isEmpty()) {
        LOG.info(message);
      } else {
        LOG.warn(message);
      }
      print(invalidModules);
      LOG.info("Totally " + allInvalidModules.size() + " modules are marked invalid for class loading:");
      print(allInvalidModules);

      checkStatusMapCorrectness();
    }
  }

  private void print(Collection<? extends SModuleReference> modules) {
    for (SModuleReference ref : modules) {
      LOG.info(ref);
    }
  }

  /**
   * Note: here we are interested in the actual status of module. (not {@link ReferenceStorage#resolveRef})
   * if it has been already disposed but still remains in our graphs (i.e. ClassLoader is not disposed yet [!]),
   * we need to mark it invalid
   */
  private boolean isModuleDisposed(SModuleReference mRef) {
    SModule resolvedModule = mRef.resolve(myRepository);
    return (resolvedModule == null || resolvedModule.getRepository() == null);
  }

  @Nullable
  private ReloadableModule resolveRef(SModuleReference ref) {
    return myRefStorage.resolveRef(ref);
  }

  private Collection<SModuleReference> findInvalidModules() {
    return findInvalidModules0(false).keySet();
  }

  @TestOnly
  Map<SModuleReference, String> findInvalidModulesProblems() {
    return findInvalidModules0(true);
  }

  @NotNull
  private Map<SModuleReference, String> findInvalidModules0(boolean errorLevel) {
    myRepository.getModelAccess().checkReadAccess();

    Map<ReloadableModule, List<SearchError>> modulesWithAbsentDeps = myModuleUpdater.getModulesWithAbsentDeps();
    Map<SModuleReference, String> mRefToProblem = new HashMap<>();
    Collection<? extends SModuleReference> allModuleRefs = getAllModules();
    for (SModuleReference mRef : allModuleRefs) {
      if (!mRefToProblem.containsKey(mRef)) {
        String msg = getModuleProblemMessage(mRef, modulesWithAbsentDeps);
        if (msg == null) {
          continue;
        }
        if (errorLevel) LOG.error(msg); else LOG.debug(msg);
        mRefToProblem.put(mRef, msg);
      }
    }
    return mRefToProblem;
  }

  /**
   * @return message with the problem description or null if the module is valid
   */
  @Nullable
  @Hack
  private String getModuleProblemMessage(SModuleReference mRef, Map<ReloadableModule, List<SearchError>> modulesWithAbsentDeps) {
    assert !isChanged();
    if (isModuleDisposed(mRef)) {
      return String.format("Module %s is disposed and therefore was marked invalid for class loading", mRef.getModuleName());
    }

    ReloadableModule module = (ReloadableModule) mRef.resolve(myRepository);
    assert module != null;

    // FIXME does not work for now, enable in the 3.4
    if (modulesWithAbsentDeps.containsKey(module)) {
      List<SearchError> errors = modulesWithAbsentDeps.get(module);
      return String.format("%s has got an absent dependency problem and therefore was marked invalid for class loading: %s", module, errors.get(0).getMsg());
    }
    for (SDependency dep : module.getDeclaredDependencies()) {
      if (dep.getScope() == SDependencyScope.DESIGN || dep.getScope() == SDependencyScope.GENERATES_INTO) {
        continue;
      }
      if (isModuleDisposed(dep.getTargetModule())) {
        return String.format("%s depends on a disposed module %s and therefore was marked invalid for class loading", module, dep.getTargetModule());
      }
    }
    return null;
  }

  private void checkStatusMapCorrectness() {
    assert myStatusMap.size() == getAllModules().size() : "Modules number inconsistency";
    for (SModuleReference mRef : getAllModules()) {
      ClassLoadingStatus status = VALID;
      for (SModuleReference mRef1 : getDependencies(Collections.singleton(mRef))) {
        ClassLoadingStatus status1 = getStatus(mRef1);
        if (!status1.isValid()) {
          status = status1;
        }
      }
      if (status != getStatus(mRef)) {
        throw new IllegalStateException("Status is wrong for the module " + mRef);
      }
    }
  }

  Collection<? extends SModuleReference> getAllModules() {
    return myModuleUpdater.getModules();
  }

  /**
   * @return all dependencies of this module (closed set under dependency-relation)
   */
  public Collection<SModuleReference> getDependencies(Iterable<? extends SModuleReference> mRefs) {
    return myModuleUpdater.getDeps(mRefs);
  }

  Collection<ReloadableModule> getResolvedDependencies(Iterable<? extends ReloadableModule> modules) {
    Collection<SModuleReference> refs = new LinkedHashSet<>();
    for (ReloadableModule module : modules) {
      refs.add(module.getModuleReference());
    }
    Collection<SModuleReference> referencedDeps = getDependencies(refs);
    Collection<ReloadableModule> resolvedDeps = resolveRefs(referencedDeps);
    assert (resolvedDeps.size() == referencedDeps.size());
    return resolvedDeps;
  }

  private Collection<ReloadableModule> resolveRefs(final Iterable<? extends SModuleReference> refs) {
    final Collection<ReloadableModule> modules = new LinkedHashSet<>();
    for (SModuleReference mRef : refs) {
      ReloadableModule module = resolveRef(mRef);
      if (module != null)  modules.add(module);
    }
    return modules;
  }

  Set<SModuleReference> getModuleRefs(Iterable<? extends ReloadableModule> modules) {
    Set<SModuleReference> result = new LinkedHashSet<>();
    for (ReloadableModule module : modules) {
      result.add(module.getModuleReference());
    }
    return result;
  }

  /**
   * @return all back dependencies of this module (closed set under back-dependency-relation)
   */
  public Collection<SModuleReference> getBackDependencies(Iterable<? extends SModuleReference> mRefs) {
    return myModuleUpdater.getBackDeps(mRefs);
  }

  public Collection<? extends ReloadableModule> getResolvedBackDependencies(Iterable<? extends ReloadableModule> modules) {
    Collection<SModuleReference> refs = new LinkedHashSet<>();
    for (ReloadableModule module : modules) refs.add(module.getModuleReference());
    return resolveRefs(getBackDependencies(refs));
  }

  boolean isModuleWatched(ReloadableModule module) {
    if (isChanged()) {
      LOG.warn("The class loading status info might be outdated");
    }
    return getAllModules().contains(module.getModuleReference());
  }

  private boolean isChanged() {
    return myModuleUpdater.isDirty();
  }

  enum DefaultStatuses implements ClassLoadingStatus {
    /**
     * tmp invalid status.
     * the module might be disposed itself or depend on some disposed module ref
     */
    @ToRemove(version = 0)
    SIMPLY_INVALID,
    /**
     * not tracked by ModulesWatcher
     */
    INVALID_NOT_LOADABLE,

    /**
     * no record in the map (kind of strange case)
     */
    INVALID_NO_RECORD,

    /**
     * module is loadable and has all its loadable deps are in the repository too
     */
    VALID;

    @Override
    public boolean isValid() {
      return this == VALID;
    }
  }

  static final class DepedencyIsDisposedStatus implements ClassLoadingStatus {
    private List<SModuleReference> myDirectProblemDeps;
    private List<SModuleReference> myDisposedRoots;

    DepedencyIsDisposedStatus(@NotNull List<SModuleReference> directProblemDeps, @NotNull List<SModuleReference> disposedRoots) {
      myDirectProblemDeps = directProblemDeps;
      myDisposedRoots = disposedRoots;
    }

    @Override
    public boolean isValid() {
      return false;
    }

    @NotNull
    public List<SModuleReference> getDisposedDependencyRoots() {
      return myDisposedRoots;
    }

    public List<SModuleReference> getProblematicDirectDependencies() {
      return myDirectProblemDeps;
    }
  }

  public interface ClassLoadingStatus {
    boolean isValid();
  }
}
