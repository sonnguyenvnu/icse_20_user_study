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
package jetbrains.mps.ide.ui.dialogs.properties.tables.models;

import com.intellij.util.ui.ItemRemovable;
import jetbrains.mps.ide.ui.dialogs.properties.PropertiesBundle;
import jetbrains.mps.project.DevKit;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.util.Condition;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsedLangsTableModel extends AbstractTableModel implements ItemRemovable {
  private final SRepository myRepository;
  private final String myColumnName;
  private List<SLanguage> myLanguageItems = new ArrayList<>();
  private List<SModuleReference> myDevKitItems = new ArrayList<>();

  public static final int ITEM_COLUMN = 0;
  private Collection<SLanguage> myInitialLanguages;
  private Collection<SModuleReference> myInitialDevKits;


  public UsedLangsTableModel(SRepository repository) {
    this(repository, PropertiesBundle.message("mps.properties.configurable.tablemodel.usedlanguages.column.item"));
  }

  public UsedLangsTableModel(SRepository repository, String columnName) {
    myRepository = repository;
    myColumnName = columnName;
  }

  public void init(Collection<SLanguage> usedLanguages, Collection<SModuleReference> usedDevKits) {
    myInitialLanguages = usedLanguages;
    myInitialDevKits = usedDevKits;
    myLanguageItems.addAll(usedLanguages);
    myDevKitItems.addAll(usedDevKits);
  }

  public void addItem(final SModuleReference item) {
    SModule m = new ModelAccessHelper(myRepository).runReadAction(() -> item.resolve(myRepository));
    if (m instanceof Language) {
      final SLanguage lang = MetaAdapterFactory.getLanguage(item);
      addItem(lang);
    } else if (m instanceof DevKit) {
      if (!myDevKitItems.contains(item)) {
        myDevKitItems.add(item);
      }
    }
  }

  public void addItem(SLanguage lang) {
    if (!myLanguageItems.contains(lang)) {
      myLanguageItems.add(lang);
    }
  }

  @Override
  public void removeRow(int idx) {
    if (idx >= myLanguageItems.size()) {
      idx -= myLanguageItems.size();
      myDevKitItems.remove(idx);
    } else {
      myLanguageItems.remove(idx);
    }
  }

  @Override
  public int getRowCount() {
    return myLanguageItems.size() + myDevKitItems.size();
  }

  @Override
  public int getColumnCount() {
    return 1;
  }

  @Override
  public String getColumnName(int column) {
    assert column == ITEM_COLUMN;
    return myColumnName;
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    if(columnIndex == ITEM_COLUMN) {
      return Import.class;
    }
    return super.getColumnClass(columnIndex);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    assert columnIndex == ITEM_COLUMN;
    return getValueAt(rowIndex);
  }

  public Import getValueAt(int rowIndex) {
    if (rowIndex >= myLanguageItems.size()) {
      rowIndex -= myLanguageItems.size();
      return new Import(myDevKitItems.get(rowIndex));
    } else {
      return new Import(myLanguageItems.get(rowIndex));
    }
  }

  public boolean isModified() {
    return !(myLanguageItems.containsAll(myInitialLanguages)
        && myInitialLanguages.containsAll(myLanguageItems)
        && myDevKitItems.containsAll(myInitialDevKits)
        && myInitialDevKits.containsAll(myDevKitItems)
    );
  }

  public void fillResult(Collection<SLanguage> languages, Collection<SModuleReference> devkits) {
    languages.addAll(myLanguageItems);
    devkits.addAll(myDevKitItems);
  }

  // union (SLanguage | DevKit)
  public static final class Import {
    public final SLanguage myLanguage;
    public final SModuleReference myDevKit;
    public Import(SLanguage lang) {
      myLanguage = lang;
      myDevKit = null;
    }
    public Import(SModuleReference devkit) {
      myDevKit = devkit;
      myLanguage = null;
    }

    @Override
    public int hashCode() {
      return 31 * (myLanguage == null ? myDevKit.hashCode() : myLanguage.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Import) {
        Import o = (Import) obj;
        return myLanguage == o.myLanguage && myDevKit == o.myDevKit;
      }
      return false;
    }

    @Override
    public String toString() {
      return myLanguage != null ? myLanguage.getQualifiedName() : myDevKit.getModuleName();
    }
  }

  public static final class ValidImportCondition implements Condition<Import> {
    private final SRepository myContextRepo;
    private final LanguageRegistry myLanguageRegistry;

    public ValidImportCondition(@NotNull Project project) {
      myContextRepo = project.getRepository();
      myLanguageRegistry = project.getComponent(LanguageRegistry.class);
    }

    @Override
    public boolean met(Import anImport) {
      if (anImport.myLanguage != null) {
        return myLanguageRegistry.getLanguage(anImport.myLanguage) != null;
      }
      return anImport.myDevKit.resolve(myContextRepo) != null;
    }
  }
}
