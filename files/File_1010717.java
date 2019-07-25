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
package jetbrains.mps.lang.editor.menus.transformation;

import jetbrains.mps.openapi.editor.descriptor.TransformationMenu;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuLookup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class TransformationMenuPointer implements TransformationMenuLookup {
  @NotNull
  private final TransformationMenu myTransformationMenu;

  public TransformationMenuPointer(@NotNull TransformationMenu transformationMenu) {
    myTransformationMenu = transformationMenu;
  }

  @NotNull
  @Override
  public Collection<TransformationMenu> lookup(@NotNull Collection<SLanguage> usedLanguages, @NotNull String menuLocation) {
    return Collections.singletonList(myTransformationMenu);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransformationMenuPointer that = (TransformationMenuPointer) o;
    return Objects.equals(myTransformationMenu, that.myTransformationMenu);
  }

  @Override
  public int hashCode() {

    return Objects.hash(myTransformationMenu);
  }
}
