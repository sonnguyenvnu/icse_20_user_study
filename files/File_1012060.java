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
package jetbrains.mps.ide.ui.dialogs.properties.tables.models;

import jetbrains.mps.ide.ui.dialogs.properties.tables.items.DependenciesTableItem;
import jetbrains.mps.ide.ui.dialogs.properties.tables.items.DependenciesTableItem.ModuleType;
import jetbrains.mps.project.structure.modules.Dependency;
import jetbrains.mps.project.structure.modules.DevkitDescriptor;
import jetbrains.mps.project.structure.modules.GeneratorDescriptor;
import jetbrains.mps.project.structure.modules.LanguageDescriptor;
import jetbrains.mps.project.structure.modules.ModuleDescriptor;
import jetbrains.mps.project.structure.modules.SolutionDescriptor;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SDependencyScope;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.LinkedHashSet;
import java.util.Set;

public class ModuleDependTableModel extends DependTableModel<ModuleDescriptor> {
  private final SRepository myRepository;

  public ModuleDependTableModel(@NotNull SRepository repository, ModuleDescriptor descriptor) {
    super(descriptor);
    myRepository = repository;
  }

  @Override
  public void init() {
    if(!(myItem instanceof DevkitDescriptor)) {
      myRepository.getModelAccess().runReadAction(() -> {
        for(Dependency dependency : myItem.getDependencies()) {
          SModuleReference moduleReference = dependency.getModuleRef();
          final SModule module = moduleReference.resolve(myRepository);
          if(module instanceof Language) {
            addLanguageItem(dependency);
          } else if(module instanceof Generator) {
            addGeneratorItem(dependency);
          } else {
            // XXX why not checked for Solution?
            addUnspecifiedItem(dependency);
          }
        }
      });
    }

    if(myItem instanceof LanguageDescriptor) {
      LanguageDescriptor languageDescriptor = (LanguageDescriptor) myItem;

      for(SModuleReference moduleReference : languageDescriptor.getExtendedLanguages()) {
        addLanguageItem(new Dependency(moduleReference, SDependencyScope.EXTENDS));
      }
    }
    else if(myItem instanceof GeneratorDescriptor) {
      GeneratorDescriptor generatorDescriptor = (GeneratorDescriptor) myItem;

      for(SModuleReference moduleReference : generatorDescriptor.getDepGenerators()) {
        addGeneratorItem(new Dependency(moduleReference, SDependencyScope.EXTENDS));
      }
    }
    else if(myItem instanceof DevkitDescriptor) {
      DevkitDescriptor devkitDescriptor = (DevkitDescriptor) myItem;

      for(SModuleReference moduleReference : devkitDescriptor.getExtendedDevkits()) {
        addDevkitItem(new Dependency(moduleReference, SDependencyScope.EXTENDS));
      }

      for(SModuleReference lang : devkitDescriptor.getExportedLanguages()) {
        addLanguageItem(new Dependency(lang, SDependencyScope.EXTENDS));
      }

      for(SModuleReference moduleReference : devkitDescriptor.getExportedSolutions()) {
        addSolutionItem(new Dependency(moduleReference, SDependencyScope.EXTENDS));
      }
    }
  }

  @Override
  public boolean isModified() {
    boolean equals = true;

    if(!(myItem instanceof DevkitDescriptor))
      equals = myItem.getDependencies().containsAll(getDependencies()) && getDependencies().containsAll(myItem.getDependencies());

    if(myItem instanceof LanguageDescriptor) {
      LanguageDescriptor languageDescriptor = (LanguageDescriptor) myItem;
      equals = equals && languageDescriptor.getExtendedLanguages().containsAll(getExtendedModules()) && getExtendedModules().containsAll(languageDescriptor.getExtendedLanguages());
    }
    else if(myItem instanceof SolutionDescriptor) {
    }
    else if(myItem instanceof GeneratorDescriptor) {
      GeneratorDescriptor generatorDescriptor = (GeneratorDescriptor) myItem;
      equals = equals && generatorDescriptor.getDepGenerators().containsAll(getExtendedModules()) && getExtendedModules().containsAll(generatorDescriptor.getDepGenerators());
    }
    else if(myItem instanceof DevkitDescriptor) {
      DevkitDescriptor devkitDescriptor = (DevkitDescriptor) myItem;

      equals &= devkitDescriptor.getExtendedDevkits().containsAll(getModulesByType(ModuleType.DEVKIT));
      equals &= getModulesByType(ModuleType.DEVKIT).containsAll(devkitDescriptor.getExtendedDevkits());

      equals &= devkitDescriptor.getExportedLanguages().containsAll(getModulesByType(ModuleType.LANGUAGE));
      equals &= getModulesByType(ModuleType.LANGUAGE).containsAll(devkitDescriptor.getExportedLanguages());

      equals &= devkitDescriptor.getExportedSolutions().containsAll(getModulesByType(ModuleType.SOLUTION));
      equals &= getModulesByType(ModuleType.SOLUTION).containsAll(devkitDescriptor.getExportedSolutions());
    }

    return !equals;
  }

  @Override
  public void apply() {

    if(!(myItem instanceof DevkitDescriptor)) {
      myItem.getDependencies().clear();
      myItem.getDependencies().addAll(getDependencies());
    }

    if(myItem instanceof LanguageDescriptor) {
      LanguageDescriptor languageDescriptor = (LanguageDescriptor) myItem;

      languageDescriptor.getExtendedLanguages().clear();
      languageDescriptor.getExtendedLanguages().addAll(getExtendedModules());
    }
    else if(myItem instanceof SolutionDescriptor) {
    }
    else if(myItem instanceof GeneratorDescriptor) {
      GeneratorDescriptor generatorDescriptor = (GeneratorDescriptor) myItem;

      generatorDescriptor.getDepGenerators().clear();
      generatorDescriptor.getDepGenerators().addAll(getExtendedModules());
    }
    else if(myItem instanceof DevkitDescriptor) {
      DevkitDescriptor devkitDescriptor = (DevkitDescriptor) myItem;

      devkitDescriptor.getExtendedDevkits().clear();
      devkitDescriptor.getExtendedDevkits().addAll(getModulesByType(ModuleType.DEVKIT));

      devkitDescriptor.getExportedLanguages().clear();
      devkitDescriptor.getExportedLanguages().addAll(getModulesByType(ModuleType.LANGUAGE));

      devkitDescriptor.getExportedSolutions().clear();
      devkitDescriptor.getExportedSolutions().addAll(getModulesByType(ModuleType.SOLUTION));
    }
  }

  private Set<Dependency> getDependencies() {
    Set<Dependency> dependencies = new LinkedHashSet<>();
    for(DependenciesTableItem tableItem : myTableItems) {
      // FIXME here's comes a hack. We used to save only 'DEFAULT' SDependency with Dependency,
      // FIXME        and 'EXTENDS' as SModuleReference (@see getExtendedModules, below).
      // FIXME        However, with support for other dependency scopes introduced, we are going to transit
      // FIXME        to single Dependency presentation. Meanwhile (as there no scopes but EXTENDS and DEFAULT in legacy descriptors)
      // FIXME        this code simply leaves EXTENDS processing as it was, but saves all other dependencies with Dependency object
      if (tableItem.getItem().getScope() != SDependencyScope.EXTENDS) {
        dependencies.add(tableItem.getItem().copy()); // XXX not sure copy is needed here
      }
    }
    return dependencies;
  }

  /**
   * Public solely for use from condition of ModulePropertiesConfigurable.ModuleDependenciesTab that needs
   * to tell actual state of modules picked as 'extends'
   * @return
   */
  public Set<SModuleReference> getExtendedModules() {
    Set<SModuleReference> set = new LinkedHashSet<>();
    for(DependenciesTableItem tableItem : myTableItems)
      if(tableItem.getItem().getScope() == SDependencyScope.EXTENDS) // XXX see getDependencies() above
        set.add(tableItem.getItem().getModuleRef());

    return set;
  }

  private Set<SModuleReference> getModulesByType(ModuleType type) {
    Set<SModuleReference> set = new LinkedHashSet<>();
    for(DependenciesTableItem tableItem : myTableItems)
      if(tableItem.getModuleType().equals(type))
        set.add(tableItem.getItem().getModuleRef());

    return set;
  }

  @Override
  public String getColumnName(int column) {
    if (getItemColumnIndex() == column) {
      return "Module";
    }
    return super.getColumnName(column);
  }

  @Override
  public int getColumnCount() {
    if(myItem instanceof DevkitDescriptor) return 2;
    return super.getColumnCount();
  }

  @Override
  public int getRoleColumnIndex() {
    if(myItem instanceof DevkitDescriptor) return -1;
    return super.getRoleColumnIndex();
  }

  @Override
  public int getItemColumnIndex() {
    if(myItem instanceof DevkitDescriptor) return 0;
    return super.getItemColumnIndex();
  }

  @Override
  public int getExportColumnIndex() {
    if(myItem instanceof DevkitDescriptor) return -1;
    return super.getExportColumnIndex();
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    if(columnIndex == getRoleColumnIndex())
      return true;
    return super.isCellEditable(rowIndex, columnIndex);
  }
}
