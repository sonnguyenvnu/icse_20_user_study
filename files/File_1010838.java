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

package jetbrains.mps.idea.build;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import jetbrains.mps.fileTypes.FileIcons;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.project.module.ModuleMPSSupport;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.ModelAccessHelper;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SRepository;

/**
 * Created by danilla on 21/10/15.
 */
public class GenerateModuleInProcessAction extends AnAction {

  public GenerateModuleInProcessAction() {
    super("Generate module", null, FileIcons.MODEL_ICON);
  }

  @Override
  public void update(AnActionEvent e) {
    Module module = LangDataKeys.MODULE.getData(e.getDataContext());
    if (module == null) {
      e.getPresentation().setVisible(false);
      return;
    } else {
      ModuleMPSSupport mpsSupport = ModuleMPSSupport.getInstance();
      boolean thereAreModels = mpsSupport != null && mpsSupport.isMPSEnabled(module) && thereAreModels(mpsSupport, module);
      e.getPresentation().setEnabled(thereAreModels);
    }
  }

  private boolean thereAreModels(ModuleMPSSupport mpsSupport, Module module) {
    Project project = module.getProject();
    SRepository repository = ProjectHelper.getProjectRepository(project);
    if (repository == null) {
      return false;
    }
    return new ModelAccessHelper(repository).runReadAction(() -> mpsSupport.getSolution(module).getModels().iterator().hasNext());
  }

  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    Module module = LangDataKeys.MODULE.getData(anActionEvent.getDataContext());
    Solution solution = ModuleMPSSupport.getInstance().getSolution(module);
    new GenerateModelsInProcess(module.getProject(), solution.getModels()).generate(getMakeConfigurator());
  }

  @Nullable
  protected MPSMakeConfigurator getMakeConfigurator() {
    return null;
  }
}
