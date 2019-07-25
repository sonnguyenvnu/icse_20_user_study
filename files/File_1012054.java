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
import jetbrains.mps.ide.navigation.NavigatableFactory;
import jetbrains.mps.ide.navigation.NodeNavigatable;
import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNodeReference;

class NodePointerNavigationHandler implements NavigatableFactory {

  private final Project myProject;

  NodePointerNavigationHandler(@NotNull Project mpsProject) {
    myProject = mpsProject;
  }

  @Override
  public boolean canCreate(@NotNull Object o) {
    return o instanceof SNodeReference;
  }

  @NotNull
  @Override
  public Navigatable create(@NotNull Object o) {
    return new NodeNavigatable(myProject, (SNodeReference) o);
  }
}
