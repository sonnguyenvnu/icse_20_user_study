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
package jetbrains.mps.util;

import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.project.structure.modules.Dependency;
import jetbrains.mps.project.structure.modules.GeneratorDescriptor;
import jetbrains.mps.project.structure.modules.LanguageDescriptor;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_AbstractRef;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_ExternalRef;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_RefSet;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingConfig_SimpleRef;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingPriorityRule;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.SModelInternal;
import jetbrains.mps.smodel.StaticReference;
import jetbrains.mps.smodel.adapter.MetaAdapterByDeclaration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.EditableSModel;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SDependency;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.ModelRoot;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility class that provides model/module reference updating in a group of models/modules.
 *
 * Expected workflow:
 * You collect models & modules where you want update references
 * by {@link #addModelToAdjust(SModel, SModel)} and {@link #addModuleToAdjust(SModule, SModule)}.
 *
 * Than you invoke {@link #adjust()} to replace all old references with new references in collected models & modules.
 *
 * @author Radimir.Sorokin
 *
 */
public final class ReferenceUpdater {
  public static final Comparator<SModel> MODEL_BY_NAME_COMPARATOR = Comparator.comparing(m -> m.getName().getValue());

  private final List<SModule> myModules = new ArrayList<>();
  private final List<SModel> myModels = new ArrayList<>();

  private final Map<SModuleReference, SModuleReference> myModuleReferenceMap = new HashMap<>();
  private final Map<SModelReference, SModelReference> myModelReferenceMap = new HashMap<>();
  private final Map<SLanguage, SLanguage> myUsedLanguagesMap = new HashMap<>();

  private boolean myAdjusted = false;

  /**
   * Add {@code newModule} to adjust.
   * After adjusting, other modules & models will refer to {@code newModule} instead of {@code oldModule}
   * Updates inner models by default
   *
   * @param oldModule old module - others contain refs to it
   * @param newModule new module - others will contain refs to it
   */
  public void addModuleToAdjust(@NotNull SModule oldModule, @NotNull SModule newModule) throws RefUpdateException {
    assertNotAdjusted();
    addModuleToAdjustImpl(oldModule, newModule);

    if (oldModule instanceof Language && newModule instanceof Language) {
      addLanguageToAdjustImpl((Language) oldModule, (Language) newModule);
    }
  }

  private void addModuleToAdjustImpl(@NotNull SModule oldModule, @NotNull SModule newModule) throws RefUpdateException {
    myModules.add(newModule);
    myModuleReferenceMap.put(oldModule.getModuleReference(), newModule.getModuleReference());

    // AP let us assume that the models are in the same order (it is rational since we are _cloning_ modules)
    List<ModelRoot> oldRoots = IterableUtil.asList(oldModule.getModelRoots());
    List<ModelRoot> newRoots = IterableUtil.asList(newModule.getModelRoots());
    if (oldRoots.size() != newRoots.size()) {
      throw new RefUpdateException("The number of the model roots are not the same");
    }
    for (int i = 0; i < oldRoots.size(); ++i) {
      ModelRoot oldRoot = oldRoots.get(i);
      ModelRoot newRoot = newRoots.get(i);
      if (!oldRoot.getClass().equals(newRoot.getClass())) {
        throw new RefUpdateException("Model roots are of different types " + oldRoot + " " + newRoot);
      }
      List<SModel> oldModels = new ArrayList<SModel>(IterableUtil.asList(oldRoot.getModels()));
      oldModels.sort(MODEL_BY_NAME_COMPARATOR);
      List<SModel> newModels = new ArrayList<SModel>(IterableUtil.asList(newRoot.getModels()));
      newModels.sort(MODEL_BY_NAME_COMPARATOR);

      if (oldModels.size() != newModels.size()) {
        throw new RefUpdateException("Model roots are supposed to have the same number of models " + oldRoot + " " + newRoot);
      }
      for (int j = 0; j < oldModels.size(); ++j) {
        SModel oldModel = oldModels.get(j);
        SModel newModel = newModels.get(j);
        addModelToAdjustImpl(oldModel, newModel);
      }
    }
  }

  private void addLanguageToAdjustImpl(@NotNull Language oldLanguage, @NotNull Language newLanguage) throws RefUpdateException {
    myUsedLanguagesMap.put(
        MetaAdapterByDeclaration.getLanguage(oldLanguage),
        MetaAdapterByDeclaration.getLanguage(newLanguage)
    );
    if (oldLanguage.getOwnedGenerators().size() != newLanguage.getOwnedGenerators().size()) {
      throw new RefUpdateException("The number of generators do not match!");
    }
    Iterator<Generator> newGeneratorIt = newLanguage.getOwnedGenerators().iterator();
    for (Generator oldGenerator : oldLanguage.getOwnedGenerators()) {
      addModuleToAdjustImpl(oldGenerator, newGeneratorIt.next());
    }
  }

  /**
   * Add {@code newModel} to adjust if it is editable.
   * After adjusting, other models will refer to {@code newModel} instead of {@code oldModel}
   *
   * @param oldModel old model - other models contain refs to it
   * @param newModel new model - other models will be contain refs to it
   */
  public void addModelToAdjust(@NotNull SModel oldModel, @NotNull SModel newModel) throws RefUpdateException {
    assertNotAdjusted();
    if (newModel.isReadOnly()) {
      throw new RefUpdateException(String.format("The model '%s' is readonly", newModel));
    }

    addModelToAdjustImpl(oldModel, newModel);
  }

  /**
   * For each collected module:
   * 1) update all module dependencies according to {@link #myModuleReferenceMap}
   *
   * For each collected model:
   * 1) update all model references in model imports according to {@link #myModelReferenceMap}
   * 2) update all languages imports according to {@link #myUsedLanguagesMap}
   * 3) update all model references in it's nodes according to {@link #myModelReferenceMap}
   *
   * It saves all models after references updating.
   * Note that after calling this method you can't use this instance to update references.
   */
  public void adjust() throws RefUpdateException {
    assertNotAdjusted();

    myModules.forEach(module -> {
      if (module instanceof Generator) {
        adjustGenerator((Generator) module);
      }else if (module instanceof Language) {
        adjustLanguage((Language) module);
      } else if (module instanceof AbstractModule) {
        adjustModule((AbstractModule) module);
      }
    });

    myModels.forEach(model -> {
      SModelInternal modelInternal = (SModelInternal) model;
      for (SModelReference aImport : modelInternal.getModelImports()) {
        if (myModelReferenceMap.containsKey(aImport)) {
          modelInternal.deleteModelImport(aImport);
          modelInternal.addModelImport(myModelReferenceMap.get(aImport));
        }
      }
      List<SLanguage> usedLanguages = new ArrayList<>(modelInternal.importedLanguageIds());
      for (SLanguage usedLanguage : usedLanguages) {
        if (myUsedLanguagesMap.containsKey(usedLanguage)) {
          modelInternal.deleteLanguageId(usedLanguage);
          modelInternal.addLanguage(myUsedLanguagesMap.get(usedLanguage));
        }
      }
      model.getRootNodes().forEach(this::updateReferences);
    });
    myModels.forEach((model -> {
      if (model instanceof EditableSModel) {
        ((EditableSModel) model).setChanged(true);
        ((EditableSModel) model).save();
      }
    }));
    myAdjusted = true;
  }

  public List<SModule> getModules() {
    return Collections.unmodifiableList(myModules);
  }

  public List<SModel> getModels() {
    return Collections.unmodifiableList(myModels);
  }

  private void addModelToAdjustImpl(@NotNull SModel oldModel, @NotNull SModel newModel) {
    if (!newModel.isReadOnly()) {
      myModels.add(newModel);
    }
    myModelReferenceMap.put(oldModel.getReference(), newModel.getReference());
  }

  private void assertNotAdjusted() throws RefUpdateException {
    if (myAdjusted) {
      throw new RefUpdateException("ReferenceUpdater instances can't be reused");
    }
  }

  private void updateReferences(SNode node) {
    node.getReferences().forEach(ref -> {
      if (ref instanceof StaticReference) {
        StaticReference reference = (StaticReference) ref;
        SModelReference targetSModelReference = reference.getTargetSModelReference();
        if (myModelReferenceMap.containsKey(targetSModelReference)) {
          StaticReference newReference = new StaticReference(
              reference.getLink(),
              node,
              myModelReferenceMap.get(targetSModelReference),
              reference.getTargetNodeId(),
              reference.getResolveInfo()
          );
          node.setReference(newReference.getLink(), newReference);
        }
      }
    });
    node.getChildren().forEach(this::updateReferences);
  }

  private void adjustLanguage(Language language) {
    adjustModule(language);

    LanguageDescriptor descriptor = language.getModuleDescriptor();
    Set<SModelReference> accessoryModels = descriptor.getAccessoryModels();
    Set<SModelReference> newAccessoryModels = new LinkedHashSet<>();
    for (SModelReference modelReference : accessoryModels) {
      SModelReference newModelReference = myModelReferenceMap.get(modelReference);
      newAccessoryModels.add(newModelReference != null ? newModelReference : modelReference);
    }
    accessoryModels.clear();
    accessoryModels.addAll(newAccessoryModels);
    language.setModuleDescriptor(descriptor);
  }

  private void adjustGenerator(Generator generator) {
    adjustModule(generator);

    GeneratorDescriptor descriptor = generator.getModuleDescriptor();
    for (MappingPriorityRule rule : descriptor.getPriorityRules()) {
      adjustMappingConfig(rule.getLeft());
      adjustMappingConfig(rule.getRight());
    }
    generator.setModuleDescriptor(descriptor);
  }

  private void adjustMappingConfig(MappingConfig_AbstractRef config) {
    if (config instanceof MappingConfig_SimpleRef) {
      MappingConfig_SimpleRef config_simpleRef = (MappingConfig_SimpleRef) config;
      SModelReference oldModelRef = PersistenceFacade.getInstance().createModelReference(config_simpleRef.getModelUID());
      SModelReference newModelRef = myModelReferenceMap.get(oldModelRef);
      if (newModelRef != null) {
        config_simpleRef.setModelUID(newModelRef.toString());
      }
    } else if (config instanceof MappingConfig_ExternalRef) {
      MappingConfig_ExternalRef config_externalRef = (MappingConfig_ExternalRef) config;
      SModuleReference oldModuleRef = config_externalRef.getGenerator();
      SModuleReference newModuleRef = myModuleReferenceMap.get(oldModuleRef);
      if (newModuleRef != null) {
        config_externalRef.setGenerator(newModuleRef);
      }
      adjustMappingConfig(config_externalRef.getMappingConfig());
    } else if (config instanceof MappingConfig_RefSet) {
      for (MappingConfig_AbstractRef configElement: ((MappingConfig_RefSet) config).getMappingConfigs()) {
        adjustMappingConfig(configElement);
      }
    }
  }

  private void adjustModule(AbstractModule module) {
    for (SDependency dependency : module.getDeclaredDependencies()) {
      SModuleReference depReference = dependency.getTargetModule();
      if (myModuleReferenceMap.containsKey(depReference)) {
        module.removeDependency(new Dependency(depReference, dependency.getScope(), dependency.isReexport()));
        module.addDependency(myModuleReferenceMap.get(depReference), dependency.isReexport());
      }
    }
  }

  public static final class RefUpdateException extends RuntimeException {
    public RefUpdateException(@NotNull String message) {
      super(message);
    }
  }
}
