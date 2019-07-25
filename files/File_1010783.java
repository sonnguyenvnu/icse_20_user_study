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
package jetbrains.mps.nodeEditor.menus.transformation;

import jetbrains.mps.lang.editor.menus.transformation.ImplicitTransformationMenu;
import jetbrains.mps.openapi.editor.descriptor.TransformationMenu;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuLookup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SLanguage;

import java.util.Collection;
import java.util.Collections;

class ImplicitMenuLookup implements TransformationMenuLookup {
  @NotNull
  private final SAbstractConcept myConcept;

  ImplicitMenuLookup(@NotNull SAbstractConcept concept) {
    myConcept = concept;
  }

  @NotNull
  @Override
  public Collection<TransformationMenu> lookup(@NotNull Collection<SLanguage> usedLanguages, @NotNull String menuLocation) {
    return Collections.singleton(new ImplicitTransformationMenu(myConcept));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ImplicitMenuLookup that = (ImplicitMenuLookup) o;

    return myConcept.equals(that.myConcept);

  }

  @Override
  public int hashCode() {
    return myConcept.hashCode();
  }

  @Override
  public String toString() {
    return "implicit menu for " + myConcept;
  }
}
