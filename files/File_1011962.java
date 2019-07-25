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

import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.Objects;

/**
 * evgeny, 11/6/11
 */
public class NodeNavigatable extends BaseNavigatable {
  private final SNodeReference myNodePointer;

  public NodeNavigatable(@NotNull Project project, @NotNull SNodeReference nodePointer) {
    super(project);
    myNodePointer = nodePointer;
  }

  @Override
  public void navigate(boolean focus) {
    new EditorNavigator(myProject).shallFocus(focus).selectIfChild().open(myNodePointer);
  }

  @Override
  public int hashCode() {
    return myNodePointer.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof NodeNavigatable) {
      return Objects.equals(myNodePointer, ((NodeNavigatable) obj).myNodePointer);
    }
    return false;
  }

  @Override
  public String toString() {
    return "NodeNavigatable[" + myNodePointer + "]";
  }

  @NotNull
  public final SNodeReference getNodePointer() {
    return myNodePointer;
  }
}
