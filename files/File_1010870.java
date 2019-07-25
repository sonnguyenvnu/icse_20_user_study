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
package jetbrains.mps.idea.core.refactoring;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidatorEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.rename.RenameHandler;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.MPSBundle;
import jetbrains.mps.idea.core.MPSDataKeys;
import jetbrains.mps.idea.core.psi.impl.MPSPsiProvider;
import jetbrains.mps.model.ModelDeleteHelper;
import jetbrains.mps.refactoring.Renamer;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.ModuleRepositoryFacade;
import jetbrains.mps.smodel.SModelFileTracker;
import jetbrains.mps.vfs.IFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.EditableSModel;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelName;
import org.jetbrains.mps.openapi.module.ModelAccess;
import org.jetbrains.mps.openapi.module.SRepository;

import javax.lang.model.SourceVersion;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class ModelRenameHandler implements RenameHandler {
  private static final Logger LOG = Logger.getInstance(ModelRenameHandler.class);

  @Override
  public boolean isAvailableOnDataContext(DataContext dataContext) {
    IFile modelFile = getModelFile(dataContext);
    PsiElement psiElement = PlatformDataKeys.PSI_ELEMENT.getData(dataContext);
    if (modelFile == null || modelFile.isDirectory() || psiElement == null) return false;
    SModel model = SModelFileTracker.getInstance(ProjectHelper.getProjectRepository(psiElement.getProject())).findModel(modelFile);
    return model instanceof EditableSModel;
  }

  @Override
  public boolean isRenaming(DataContext dataContext) {
    return isAvailableOnDataContext(dataContext);
  }

  @Override
  public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile, DataContext dataContext) {
    LOG.assertTrue(false);
  }

  @Override
  public void invoke(@NotNull final Project project, @NotNull PsiElement[] psiElements, DataContext dataContext) {
    IFile modelFile = getModelFile(dataContext);
    if (modelFile == null) return;

    SRepository repository = ProjectHelper.getProjectRepository(project);
    SModel sModel = SModelFileTracker.getInstance(repository).findModel(modelFile);
    if (!(sModel instanceof EditableSModel)) return;

    final EditableSModel editableSModel = (EditableSModel) sModel;
    final AtomicReference<String> targetFqName = new AtomicReference<>(null);

    Pair<String, Boolean> result = Messages.showInputDialogWithCheckBox(
      MPSBundle.message("rename.model.to", editableSModel.getName().getLongName()),
      MPSBundle.message("rename.model"),
      MPSBundle.message("update.all.references"), true, true, null, editableSModel.getName().getLongName(),
      new MyInputValidator(ProjectHelper.getModelAccess(project)) {
        @Override
        protected void doRename(String fqName) {
          targetFqName.set(fqName);
        }

        @Override
        protected SRepository getRepository() {
          return repository;
        }
      });

    if (targetFqName.get() != null) {
      ApplicationManager.getApplication().runWriteAction(() -> deleteGeneratedFiles(editableSModel));
      final ModelRenamer renamer = new ModelRenamer(editableSModel, targetFqName.get(), !(result.getSecond()));
      ProjectHelper.getModelAccess(project).executeCommand(renamer::rename);
      ProgressManager.getInstance().run(new Task.Modal(project, MPSBundle.message("model.rename.handler.init.progress"), false) {
        @Override
        public void run(@NotNull ProgressIndicator indicator) {
          indicator.pushState();
          indicator.setIndeterminate(true);
          try {
            ProjectHelper.getModelAccess(project).runWriteAction(() -> renamer.updateReferencesIfNeeded(project));
          } finally {
            indicator.popState();
          }
        }
      });


      //Get MPSPsiModel source file to select in project view.
      final AtomicReference<VirtualFile> psiModelSourceFile = new AtomicReference<>();
      ProjectHelper.getModelAccess(project).runReadAction(
        () -> psiModelSourceFile.set(MPSPsiProvider.getInstance(project).getPsi(editableSModel.getReference()).getSourceVirtualFile()));

      //Navigate using SourceFile (pointing to real file)
      ProjectView.getInstance(project).select(editableSModel, psiModelSourceFile.get(), true);
    }
  }

  private IFile getModelFile(DataContext dataContext) {
    IFile modelFile = null;
    Set<IFile> modelFiles = MPSDataKeys.MODEL_FILES.getData(dataContext);
    if (modelFiles != null && modelFiles.size() == 1) {
      modelFile = modelFiles.iterator().next();
    }
    return modelFile;
  }

  private void deleteGeneratedFiles(SModel sModel) {
    // TODO: find a way to safely delete generated files. Until then, let's not make a mess
    if (true) {
      return;
    }

    // TODO for 3.5: check rewritten code and remove if(true)
    new ModelDeleteHelper(sModel).removeGeneratedArtifacts();
  }

  private static abstract class MyInputValidator implements InputValidatorEx {
    private final ModelAccess myModelAccess;

    private MyInputValidator(ModelAccess modelAccess) {
      myModelAccess = modelAccess;
    }

    @Override
    public boolean checkInput(String text) {
      return text != null && isModelNameValid(text.trim());
    }

    @Override
    public boolean canClose(String text) {
      if (text == null) return false;
      String targetName = text.trim();
      if (!isModelNameValid(targetName)) return false;
      doRename(targetName);
      return true;
    }

    @Override
    public String getErrorText(String text) {
      if (text != null) {
        String[] errorText = new String[1];
        if (!isModelNameValid(text.trim(), errorText)) {
          return errorText[0];
        }
      }
      return null;
    }

    protected abstract void doRename(String fqName);

    protected abstract SRepository getRepository();

    private boolean isModelNameValid(String modelFqName) {
      return isModelNameValid(modelFqName, new String[1]);
    }

    private boolean isModelNameValid(String modelName, String[] errorText) {
      if (modelName.length() == 0) {
        errorText[0] = MPSBundle.message("create.new.model.dialog.error.empty.name");
        return false;
      }


      if (modelName.lastIndexOf(".") == modelName.length()) {
        errorText[0] = MPSBundle.message("create.new.model.dialog.error.empty.short.name");
        return false;
      }

      if (!(SourceVersion.isName(modelName))) {
        errorText[0] = MPSBundle.message("create.new.model.dialog.error.invalid.java", modelName);
        return false;
      }
      try {
        SModelName mn = new SModelName(modelName);
        if (new ModelAccessHelper(myModelAccess).runReadAction(() -> !new ModuleRepositoryFacade(getRepository()).getModelsByName(mn).isEmpty())) {
          errorText[0] = MPSBundle.message("create.new.model.dialog.error.model.exists", modelName);
          return false;
        }
      } catch (IllegalArgumentException ex) {
        errorText[0] = ex.getMessage();
        return false;
      }
      return true;
    }
  }

  private static class ModelRenamer {
    private final EditableSModel myModelDescriptor;
    private final String myNewName;
    private boolean myLazy;

    public ModelRenamer(EditableSModel modelDescriptor, String fqName, boolean lazy) {
      myModelDescriptor = modelDescriptor;
      myNewName = fqName;
      myLazy = lazy;
    }

    public void rename() {
      myModelDescriptor.rename(myNewName, true);
    }

    public void updateReferencesIfNeeded(Project project) {
      if (!myLazy) {
        Renamer.updateModelAndModuleReferences(ProjectHelper.fromIdeaProject(project).getRepository());
      }
    }
  }
}
