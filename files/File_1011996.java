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

import jetbrains.mps.ide.icons.GlobalIconManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.NavigationParticipant.NavigationTarget;

import java.util.function.BiConsumer;

/**
 * Tells {@link ChooseByNameData} how to render {@link NavigationTarget}
 * @author Artem Tikhomirov
 * @since 3.5
 */
public class NavigationTargetPresentation implements ElementPresentation<NavigationTarget> {
  @Override
  public void names(@NotNull Iterable<NavigationTarget> elements, @NotNull BiConsumer<NavigationTarget, String> nameConsumer) {
    elements.forEach(nt -> nameConsumer.accept(nt, nt.getPresentation()));
  }

  @Override
  public boolean canRender(@Nullable Object element) {
    return element instanceof NavigationTarget;
  }

  @Override
  public void render(@NotNull NavigationTarget element, @NotNull ElementDescriptor presentation) {
    presentation.name = element.getPresentation();
    //we don't use alternative icon here since it's very expensive and slows down Ctrl+N popup considerably
    presentation.icon = GlobalIconManager.getInstance().getIconFor(element.getConcept());
    SModelReference modelRef = element.getNodeReference().getModelReference();
    SModuleReference moduleRef = modelRef.getModuleReference();
    presentation.location = moduleRef == null ? String.format("(%s)", modelRef.getModelName()) : String.format("(%s/%s)", moduleRef.getModuleName(), modelRef.getModelName());
  }
}
