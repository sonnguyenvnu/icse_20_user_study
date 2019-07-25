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

package jetbrains.mps.idea.core.library;

import com.intellij.ide.util.ChooseElementsDialog;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileTypes.FileTypes;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectBundle;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.impl.libraries.LibraryEx;
import com.intellij.openapi.roots.libraries.DummyLibraryProperties;
import com.intellij.openapi.roots.libraries.Library;
import com.intellij.openapi.roots.libraries.LibraryType;
import com.intellij.openapi.roots.libraries.NewLibraryConfiguration;
import com.intellij.openapi.roots.libraries.PersistentLibraryKind;
import com.intellij.openapi.roots.libraries.ui.AttachRootButtonDescriptor;
import com.intellij.openapi.roots.libraries.ui.LibraryEditorComponent;
import com.intellij.openapi.roots.libraries.ui.LibraryPropertiesEditor;
import com.intellij.openapi.roots.libraries.ui.LibraryRootsComponentDescriptor;
import com.intellij.openapi.roots.libraries.ui.OrderRoot;
import com.intellij.openapi.roots.libraries.ui.OrderRootTypePresentation;
import com.intellij.openapi.roots.libraries.ui.RootDetector;
import com.intellij.openapi.roots.ui.configuration.FacetsProvider;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.DefaultLibraryRootsComponentDescriptor;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.LibraryEditor;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.JarFileSystem;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import jetbrains.mps.idea.core.MPSBundle;
import jetbrains.mps.idea.core.facet.MPSFacetType;
import jetbrains.mps.idea.core.icons.MPSIcons;
import jetbrains.mps.idea.core.project.SolutionIdea;
import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.project.SModuleOperations;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.ModuleRepositoryFacade;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import javax.swing.Icon;
import javax.swing.JComponent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ModuleLibraryType extends LibraryType<DummyLibraryProperties> {
  public ModuleLibraryType() {
    super(MpsModuleLibraryKindContainer.MPS_MODULE_LIBRARY_KIND);
  }

  @Nullable
  public static VirtualFile getJarFile(String path) {
    VirtualFile vFile = VirtualFileManager.getInstance().findFileByUrl(VirtualFileManager.constructUrl(LocalFileSystem.PROTOCOL, path));
    if (vFile == null || vFile.isDirectory() || vFile.getFileType() != FileTypes.ARCHIVE) {
      return null;
    }
    return JarFileSystem.getInstance().findFileByPath(vFile.getPath() + JarFileSystem.JAR_SEPARATOR);
  }

  public static Set<VirtualFile> getModuleJars(AbstractModule usedModule) {
    Set<VirtualFile> stubFiles = new HashSet<VirtualFile>();
    for (String stubPath : SModuleOperations.getJavaFacet(usedModule).getClassPath()) {
      VirtualFile jarFile = getJarFile(stubPath);
      if (jarFile != null) {
        stubFiles.add(jarFile);
      }
    }
    return stubFiles;
  }

  @Override
  public String getCreateActionName() {
    return MPSBundle.message("library.mps.solutions");
  }

  @Override
  public NewLibraryConfiguration createNewLibrary(@NotNull JComponent parentComponent, @Nullable VirtualFile contextDirectory, @NotNull final Project project) {
    SRepository projectRepository = ProjectHelper.getProjectRepository(project);
    List<SModuleReference> availableSolutions = calculateVisibleModules(projectRepository, Collections.<VirtualFile>emptySet());

    ChooseElementsDialog<SModuleReference> chooser = new SModuleReferenceChooserDialog(projectRepository, parentComponent, availableSolutions);
    chooser.show();
    List<SModuleReference> chosenElements = chooser.getChosenElements();
    if (chosenElements.isEmpty()) {
      return null;
    }

    String name = ModuleLibrariesUtil.LIBRARY_PREFIX + chosenElements.get(0).getModuleName();
    if (chosenElements.size() > 1) {
      name += "...";
    }

    final Set<OrderRoot> roots = createRootsFor(projectRepository, chosenElements);

    return new NewLibraryConfiguration(name, this, new DummyLibraryProperties()) {
      @Override
      public void addRoots(@NotNull LibraryEditor editor) {
        editor.addRoots(roots);
      }
    };
  }

  private Set<OrderRoot> createRootsFor(SRepository repository, List<SModuleReference> chosenElements) {
    final Set<OrderRoot> roots = new LinkedHashSet<OrderRoot>();
    for (SModuleReference moduleReference : chosenElements) {
      AbstractModule module = new ModelAccessHelper(repository).runReadAction(() -> (AbstractModule) moduleReference.resolve(repository));
      roots.add(new OrderRoot(VirtualFileUtils.getOrCreateVirtualFile(module.getDescriptorFile()), ModuleXmlRootDetector.MPS_MODULE_XML, false));
      for (VirtualFile virtualFile : getModuleJars(module)) {
        roots.add(new OrderRoot(virtualFile, OrderRootType.CLASSES, false));
      }
    }
    return roots;
  }

  @Override
  public LibraryPropertiesEditor createPropertiesEditor(@NotNull LibraryEditorComponent editorComponent) {
    return null;
  }

  @Override
  public Icon getIcon() {
    return MPSIcons.MPS_ICON;
  }

  @Override
  public DummyLibraryProperties detect(@NotNull List<VirtualFile> classesRoots) {
    return super.detect(classesRoots);
  }

  @Override
  public boolean isSuitableModule(@NotNull Module module, @NotNull FacetsProvider facetsProvider) {
    return !facetsProvider.getFacetsByType(module, MPSFacetType.ID).isEmpty();
  }

  @Override
  public LibraryRootsComponentDescriptor createLibraryRootsComponentDescriptor() {
    return new MyLibraryRootsComponentDescriptor();
  }

  public static boolean isModuleLibrary(Library l) {
    if (l instanceof LibraryEx) {
      PersistentLibraryKind<?> kind = ((LibraryEx) l).getKind();
      return kind != null && MpsModuleLibraryKindContainer.MPS_MODULE_LIBRARY_KIND.getKindId().equals(kind.getKindId());
    }
    return false;
  }

  public static ModuleLibraryType getInstance() {
    return LibraryType.EP_NAME.findExtension(ModuleLibraryType.class);
  }

  private static class SModuleReferenceChooserDialog extends ChooseElementsDialog<SModuleReference> {
    private final SRepository myRepo;

    public SModuleReferenceChooserDialog(SRepository repo, Component parent, List<SModuleReference> availableSolutions) {
      super(parent, availableSolutions, MPSBundle.message("used.modules.chooser.title"));
      myRepo = repo;
    }


    @Override
    protected String getItemText(SModuleReference item) {
      return item.getModuleName();
    }

    @Override
    protected Icon getItemIcon(final SModuleReference item) {
      return new ModelAccessHelper(myRepo).runReadAction(() -> {
        if (item.resolve(myRepo) instanceof Solution) {
          return MPSIcons.SOLUTION_ICON;
        } else {
          return MPSIcons.LANGUAGE_ICON;
        }
      });
    }
  }

  private static class MyLibraryRootsComponentDescriptor extends DefaultLibraryRootsComponentDescriptor {
    @Override
    public OrderRootTypePresentation getRootTypePresentation(@NotNull OrderRootType type) {
      if (type == ModuleXmlRootDetector.MPS_MODULE_XML) {
        return ModuleXmlRootDetector.getPresentation();
      }
      return null;
    }

    @Override
    public OrderRootType[] getRootTypes() {
      ArrayList<OrderRootType> types = new ArrayList<OrderRootType>();
      types.addAll(Arrays.asList(super.getRootTypes()));
      types.add(ModuleXmlRootDetector.MPS_MODULE_XML);
      return types.toArray(new OrderRootType[types.size()]);
    }

    @NotNull
    @Override
    public List<? extends RootDetector> getRootDetectors() {
      List<RootDetector> detectors = new ArrayList<RootDetector>();
      detectors.addAll(super.getRootDetectors());
      detectors.add(ModuleXmlRootDetector.getInstance());
      return detectors;
    }

    @NotNull
    @Override
    public FileChooserDescriptor createAttachFilesChooserDescriptor(@Nullable String libraryName) {
      // same as super apart from the constructor invocation parameters
      FileChooserDescriptor descriptor = new FileChooserDescriptor(false, false, true, false, true, true);
      descriptor.setTitle(StringUtil.isEmpty(libraryName) ? ProjectBundle.message("library.attach.files.action")
        : ProjectBundle.message("library.attach.files.to.library.action", libraryName));
      descriptor.setDescription(ProjectBundle.message("library.attach.files.description"));
      return descriptor;
    }

    @NotNull
    @Override
    public List<? extends AttachRootButtonDescriptor> createAttachButtons() {
      return Arrays.asList(new AttachRootButtonDescriptor(ModuleXmlRootDetector.MPS_MODULE_XML, MPSBundle.message("library.attach.mps.solution")) {
        @Override
        public VirtualFile[] selectFiles(@NotNull JComponent parent, @Nullable VirtualFile initialSelection, @Nullable final Module contextModule, @NotNull final LibraryEditor libraryEditor) {
          final SRepository repository = ProjectHelper.getProjectRepository(contextModule.getProject());
          List<SModuleReference> visibleModules = calculateVisibleModules(repository, new HashSet<VirtualFile>(Arrays.asList(libraryEditor.getFiles(ModuleXmlRootDetector.MPS_MODULE_XML))));

          ChooseElementsDialog<SModuleReference> chooser = new SModuleReferenceChooserDialog(repository, parent, visibleModules);
          chooser.show();
          final List<SModuleReference> chosenElements = chooser.getChosenElements();

          final Set<VirtualFile> addedDescriptors = new LinkedHashSet<VirtualFile>();
          final Set<VirtualFile> addedJars = new LinkedHashSet<VirtualFile>();
          repository.getModelAccess().runReadAction(new Runnable() {
            @Override
            public void run() {
              for (SModuleReference module : chosenElements) {
                AbstractModule chosenModule = (AbstractModule) module.resolve(repository);
                addedDescriptors.add(VirtualFileUtils.getOrCreateVirtualFile(chosenModule.getDescriptorFile()));
                for (VirtualFile virtualFile : getModuleJars(chosenModule)) {
                  addedJars.add(virtualFile);
                }
              }
            }
          });
          // that's a hack
          // I want to add 2 different root types here: classes and module xml-s
          for (VirtualFile classesJar : addedJars) {
            libraryEditor.addRoot(classesJar, OrderRootType.CLASSES);
          }
          return addedDescriptors.toArray(new VirtualFile[addedDescriptors.size()]);
        }
      });
    }
  }

  private static List<SModuleReference> calculateVisibleModules(SRepository repository, final Set<VirtualFile> excluded) {
    final List<SModuleReference> availableSolutions = new ArrayList<SModuleReference>();
    final List<SModuleReference> availableLanguages = new ArrayList<SModuleReference>();
    repository.getModelAccess().runReadAction(new Runnable() {
      @Override
      public void run() {
        for (SModule module : new ModuleRepositoryFacade(repository).getAllModules(SModule.class)) {
          if (module instanceof SolutionIdea || ((AbstractModule) module).getDescriptorFile() == null) {
            continue;
          }
          if (excluded.contains(VirtualFileUtils.getOrCreateVirtualFile(((AbstractModule) module).getDescriptorFile()))) {
            // skip solutions that are already in a lib
            continue;
          }
          if (module instanceof Solution) {
            availableSolutions.add(module.getModuleReference());
          } else {
            availableLanguages.add(module.getModuleReference());
          }
        }
      }
    });
    Comparator<SModuleReference> moduleComparator = new Comparator<SModuleReference>() {
      @Override
      public int compare(SModuleReference o1, SModuleReference o2) {
        return o1.getModuleName().compareTo(o2.getModuleName());
      }
    };
    Collections.sort(availableSolutions, moduleComparator);
    Collections.sort(availableLanguages, moduleComparator);
    List<SModuleReference> result = new ArrayList<SModuleReference>();
    result.addAll(availableSolutions);
    result.addAll(availableLanguages);
    return result;
  }
}
