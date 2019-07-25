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
package jetbrains.mps.library;

import gnu.trove.THashMap;
import gnu.trove.THashSet;
import jetbrains.mps.extapi.module.SRepositoryExt;
import jetbrains.mps.library.ModulesMiner.ModuleHandle;
import jetbrains.mps.library.contributor.LibDescriptor;
import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.project.structure.modules.LanguageDescriptor;
import jetbrains.mps.smodel.MPSModuleOwner;
import jetbrains.mps.smodel.ModuleRepositoryFacade;
import jetbrains.mps.util.CollectionUtil;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.vfs.IFile;
import jetbrains.mps.vfs.RedispatchListener;
import jetbrains.mps.vfs.refresh.FileListener;
import jetbrains.mps.vfs.refresh.FileListeningPreferences;
import jetbrains.mps.vfs.refresh.FileSystemEvent;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.util.ProgressMonitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * SLibrary tracks a path {@link #myFile} with modules inside.
 * It listens to file system events and reloads modules from disk if necessary.
 * It is actually the layer between SRepository and SModule in the repository hierarchy (as well as the Project).
 * [The repository consists from library modules, project modules and several special modules (there are few of them)]
 *
 * evgeny, 11/3/12
 */
public class SLibrary implements MPSModuleOwner, Comparable<SLibrary> {
  private static final Logger LOG = Logger.getLogger(SLibrary.class);
  private static final FileListeningPreferences LISTENING_PREFS =
      FileListeningPreferences.construct()
                              .notifyOnAncestorCreation() // remove dir {lib_dir}/.. and then `git reset --hard` yields one fs creation event for the directory {lib_dir}/..
                              .notifyOnDescendantCreation()
                              .notifyOnParentRemoval()
                              .build();

  @NotNull private final IFile myFile;
  private final SRepositoryExt myRepository;
  private final ClassLoader myPluginClassLoader;
  private final boolean myHidden;
  private final AtomicReference<List<ModuleHandle>> myHandles = new AtomicReference<>();
  private final ModuleFileTracker myFileTracker;
  private final FileListener myPostNotifyDispatch;

  public SLibrary(@NotNull SRepositoryExt repoToUpdate, LibDescriptor pathDescriptor, boolean hidden) {
    myRepository = repoToUpdate;
    myPluginClassLoader = pathDescriptor.getPluginClassLoader();
    myFile = pathDescriptor.getPath();
    myHidden = hidden;
    // SLibrary listens to all file changes as it needs to react to create events as well as change/delete for existing modules.
    // ModuleFileTracker helps to keep record which module originates from what file
    myFileTracker = new ModuleFileTracker();
    // XXX provisional, copied from ModuleFileTracker, have to review
    myPostNotifyDispatch = new RedispatchListener(SLibrary.this::update, LISTENING_PREFS);
  }

  @NotNull
  public IFile getFile() {
    return myFile;
  }

  /**
   * @return a classloader which will be the parent for all ModuleClassLoaders created for the modules in this SLibrary
   */
  @Nullable
  public ClassLoader getPluginClassLoader() {
    return myPluginClassLoader;
  }

  List<ModuleHandle> getHandles() {
    List<ModuleHandle> moduleHandles = myHandles.get();
    if (moduleHandles == null) {
      return Collections.emptyList();
    }
    return moduleHandles;
  }

  void attach() {
    LOG.debug("Attaching " + this);
    // without this the performance drops because of the heavy idea local filesystem listening mechanism
    myFile.addListener(myPostNotifyDispatch);
    collectAndRegisterModules();
  }

  void dispose() {
    LOG.debug("Disposing " + this);
    new ModuleRepositoryFacade(myRepository).unregisterModules(this);
    myFile.removeListener(myPostNotifyDispatch);
  }

  /*package*/ final void update(ProgressMonitor monitor, @NotNull FileSystemEvent event) {
    // update() used to come with global model write lock. This code has better idea about what to lock.
    // Write per jar file is not the best alternative, SLibrary not always a directory, it's a single jar e.g. in Ant::generate.
    // Therefore, we use RedispatchListener and the fact FileProcessor adds up events per FSListener instance that comes through FSE.notify()
    // and expect this method to be invoked only once even if there are a lot of files of this SLibrary have been affected (added/removed/changed)
    myRepository.getModelAccess().runWriteAction(() -> {
      // for removed files, take modules associated with these files
      // Note, there could be a new file among added with a MD for existing toBeRemoved module (e.g. file rename/move).
      final Map<SModuleReference, IFile> toRemoveCandidates = myFileTracker.getAffectedBy(event.getRemoved());
      // We shall remove our listener for files we no longer care about. XXX what of myFile is among removed, is it fine to remove the listener?
      toRemoveCandidates.values().forEach(f -> {
        if (!myFile.equals(f)) {
          f.removeListener(myPostNotifyDispatch);
        }
      });

      // for changed files, take associated modules, these are going to be either changed or removed (in case respective
      // module file no longer describes the same module
      final Map<SModuleReference, IFile> trackedToChangeCandidates = myFileTracker.getTrackedFor(event.getChanged());
      // for changed and added, collect module descriptors using MM
      final ModulesMiner modulesMiner = collectFromCreatedAndChanged(event);
      THashMap<SModuleReference, ModuleHandle> newlyDiscovered = new THashMap<>();
      for (ModuleHandle mh : modulesMiner.getCollectedModules()) {
        // XXX though it's not nice to assume no identical module id in different files
        //     just too much work to handle this case right now
        newlyDiscovered.put(mh.getDescriptor().getModuleReference(), mh);
      }
      // intersect trackedToChangeCandidates and newly collected. These are 'changed' modules (XXX though what if its IFile aka AM.getDescriptorFile was changed?)
      final List<SModuleReference> changedModules = CollectionUtil.intersect(trackedToChangeCandidates.keySet(), newlyDiscovered.keySet());
      // newlyCollected subtract trackedToChangeCandidates are to be reported as 'New'
      final List<SModuleReference> brandNew = CollectionUtil.subtract(newlyDiscovered.keySet(), trackedToChangeCandidates.keySet());
      // toBeChanged subtract newlyCollected - those to be 'removed'. Here, it means file persists but module is gone
      final List<SModuleReference> stale = CollectionUtil.subtract(trackedToChangeCandidates.keySet(), newlyDiscovered.keySet());
      // newlyCollected intersect with toBeRemoved - as 'changed'?
      final List<SModuleReference> movedModules = CollectionUtil.intersect(newlyDiscovered.keySet(), toRemoveCandidates.keySet());
      // XXX when do I unregister and register again my FS listener?

      // movedModules got representation in newlyDiscovered, no need to tell them removed
      toRemoveCandidates.keySet().removeAll(movedModules);  // aka     movedModules.forEach(toRemoveCandidates::remove);
      // modules from changed files that we not re-discovered are better to get abandoned.
      // note, we don't remove FS listener for these files, due to
      //   (a) there could be still other modules in the same file
      //   (b) it's tricky to find out whether we no longer need to listen to its changes.
      stale.forEach(mr -> toRemoveCandidates.put(mr, trackedToChangeCandidates.get(mr)));
      // next code (with newHandles) is just for the sake of myHandles update, which is in use in a single place, in MPSIDEA Plugin, to populate
      // cached repo. FIXME please refactor and get rid of this
      ArrayList<ModuleHandle> newHandles = new ArrayList<>(myHandles.get().size());
      if (toRemoveCandidates.isEmpty()) {
        newHandles.addAll(myHandles.get());
      } else {
        final Set<SModuleReference> toRemoveRefs = toRemoveCandidates.keySet();
        for (ModuleHandle existingMH : myHandles.get()) {
          if (!toRemoveRefs.contains(existingMH.getDescriptor().getModuleReference())) {
            newHandles.add(existingMH);
          }
        }
      }
      for (Entry<SModuleReference, IFile> entry : toRemoveCandidates.entrySet()) {
        SModuleReference mRef = entry.getKey();
        final SModule module = mRef.resolve(myRepository);
        if (module == null) {
          LOG.warn("The module " + mRef + " is not found in the repo");
          // FIXME it's odd there's no module,
          // though we are just about to remove it anyway, ignore
        } else {
          myRepository.unregisterModule(module, this);
        }
        myFileTracker.forget(entry.getValue(), mRef);
        // fs listener for deleted files has been already removed, above. for 'stale' modules, listener on their files is kept.
        // myHandles are updated above
      }
      // first, install new modules, just in case any of the changes needs one
      ArrayList<ModuleHandle> toLoad = new ArrayList<>(brandNew.size());
      for (SModuleReference mr : brandNew) {
        ModuleHandle moduleHandle = newlyDiscovered.get(mr);
        assert moduleHandle != null; // brandNew is based on newlyDiscovered, can't be null
        toLoad.add(moduleHandle);
      }
      // XXX I don't like the fact we remove myPostNotifyDispatch in this method, but add it in another, perhaps, shall add listener here?
      // shall sort toLoad so that Languages come first, use approach of ModulesMiner.getCollectedModules(). Alternatively, could keep brandNew
      // and newlyDiscovered with the order from MM (use some ordered Map implementation)
      // FIXME need a better mechanism to find out MD kind than instanceof. E.g. DeployedDescriptor is the same for any module, once we
      //       drop source modules, we are likely to fail here
      toLoad.sort(Comparator.comparingInt(v -> v.getDescriptor() instanceof LanguageDescriptor ? 0 : 1));
      instantiateAndActivateModules(toLoad);
      newHandles.addAll(toLoad);
      myHandles.set(newHandles);
      //
      for (SModuleReference mr : changedModules) {
        final SModule module = mr.resolve(myRepository);
        if (module == null) {
          continue;
        }
        if (false == module instanceof AbstractModule) {
          continue;
        }
        ((AbstractModule) module).setModuleDescriptor(newlyDiscovered.get(mr).getDescriptor(), false);
      }
    });
  }

  @NotNull
  private ModulesMiner collectFromCreatedAndChanged(@NotNull FileSystemEvent event) {
    final ModulesMiner modulesMiner = createModuleMiner();
    Set<IFile> toCollectModulesFrom = new HashSet<>(event.getCreated());
    toCollectModulesFrom.addAll(event.getChanged());
    if (toCollectModulesFrom.stream().anyMatch(myFile::isDescendant)) {
      modulesMiner.collectModules(myFile);
    } else {
      toCollectModulesFrom.forEach(modulesMiner::collectModules);
    }
    return modulesMiner;
  }

  private ModulesMiner createModuleMiner() {
    return new ModulesMiner();
  }

  private void collectAndRegisterModules() {
    final ModulesMiner modulesMiner = createModuleMiner().collectModules(myFile);
    List<ModuleHandle> moduleHandles = new ArrayList<>(modulesMiner.getCollectedModules());
    // MM gives all modules, including those sharing descriptor file with their source language
    // we expect this sequence sorted, with LanguageDescriptors first, as MRF doesn't tolerate generators coming in front of their languages yet.
    instantiateAndActivateModules(moduleHandles);
    myHandles.set(moduleHandles);
  }

  private void instantiateAndActivateModules(List<ModuleHandle> moduleHandles) {
    ModuleRepositoryFacade mrf = new ModuleRepositoryFacade(myRepository);
    List<SModule> loaded = new ArrayList<>(moduleHandles.size());
    Set<IFile> uniqueFiles = new THashSet<>();
    for (ModuleHandle moduleHandle : moduleHandles) {
      SModule module = mrf.instantiateModule(moduleHandle, this);
      loaded.add(module);
      myFileTracker.track(moduleHandle.getFile(), module);
      uniqueFiles.add(moduleHandle.getFile());
    }
    for (SModule module : loaded) {
      if (module instanceof AbstractModule) {
        ((AbstractModule) module).onModuleLoad();
      }
    }
    // FIXME it shall be myFileTracker that is responsible for listener attach/detach on track/forget, though have to figure out how
    //       to exclude myFile then.
    //       Besides, would be great not to bother attaching listeners to files residing inside a jar (e.g. if myFile is a jar).
    uniqueFiles.remove(myFile);
    uniqueFiles.forEach(f -> f.addListener(myPostNotifyDispatch));
  }

  @Override
  public boolean isHidden() {
    return myHidden;
  }

  @Override
  public String toString() {
    return "SLibrary [path " + myFile + "; plugin " + myPluginClassLoader + "]";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SLibrary library = (SLibrary) o;

    if (myHidden != library.myHidden) {
      return false;
    }

    return Objects.equals(myPluginClassLoader, library.myPluginClassLoader) && myFile.equals(library.myFile);
  }

  @Override
  public int hashCode() {
    int result = myFile.hashCode();
    result = 31 * result + (myPluginClassLoader != null ? myPluginClassLoader.hashCode() : 0);
    result = 31 * result + (myHidden ? 1 : 0);
    return result;
  }

  @Override
  public int compareTo(@NotNull SLibrary another) {
    String n1 = getFile().getName();
    String n2 = another.getFile().getName();
    // when comparing generator module, ensure it goes after any other non-generator module, to ensure languages come first.
    // At the moment, ModuleRepositoryFacade is not capable to register generator for a language not yet known to the repo.
    // Also see ModulesMiner.getCollectedModules()
    if (n1.endsWith("-generator.jar") ^ n2.endsWith("-generator.jar")) {
      return n1.endsWith("-generator.jar") ? 1 : -1;
    }
    return n1.compareTo(n2);
  }
}
