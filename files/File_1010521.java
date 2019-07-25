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
package jetbrains.mps.project;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.smodel.ModelImports;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class ModelsAutoImportsManager implements CoreComponent {
  // todo: should be application component ?
  // todo: is auto imports workbench functionality?
  private Set<AutoImportsContributor> contributors = new HashSet<>();

  @ToRemove(version = 2018.3)
  private static ModelsAutoImportsManager ourInstance;

  @Override
  public void init() {
    ourInstance = this;
  }

  @Override
  public void dispose() {
    ourInstance = null;
  }

  /**
   * @deprecated use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} and instance method {@link #register(AutoImportsContributor)} instead
   */
  @Deprecated
  @ToRemove(version = 2018.3)
  public static void registerContributor(AutoImportsContributor contributor) {
    ourInstance.register(contributor);
  }

  /**
   * @deprecated use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} and instance method {@link #unregister(AutoImportsContributor)} instead
   */
  @Deprecated
  @ToRemove(version = 2018.3)
  public static void unregisterContributor(AutoImportsContributor contributor) {
    ourInstance.unregister(contributor);
  }

  public void register(AutoImportsContributor contributor) {
    contributors.add(contributor);
  }

  public void unregister(AutoImportsContributor contributor) {
    contributors.remove(contributor);
  }

  /**
   * @deprecated use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} and instance method {@link #getModelsToImport(SModule, SModel)} instead
   */
  @Deprecated
  @ToRemove(version = 2018.3)
  public static Set<SModel> getAutoImportedModels(SModule contextModule, SModel model) {
    return ourInstance.getModelsToImport(contextModule, model);
  }

  public Set<SModel> getModelsToImport(SModule contextModule, SModel model) {
    Set<SModel> result = new HashSet<>();
    for (AutoImportsContributor contributor : contributors) {
      if (contributor.isApplicable(contextModule)) {
        result.addAll(contributor.getAutoImportedModels(contextModule, model));
      }
    }
    return result;
  }

  /**
   * @deprecated use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} and instance method {@link #getLanguagesToImport(SModule, SModel)} instead
   */
  @Deprecated
  @ToRemove(version = 2018.3)
  public static Set<SLanguage> getLanguages(SModule contextModule, SModel model) {
    return ourInstance.getLanguagesToImport(contextModule, model);
  }

  public Set<SLanguage> getLanguagesToImport(SModule contextModule, SModel model) {
    Set<SLanguage> result = new HashSet<>();
    for (AutoImportsContributor contributor : contributors) {
      if (contributor.isApplicable(contextModule)) {
        result.addAll(contributor.getLanguages(contextModule, model));
      }
    }
    return result;
  }

  /**
   * @deprecated use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} and instance method {@link #getDevkitsToImport(SModule, SModel)} instead
   */
  @Deprecated
  @ToRemove(version = 2018.3)
  public static Set<SModuleReference> getDevKits(SModule contextModule, SModel forModel) {
    return ourInstance.getDevkitsToImport(contextModule, forModel);
  }

  public Set<SModuleReference> getDevkitsToImport(SModule contextModule, SModel forModel) {
    Set<SModuleReference> result = new HashSet<>();
    for (AutoImportsContributor contributor : contributors) {
      if (contributor.isApplicable(contextModule)) {
        result.addAll(contributor.getDevKits(contextModule, forModel));
      }
    }
    return result;
  }

  /**
   * @deprecated use {@link jetbrains.mps.components.ComponentHost#findComponent(Class)} and instance method {@link #performImports(SModule, SModel)} instead
   */
  @Deprecated
  @ToRemove(version = 2018.3)
  public static void doAutoImport(SModule module, SModel model) {
    ourInstance.performImports(module, model);
  }

  public void performImports(SModule module, SModel model) {
    ModelImports modelImports = new ModelImports(model);
    for (SModel modelToImport : getModelsToImport(module, model)) {
      modelImports.addModelImport(modelToImport.getReference());
    }
    for (SLanguage language : getLanguagesToImport(module, model)) {
      modelImports.addUsedLanguage(language);
    }
    for (SModuleReference devKit : getDevkitsToImport(module, model)) {
      modelImports.addUsedDevKit(devKit);
    }
  }

  public static abstract class AutoImportsContributor<ModuleType extends SModule> {

    /**
     * @deprecated Use generic {@link #isApplicable(SModule)} instead, and stop parameterising
     *             the class with ModuleType, it's to be removed after 2018.3 (signature of
     *             all the methods of this class will use SModule then). It's not possible
     *             to change {@code ModuleType contextModule} to {@code SModule contextModule}
     *             right away as it breaks compile-time code compatibility.
     */
    @Deprecated
    @ToRemove(version = 2018.3)
    public Class<ModuleType> getApplicableSModuleClass() {
      return null;
    }

    /**
     * IMPORTANT! THIS METHOD HAS DEFAULT IMPLEMENTATION FOR TRANSITION PERIOD
     *            AND TO BECOME ABSTRACT IN NEXT RELEASE. PLEASE OVERRIDE!
     */
    public boolean isApplicable(SModule module) {
      Class<ModuleType> applicableSModuleClass = getApplicableSModuleClass();
      return applicableSModuleClass != null && applicableSModuleClass.isInstance(module);
    }

    public Set<SModel> getAutoImportedModels(ModuleType contextModule, SModel model) {
      // XXX SModel return value implies we resolve models somehow, not nice compared to
      //     SLanguage and SModuleReference of other methods.
      return Collections.emptySet();
    }

    @NotNull
    public Collection<SLanguage> getLanguages(ModuleType contextModule, SModel model) {
      return Collections.emptyList();
    }

    public Collection<SModuleReference> getDevKits(ModuleType contextModule, SModel forModel) {
      return Collections.emptyList();
    }
  }
}
