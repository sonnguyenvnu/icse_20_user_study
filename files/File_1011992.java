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
import jetbrains.mps.ide.icons.IdeIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.function.BiConsumer;

/**
 * Knows how to represent models for {@link ChooseByNameData}
 * @author Artem Tikhomirov
 * @since 3.4
 */
public class ModelsPresentation implements ElementPresentation<SModelReference> {

  private final SRepository myRepo;

  /**
   * @param repo that is capable to resolve elements both from local and global scope of corresponding {@linkplain ChooseByNameData chooser model}
   */
  public ModelsPresentation(@NotNull SRepository repo) {
    myRepo = repo;
  }

  @Override
  public void names(@NotNull Iterable<SModelReference> elements, @NotNull BiConsumer<SModelReference, String> nameConsumer) {
    elements.forEach(mr -> nameConsumer.accept(mr, mr.getName().getValue()));
  }

  @Override
  public boolean canRender(@Nullable Object element) {
    return element instanceof SModelReference;
  }

  @Override
  public void render(@NotNull final SModelReference element, @NotNull final ElementDescriptor presentation) {
    myRepo.getModelAccess().runReadAction(() -> {
      SModel model = element.resolve(myRepo);
      if (model != null) {
        presentation.name = model.getName().getValue();
        presentation.location = model.getModule().getModuleName();
        presentation.icon = GlobalIconManager.getInstance().getIconFor(model);
      } else {
        presentation.name = element.getName().getValue();
        presentation.location = "unknown";
        presentation.icon = IdeIcons.UNKNOWN_ICON;
      }
    });
  }
}
