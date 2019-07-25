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
package jetbrains.mps.ide.messages.navigation;

import com.intellij.pom.Navigatable;
import jetbrains.mps.ide.navigation.ModelNavigatable;
import jetbrains.mps.ide.navigation.NavigatableFactory;
import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelReference;

/**
 * non-public unless necessary. XXX perhaps, shall move next to ModelNavigatable into jetbrains.mps.ide.navigation?
 * @author Artem Tikhomirov
 * @since 3.4
 */
class ModelNavigatableFactory implements NavigatableFactory {
  private final Project myProject;

  ModelNavigatableFactory(@NotNull Project mpsProject) {
    myProject = mpsProject;
  }

  @Override
  public boolean canCreate(@NotNull Object o) {
    return o instanceof SModelReference;
  }

  @NotNull
  @Override
  public Navigatable create(@NotNull Object o) {
    return new ModelNavigatable(myProject, (SModelReference) o);
  }
}
