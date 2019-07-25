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

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.pom.NonNavigatable;
import jetbrains.mps.ide.messages.FileWithLogicalPosition;
import jetbrains.mps.ide.navigation.NavigatableFactory;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import org.jetbrains.annotations.NotNull;

class FileWithLogicalPositionNavigationHandler implements NavigatableFactory {
  private final Project myProject;

  FileWithLogicalPositionNavigationHandler(@NotNull Project ideaProject) {
    myProject = ideaProject;
  }

  @Override
  public boolean canCreate(@NotNull Object o) {
    return o instanceof FileWithLogicalPosition;
  }

  @NotNull
  @Override
  public Navigatable create(@NotNull Object o) {
    final FileWithLogicalPosition pos = (FileWithLogicalPosition) o;
    final VirtualFile vf = VirtualFileUtils.getProjectVirtualFile(pos.getFile());
    if (vf == null) {
      return NonNavigatable.INSTANCE;
    }
    return new VirtualFileNavigatable(myProject, vf).at(pos.getLine(), pos.getColumn());
  }
}
