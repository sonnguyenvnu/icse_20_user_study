/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.openapi.navigation;

import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

/**
 * Handy facility around {@link NavigationSupport} to navigate to ProjectPane, similar to {@link EditorNavigator}.
 * Hides pre-requisites of respective {@link NavigationSupport} methods (like model read + EDT), avoids code duplication (resolve, if != null)
 *
 * @author Artem Tikhomirov
 * @since 3.4
 */
public class ProjectPaneNavigator {
  private final Project myProject;
  private boolean myFocus;

  public ProjectPaneNavigator(@NotNull Project mpsProject) {
    myProject = mpsProject;
  }

  /**
   * Default: no focus, <code>false</code>
   */
  public ProjectPaneNavigator shallFocus(boolean shallFocus) {
    myFocus = shallFocus;
    return this;
  }

  public void select(@NotNull final SNodeReference node) {
    myProject.getModelAccess().runReadInEDT(() -> {
      SNode n = node.resolve(myProject.getRepository());
      if (n != null) {
        NavigationSupport.getInstance().selectInTree(myProject, n, myFocus);
      }
    });
  }

  public void select(@NotNull final SModelReference model) {
    myProject.getModelAccess().runReadInEDT(() -> {
      SModel m = model.resolve(myProject.getRepository());
      if (m != null) {
        NavigationSupport.getInstance().selectInTree(myProject, m, myFocus);
      }
    });
  }

  public void select(@NotNull final SModuleReference module) {
    myProject.getModelAccess().runReadInEDT(() -> {
      SModule m = module.resolve(myProject.getRepository());
      if (m != null) {
        NavigationSupport.getInstance().selectInTree(myProject, m, myFocus);
      }
    });
  }

  public void select(@NotNull SLanguage language) {
    SModuleReference smr = language.getSourceModuleReference();
    if (smr != null) {
      select(smr);
    }
  }
}
