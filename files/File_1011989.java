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
package jetbrains.mps.workbench.choose;

import jetbrains.mps.icons.MPSIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SLanguage;

import java.util.function.BiConsumer;

/**
 * Knows how to represent deployed languages for {@link ChooseByNameData}
 * @author Artem Tikhomirov
 * @since 3.4
 */
public class LanguagesPresentation implements ElementPresentation<SLanguage> {

  public LanguagesPresentation() {
  }

  @Override
  public void names(@NotNull Iterable<SLanguage> elements, @NotNull BiConsumer<SLanguage, String> nameConsumer) {
    elements.forEach(lang -> nameConsumer.accept(lang, lang.getQualifiedName()));
  }

  @Override
  public boolean canRender(@Nullable Object element) {
    return element instanceof SLanguage;
  }

  @Override
  public void render(@NotNull SLanguage element, @NotNull ElementDescriptor presentation) {
    presentation.name = element.getQualifiedName();
    presentation.icon = MPSIcons.LanguageRuntime;
  }
}
