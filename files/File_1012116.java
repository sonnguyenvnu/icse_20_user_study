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
package jetbrains.mps.ide.findusages.caches;

import com.intellij.openapi.vfs.VirtualFile;
import jetbrains.mps.extapi.persistence.FileBasedModelRoot;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.project.Project;
import jetbrains.mps.util.annotation.Hack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryContentAdapter;
import org.jetbrains.mps.openapi.persistence.ModelRoot;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Finds indexable roots for a global repository (do not get misled by a project presence here --
 * we use project#getRepository which (as for 09.09.16) delegates to the global repo)
 *
 * Also caches them and clears them on every module change or module added/removed events. [premature optimization?]
 *
 * AP
 */
final class IndexableRootCalculator {
  private final Project myProject;

  private final SRepositoryContentAdapter myModuleChangesListener = new SModuleChangesListener();
  private final AtomicReference<Set<VirtualFile>> myRootsCache = new AtomicReference<>();


  public IndexableRootCalculator(@NotNull com.intellij.openapi.project.Project project) {
    myProject = ProjectHelper.fromIdeaProject(project);
  }

  public void register() {
    SRepository repository = myProject.getRepository();
    repository.getModelAccess().runReadAction(() -> repository.addRepositoryListener(myModuleChangesListener));
  }

  public void unregister() {
    SRepository repository = myProject.getRepository();
    repository.getModelAccess().runReadAction(() -> repository.removeRepositoryListener(myModuleChangesListener));
  }

  /**
   * We are iterating over all modules, visible inside this project including libraries & core modules.
   * Thus we provide indices for libs.
   * Must be gone when some kind of BootRepository is introduced
   *
   * Internal: Recalculate cached collection of IndexableRoots if there is any invalid file it
   * This allows to maintain contract of {@link com.intellij.util.indexing.IndexableSetContributor#getAdditionalProjectRootsToIndex(com.intellij.openapi.project.Project)}
   * in our implementation {@link MPSIndexableSetContributor#getAdditionalProjectRootsToIndex(com.intellij.openapi.project.Project)}
   *
   */
  @Hack
  @NotNull
  public Set<VirtualFile> getIndexableRoots() {
    Set<VirtualFile> indexableRoots = myRootsCache.get();
    if (indexableRoots == null || indexableRoots.stream().anyMatch(file -> !file.isValid())) {
      indexableRoots = calcRoots();
      myRootsCache.compareAndSet(null, indexableRoots);
    }
    return indexableRoots;
  }

  @NotNull
  private Set<VirtualFile> calcRoots() {
    final Set<VirtualFile> files = new HashSet<>();

    myProject.getModelAccess().runReadAction(() -> {
      for (final SModule m : myProject.getRepository().getModules()) {
        for (String path : getIndexablePaths(m)) {
          VirtualFile file = VirtualFileUtils.getVirtualFile(path);
          if (file != null) {
            files.add(file);
          }
        }
      }
    });
    return Collections.unmodifiableSet(files);
  }

  private static Collection<String> getIndexablePaths(@NotNull SModule module) {
    // todo: maybe move getIndexablePaths method to FileBasedModelRoot, or even in ModelRoot classes?
    Set<String> result = new HashSet<>();

    for (ModelRoot modelRoot : module.getModelRoots()) {
      if (modelRoot instanceof FileBasedModelRoot) {
        FileBasedModelRoot fileBasedModelRoot = (FileBasedModelRoot) modelRoot;
        String contentRoot = fileBasedModelRoot.getContentRoot();
        if (contentRoot != null) {
          result.add(exposePath(contentRoot));
        }
        // todo: use excluded & source folders like IDEA
      }
    }

    return result;
  }

  private static String exposePath(String path) {
    String suffix = path.endsWith("." + MPSExtentions.MPS_ARCH) ? "!/" : "";
    return path + suffix;
  }

  private class SModuleChangesListener extends SRepositoryContentAdapter {
    private void invalidateCache() {
      myRootsCache.set(null);
    }

    @Override
    public void moduleAdded(@NotNull SModule module) {
      invalidateCache();
    }

    @Override
    public void moduleRemoved(@NotNull SModuleReference moduleReference) {
      invalidateCache();
    }

    @Override
    public void moduleChanged(@NotNull SModule module) {
      invalidateCache();
    }
  }
}
