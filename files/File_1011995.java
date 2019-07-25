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
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.function.BiConsumer;

/**
 * Knows how to represent modules for {@link ChooseByNameData}
 * @author Artem Tikhomirov
 * @since 3.4
 */
public class ModulesPresentation implements ElementPresentation<SModuleReference> {

  private final SRepository myRepo;

  /**
   * @param repo repository capable to resolve module references from both local and global scope of corresponding {@linkplain ChooseByNameData chooser model}
   */
  public ModulesPresentation(@NotNull SRepository repo) {
    myRepo = repo;
  }

  @Override
  public void names(@NotNull Iterable<SModuleReference> elements, @NotNull BiConsumer<SModuleReference, String> nameConsumer) {
    elements.forEach(mr -> nameConsumer.accept(mr, mr.getModuleName()));
  }

  @Override
  public boolean canRender(@Nullable Object element) {
    return element instanceof SModuleReference;
  }

  @Override
  public void render(@NotNull final SModuleReference element, @NotNull final ElementDescriptor presentation) {
    myRepo.getModelAccess().runReadAction(() -> {
      SModule module = element.resolve(myRepo);
      if (module != null) {
        presentation.name = module.getModuleName();
        presentation.icon = GlobalIconManager.getInstance().getIconFor(module);
      } else {
        presentation.name = element.getModuleName();
        presentation.icon = IdeIcons.UNKNOWN_ICON;
      }
    });
  }
}
