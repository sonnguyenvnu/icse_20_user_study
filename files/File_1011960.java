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
package jetbrains.mps.ide.navigation;

import jetbrains.mps.openapi.navigation.ProjectPaneNavigator;
import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;

/**
* evgeny, 11/6/11
*/
public class ModelNavigatable extends BaseNavigatable {
  private SModelReference myModelReference;

  public ModelNavigatable(@NotNull Project project, @NotNull SModelReference modelReference) {
    super(project);
    myModelReference = modelReference;
  }

  @Override
  public void navigate(boolean focus) {
    SModel descriptor = myModelReference.resolve(myProject.getRepository());
    if (descriptor == null) {
      return;
    }
    new ProjectPaneNavigator(myProject).shallFocus(focus).select(myModelReference);
  }
}
