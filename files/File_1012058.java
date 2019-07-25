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
package jetbrains.mps.ide.ui.dialogs.properties;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.SpeedSearchComparator;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.table.JBTable;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import jetbrains.mps.FilteredGlobalScope;
import jetbrains.mps.extapi.model.TransientSModel;
import jetbrains.mps.extapi.persistence.FileDataSource;
import jetbrains.mps.extapi.persistence.FolderDataSource;
import jetbrains.mps.findUsages.CompositeFinder;
import jetbrains.mps.generator.ModelGenerationStatusManager;
import jetbrains.mps.icons.MPSIcons;
import jetbrains.mps.icons.MPSIcons.General;
import jetbrains.mps.ide.findusages.model.IResultProvider;
import jetbrains.mps.ide.findusages.model.SearchQuery;
import jetbrains.mps.ide.findusages.model.holders.GenericHolder;
import jetbrains.mps.ide.findusages.model.holders.LanguageHolder;
import jetbrains.mps.ide.findusages.model.holders.ModelsHolder;
import jetbrains.mps.ide.findusages.model.holders.ModuleRefHolder;
import jetbrains.mps.ide.findusages.model.scopes.ModelsScope;
import jetbrains.mps.ide.findusages.view.FindUtils;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.ui.dialogs.properties.choosers.CommonChoosers;
import jetbrains.mps.ide.ui.dialogs.properties.input.ModuleCollector;
import jetbrains.mps.ide.ui.dialogs.properties.renders.DependencyCellState;
import jetbrains.mps.ide.ui.dialogs.properties.renders.LanguageTableCellRenderer;
import jetbrains.mps.ide.ui.dialogs.properties.renders.ModelTableCellRender;
import jetbrains.mps.ide.ui.dialogs.properties.tables.models.ModelImportedModelsTableModel;
import jetbrains.mps.ide.ui.dialogs.properties.tables.models.UsedLangsTableModel;
import jetbrains.mps.ide.ui.dialogs.properties.tables.models.UsedLangsTableModel.ValidImportCondition;
import jetbrains.mps.ide.ui.dialogs.properties.tabs.BaseTab;
import jetbrains.mps.ide.ui.finders.LanguageUsagesFinder;
import jetbrains.mps.ide.ui.finders.ModelUsagesFinder;
import jetbrains.mps.kernel.model.MissingDependenciesFixer;
import jetbrains.mps.project.DevKit;
import jetbrains.mps.project.ModuleInstanceCondition;
import jetbrains.mps.project.Project;
import jetbrains.mps.project.VisibleModuleCondition;
import jetbrains.mps.project.dependency.VisibilityUtil;
import jetbrains.mps.scope.ConditionalScope;
import jetbrains.mps.smodel.DefaultSModelDescriptor;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.MPSModuleRepository;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.ModelDependencyScanner;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.util.CollectionUtil;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.ComputeRunnable;
import jetbrains.mps.util.ConditionalIterable;
import jetbrains.mps.util.FileUtil;
import jetbrains.mps.util.IterableUtil;
import jetbrains.mps.util.NotCondition;
import jetbrains.mps.workbench.choose.ModelScopeIterable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.EditableSModel;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SearchScope;
import org.jetbrains.mps.openapi.persistence.DataSource;
import org.jetbrains.mps.openapi.persistence.NullDataSource;
import org.jetbrains.mps.util.Condition;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelPropertiesConfigurable extends MPSPropertiesConfigurable {
  private ModelProperties myModelProperties;
  private SModel myModelDescriptor;
  private boolean myInPlugin = false;

  public ModelPropertiesConfigurable(SModel modelDescriptor, Project project) {
    this(modelDescriptor, project, false);
  }

  public ModelPropertiesConfigurable(final SModel modelDescriptor, Project project, boolean inPlugin) {
    super(project);
    myModelDescriptor = modelDescriptor;
    // readAction here is a hack, rather action shall do read. Alas, there are few places to get fixed, can't do it right now.
    myModelProperties = new ModelAccessHelper(project.getModelAccess()).runReadAction(() -> new ModelProperties(modelDescriptor));
    myInPlugin = inPlugin;

    registerTabs(/*new ModelCommonTab(),*/ new ModelDependenciesComponent(), new ModelUsedLanguagesTab(), new InfoTab());
  }

  @Nls
  @Override
  public String getDisplayName() {
    return String.format(PropertiesBundle.message("model.title"), myModelDescriptor.getModelName());
  }

  @Override
  public boolean isModified() {
    if (myModelDescriptor.isReadOnly()) {
      return false;
    }
    return super.isModified();
  }

  @Override
  protected void save() {
    myModelProperties.saveChanges();
    // change of model properties might affect generation status. This explicit call is needed
    // unless model dispatch proper change events (which it does not at the moment), and project pane
    // got no other means to find out it needs to update generation status
    myProject.getComponent(ModelGenerationStatusManager.class).invalidateData(Collections.singleton(myModelDescriptor));
    new MissingDependenciesFixer(myModelDescriptor).fixModuleDependencies();

    if (!(myModelDescriptor.getSource() instanceof NullDataSource)) {
      ((EditableSModel) myModelDescriptor).save();
    }
  }

  public class ModelCommonTab extends CommonTab {

    private ModelDependenciesComponent myModelDependenciesComponent;

    @Override
    protected String getConfigItemName() {
      return myModelDescriptor.getModelName();
    }

    @Override
    protected String getConfigItemPath() {
      if (myModelDescriptor instanceof EditableSModel) {
        DataSource source = myModelDescriptor.getSource();
        if (source instanceof FileDataSource) {
          return FileUtil.getCanonicalPath(((FileDataSource) source).getFile().getPath());
        }
      }
      return "";
    }

    @Override
    protected JComponent getBottomComponent() {
      myModelDependenciesComponent = new ModelDependenciesComponent();
      return myModelDependenciesComponent.getImportedModelsComponent();
    }

    @Override
    public void init() {
      super.init();
      myTextFieldName.setEditable(false);
    }

    @Override
    public boolean isModified() {
      return super.isModified() || myModelDependenciesComponent.isModified();
    }

    @Override
    public void apply() {
      myModelDependenciesComponent.apply();
    }
  }

  /*package*/ Set<SModelReference> getActualCrossModelReferences() {
    return new ModelAccessHelper(myProject.getRepository()).runReadAction(() -> {
      final ModelDependencyScanner modelScanner = new ModelDependencyScanner();
      modelScanner.crossModelReferences(true).usedLanguages(false).usedConcepts(false).walk(myModelDescriptor);
      return modelScanner.getCrossModelReferences();
    });
  }

  protected class ModelDependenciesComponent extends BaseTab {
    private ModelImportedModelsTableModel myImportedModels;
    private JPanel myImportedModelsComponent;
    private FindActionButton myFindActionButton;

    public JPanel getImportedModelsComponent() {
      return myImportedModelsComponent;
    }

    public ModelDependenciesComponent() {
      super(PropertiesBundle.message("mps.properties.dependencies.title"), General.Dependencies,
          PropertiesBundle.message("mps.properties.dependencies.tip"));
      myImportedModels = new ModelImportedModelsTableModel(myModelProperties);
      myImportedModels.init();
    }

    protected boolean confirmRemove(final Object value) {
      if (value instanceof SModelReference) {
        final SModelReference modelReference = (SModelReference) value;
        if (getActualCrossModelReferences().contains(modelReference)) {
          ViewUsagesDeleteDialog viewUsagesDeleteDialog = new ViewUsagesDeleteDialog(
              ProjectHelper.toIdeaProject(myProject), "Delete imported model",
              "This model is used in model. Do you really want to delete it?", "Model state will become inconsistent") {
            @Override
            public void doViewAction() {
              myFindActionButton.actionPerformed(null);
            }
          };
          viewUsagesDeleteDialog.show();
          return viewUsagesDeleteDialog.isOK();
        }
      }

      return true;
    }

    @Override
    public void init() {
      myImportedModelsComponent = new JPanel();
      myImportedModelsComponent.setLayout(new GridLayoutManager(2, 1, MPSPropertiesConfigurable.INSETS, -1, -1));

      final JBTable importedModelsTable = new JBTable();
      importedModelsTable.setShowHorizontalLines(false);
      importedModelsTable.setShowVerticalLines(false);
      importedModelsTable.setAutoCreateRowSorter(false);
      importedModelsTable.setAutoscrolls(true);
      importedModelsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

      importedModelsTable.setModel(myImportedModels);

      ModelTableCellRender cellRender = new ModelTableCellRender(myProject.getRepository());
      cellRender.addCellState(m -> m == null, DependencyCellState.NOT_AVAILABLE);
      cellRender.addCellState(m -> !VisibilityUtil.isVisible(myModelDescriptor.getModule(), m), DependencyCellState.NOT_IN_SCOPE);
      final Set<SModelReference> actualCrossModelRefs = getActualCrossModelReferences();
      cellRender.addCellState(m -> !actualCrossModelRefs.contains(m.getReference()), DependencyCellState.UNUSED);
      importedModelsTable.setDefaultRenderer(SModelReference.class, cellRender);

      ToolbarDecorator decorator = ToolbarDecorator.createDecorator(importedModelsTable);
      decorator.setAddAction(anActionButton -> {
        // XXX much like ModelImportHelper#addImport (scope-wise), might be worth a refactoring
        ArrayList<SModelReference> models = new ArrayList<>(200);
        Condition<SModel> notTransient = m -> !(m instanceof TransientSModel);
        SearchScope globalScope = new ConditionalScope(new FilteredGlobalScope(), null, notTransient);
        new ModelScopeIterable(globalScope, myProject.getRepository()).forEach(models::add);
        List<SModelReference> list = CommonChoosers.showDialogModelCollectionChooser(ProjectHelper.toIdeaProject(myProject), models, null);
        for (SModelReference reference : list) {
          if (!myModelDescriptor.getReference().equals(reference)) {
            myImportedModels.addItem(reference);
          }
        }
      }).setRemoveAction(new RemoveEntryAction(importedModelsTable) {
        @Override
        protected boolean confirmRemove(int row) {
          return ModelDependenciesComponent.this.confirmRemove(importedModelsTable.getValueAt(row, 0));
        }
      }).addExtraAction(myFindActionButton = new FindActionButton(importedModelsTable) {
        @Override
        public void actionPerformed(AnActionEvent e) {
          final SearchScope scope = new ModelsScope(myModelDescriptor);
          final List<SModelReference> modelReferences = new ArrayList<>();
          for (int i : myTable.getSelectedRows()) {
            modelReferences.add(myImportedModels.getValueAt(i));
          }
          final SearchQuery query = new SearchQuery(new ModelsHolder(modelReferences), scope);
          final IResultProvider provider = FindUtils.makeProvider(new CompositeFinder(new ModelUsagesFinder()));
          showUsageImpl(query, provider);
          forceCancelCloseDialog();
        }
      }).addExtraAction(new AnActionButton() {
        {
          getTemplatePresentation().setIcon(MPSIcons.General.ModelChecker);
          getTemplatePresentation().setText("Remove unused model imports");
        }

        @Override
        public boolean isEnabled() {
          return super.isEnabled() && anyModelNotUsed();
        }

        private boolean anyModelNotUsed() {
          return myImportedModels.getItemsStream().anyMatch(mr -> !actualCrossModelRefs.contains(mr));
        }

        @Override
        public void actionPerformed(AnActionEvent e) {
          boolean anyRemoved = false;
          for(int row = myImportedModels.getRowCount()-1; row >= 0; row--) {
            if (!actualCrossModelRefs.contains(myImportedModels.getValueAt(row))) {
              myImportedModels.removeRow(row);
              anyRemoved = true;
            }
          }
          if (anyRemoved) {
            myImportedModels.fireTableDataChanged();
            importedModelsTable.clearSelection();
          }
        }
      });
      decorator.setPreferredSize(new Dimension(500, 150));

      JPanel table = decorator.createPanel();
      table.setBorder(IdeBorderFactory.createBorder());
      myImportedModelsComponent.add(table, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

      new TableColumnSearch(importedModelsTable, 0).setComparator(new SpeedSearchComparator(false, true));

      setTabComponent(myImportedModelsComponent);
    }

    @Override
    public boolean isModified() {
      return myImportedModels.isModified();
    }

    @Override
    public void apply() {
      myImportedModels.apply();
    }
  }

  public class ModelUsedLanguagesTab extends UsedLanguagesTab {
    private IsLanguageInUse myInUseCondition;

    @Override
    protected UsedLangsTableModel getUsedLangsTableModel() {
      UsedLangsTableModel rv = new UsedLangsTableModel(myProject.getRepository());
      rv.init(myModelProperties.getUsedLanguages(), myModelProperties.getUsedDevKits());
      return rv;
    }

    @Override
    public void apply() {
      myModelProperties.getUsedLanguages().clear();
      myModelProperties.getUsedDevKits().clear();
      myUsedLangsTableModel.fillResult(myModelProperties.getUsedLanguages(), myModelProperties.getUsedDevKits());
    }

    @Override
    protected TableCellRenderer getTableCellRender() {
      SRepository contextRepo = myProject.getRepository();
      Set<SLanguage> inUse = new ModelAccessHelper(myProject.getModelAccess()).runReadAction(new ComputeUsedLanguages(myModelDescriptor));
      myInUseCondition = new IsLanguageInUse(inUse, myModelProperties.getUsedLanguages());
      LanguageTableCellRenderer usedInModel = new LanguageTableCellRenderer(contextRepo);
      usedInModel.addCellState(NotCondition.negate(new ValidImportCondition(myProject)), DependencyCellState.NOT_AVAILABLE);
      usedInModel.addCellState(NotCondition.negate(myInUseCondition), DependencyCellState.UNUSED);
      return usedInModel;
    }

    protected void findUsages(final Object value) {
      final SearchScope scope = new ModelsScope(myModelDescriptor);
      final UsedLangsTableModel.Import entry = (UsedLangsTableModel.Import) value;
      final SearchQuery query = new SearchQuery(entry.myLanguage != null ? new LanguageHolder(entry.myLanguage) : new ModuleRefHolder(entry.myDevKit), scope);
      final IResultProvider provider = FindUtils.makeProvider(new LanguageUsagesFinder());
      // FIXME FindAction below uses slightly different code to perform search, merge. Unwrap devkit here, do not rely on LanguageUsageFinder to do that?
      showUsageImpl(query, provider);
      forceCancelCloseDialog();
    }

    @Override
    protected ToolbarDecorator createToolbar(JBTable usedLangsTable) {
      ToolbarDecorator decorator =  super.createToolbar(usedLangsTable);
      decorator.setAddAction(anActionButton -> {
        Iterable<SModule> modules = new ConditionalIterable<>(getProjectModules(), new ModuleInstanceCondition(Language.class, DevKit.class));
        modules = new ConditionalIterable<>(modules, new VisibleModuleCondition());
        ComputeRunnable<List<SModuleReference>> c = new ComputeRunnable<>(new ModuleCollector(modules));
        myProject.getModelAccess().runReadAction(c);
        List<SModuleReference> list = CommonChoosers.showModuleSetChooser(myProject, "Choose Language or DevKit", c.getResult());
        for (SModuleReference reference : list) {
          myUsedLangsTableModel.addItem(reference);
        }
        myUsedLangsTableModel.fireTableDataChanged();
      }).setRemoveAction(new RemoveEntryAction(usedLangsTable) {
        @Override
        protected boolean confirmRemove(int row) {
          final UsedLangsTableModel.Import entry = myUsedLangsTableModel.getValueAt(row);
          boolean inActualUse = new ModelAccessHelper(myProject.getModelAccess()).runReadAction(() -> myInUseCondition.met(entry));
          if (inActualUse) {
            ViewUsagesDeleteDialog viewUsagesDeleteDialog = new ViewUsagesDeleteDialog(
                ProjectHelper.toIdeaProject(myProject), "Delete used language",
                "This language is used by model. Do you really want to delete it?", "Model state will become inconsistent") {
              @Override
              public void doViewAction() {
                findUsages(entry);
              }
            };
            viewUsagesDeleteDialog.show();
            return viewUsagesDeleteDialog.isOK();
          }
          return true;
        }
      });

      decorator.addExtraAction(new FindActionButton(usedLangsTable) {
        @Override
        public void actionPerformed(AnActionEvent e) {
          final SearchScope scope = new ModelsScope(myModelDescriptor);
          final List<SLanguage> languages = getSelectedLanguages();
          final SearchQuery query = new SearchQuery(new GenericHolder<Collection<SLanguage>>(languages, "Languages"), scope);
          final IResultProvider provider = FindUtils.makeProvider(new CompositeFinder(new LanguageUsagesFinder()));
          showUsageImpl(query, provider);
          forceCancelCloseDialog();
        }
      });

      decorator.addExtraAction(new AnActionButton() {
        {
          getTemplatePresentation().setIcon(MPSIcons.General.ModelChecker);
          getTemplatePresentation().setText("Remove unused languages");
        }
        @Override
        public void actionPerformed(AnActionEvent e) {
          myProject.getModelAccess().runReadAction(() -> {
            boolean anyRemoved = false;
            for (int row = myUsedLangsTable.getRowCount() - 1; row >= 0; row--) {
              if (!myInUseCondition.met(myUsedLangsTableModel.getValueAt(row))) {
                myUsedLangsTableModel.removeRow(row);
                anyRemoved = true;
              }
            }
            if (anyRemoved) {
              myUsedLangsTableModel.fireTableDataChanged();
              myUsedLangsTable.clearSelection();
            }
          });
        }
      });
      return decorator;
    }
  }

  private class InfoTab extends BaseTab {
    private final boolean myIsDefSModelDescr;
    private JBCheckBox myDoNotGenerateCheckBox;
    private JBCheckBox myGenerateIntoModelFolderCheckBox;
    private UsedLangsTableModel myEngagedLanguagesModel;

    public InfoTab() {
      super(PropertiesBundle.message("model.info.title"), AllIcons.General.ExternalToolsSmall,
          PropertiesBundle.message("model.info.tip"));
      myIsDefSModelDescr = myInPlugin && myModelDescriptor instanceof DefaultSModelDescriptor;
    }

    @Override
    public void init() {
      int rowsCount = myIsDefSModelDescr ? 6 : 5;
      int rowIndex = 0;

      final JPanel panel = new JPanel();
      panel.setLayout(new GridLayoutManager(rowsCount, 1, INSETS, -1, -1));

      myDoNotGenerateCheckBox = new JBCheckBox(PropertiesBundle.message("model.info.checkboxDNG"),
          myModelProperties.isDoNotGenerate());
      panel.add(myDoNotGenerateCheckBox, new GridConstraints(rowIndex++, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

      if (myIsDefSModelDescr) {
        myGenerateIntoModelFolderCheckBox = new JBCheckBox(PropertiesBundle.message("model.info.checkboxGIMF"),
            myModelProperties.isGenerateIntoModelFolder());
        panel.add(myGenerateIntoModelFolderCheckBox,
            new GridConstraints(rowIndex++, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK, GridConstraints.SIZEPOLICY_FIXED, null, null, null,
                0, false));
      }

      panel.add(new JBLabel(PropertiesBundle.message("mps.properties.common.filepathlabel")),
          new GridConstraints(rowIndex++, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
              GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

      String filePath = "(not editable model)";
      if (myModelDescriptor instanceof EditableSModel) {
        DataSource source = myModelDescriptor.getSource();
        if (source instanceof FileDataSource) {
          filePath = FileUtil.getCanonicalPath(((FileDataSource) source).getFile().getPath());
        } else if (source instanceof FolderDataSource) {
          filePath = FileUtil.getCanonicalPath(((FolderDataSource) source).getFolder().getPath());
        }
      }
      JTextField textField = new JTextField();
      textField.setEditable(false);
      textField.setText(filePath);
      panel.add(textField, new GridConstraints(rowIndex++, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null));

      final JBTable languagesTable = new JBTable();
      languagesTable.setShowHorizontalLines(false);
      languagesTable.setShowVerticalLines(false);
      languagesTable.setAutoCreateRowSorter(false);
      languagesTable.setAutoscrolls(true);
      languagesTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

      myEngagedLanguagesModel = new UsedLangsTableModel(myProject.getRepository(), PropertiesBundle.message("model.info.engaged.title"));
      ArrayList<SLanguage> engagedLanguages = new ArrayList<>();
      engagedLanguages.addAll(myModelProperties.getLanguagesEngagedOnGeneration());
      myEngagedLanguagesModel.init(engagedLanguages, Collections.emptyList());
      languagesTable.setModel(myEngagedLanguagesModel);

      LanguageTableCellRenderer cellRenderer = new LanguageTableCellRenderer(myProject.getRepository());
      Set<SLanguage> languagesInUse = new ModelAccessHelper(myProject.getModelAccess()).runReadAction(new ComputeUsedLanguages(myModelDescriptor));
      IsLanguageInUse inUseCondition = new IsLanguageInUse(languagesInUse, Collections.emptySet());
      cellRenderer.addCellState(inUseCondition, DependencyCellState.SUPERFLUOUS_ENGAGED);
      cellRenderer.registerIn(languagesTable);

      ToolbarDecorator decorator = ToolbarDecorator.createDecorator(languagesTable);
      decorator.setAddAction(anActionButton -> {
        // XXX use of LanguageRegistry here effectively prevents us from specifying languages that are not yet deployed, is it what we want?
        Collection<SLanguage> languages = myProject.getComponent(LanguageRegistry.class).getAllLanguages();
        List<SLanguage> list = CommonChoosers.showLanguageSetChooser(myProject, PropertiesBundle.message("model.into.engaged.add.title"), languages);
        for (SLanguage l : list) {
          myEngagedLanguagesModel.addItem(l);
        }
        myEngagedLanguagesModel.fireTableDataChanged();
      }).setRemoveAction(new RemoveEntryAction(languagesTable));
      decorator.setPreferredSize(new Dimension(500, 150));

      JPanel table = decorator.createPanel();
      table.setBorder(IdeBorderFactory.createBorder());
      panel.add(table, new GridConstraints(rowIndex, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));

      new TableColumnSearch(languagesTable, 0).setComparator(new SpeedSearchComparator(false, true));

      setTabComponent(panel);
    }

    @Override
    public boolean isModified() {
      return myDoNotGenerateCheckBox.isSelected() != myModelProperties.isDoNotGenerate()
          || (myIsDefSModelDescr && (myGenerateIntoModelFolderCheckBox.isSelected() != myModelProperties.isGenerateIntoModelFolder()))
          || myEngagedLanguagesModel.isModified();
    }

    @Override
    public void apply() {
      myModelProperties.setDoNotGenerate(myDoNotGenerateCheckBox.isSelected());
      if (myIsDefSModelDescr) {
        myModelProperties.setGenerateIntoModelFolder(myGenerateIntoModelFolderCheckBox.isSelected());
      }
      ArrayList<SLanguage> engagedLanguages = new ArrayList<>();
      myEngagedLanguagesModel.fillResult(engagedLanguages, new ArrayList<>()/*ignored, shall be empty*/);
      myModelProperties.getLanguagesEngagedOnGeneration().clear();
      myModelProperties.getLanguagesEngagedOnGeneration().addAll(engagedLanguages);
    }
  }

  /**
   * Answers whether given module is among specified
   */
  private static class IsLanguageInUse implements Condition<UsedLangsTableModel.Import> {
    private final Collection<SLanguage> myActualUse;
    private final Collection<SLanguage> myExplicitUse;

    /**
     * @param actualInUse set of modules to check against
     * @param explicitInUse set of modules to treat as known and that should not be considered when (and if) we build derived
     *                      dependencies of a module in question.
     */
    public IsLanguageInUse(@NotNull Collection<SLanguage> actualInUse, @NotNull Collection<SLanguage> explicitInUse) {
      myActualUse = actualInUse;
      myExplicitUse = explicitInUse;
    }
    @Override
    public boolean met(UsedLangsTableModel.Import entry) {
      if (entry == null) {
        return false;
      }
      if (entry.myLanguage != null) {
        return myActualUse.contains(entry.myLanguage);
      }
      if (entry.myDevKit != null) {
        // FIXME we shall do with DevKit as SModule smth anyway (i.e. to match SLanguage), that's why MPSModuleRepository here
        final SModule module = entry.myDevKit.resolve(MPSModuleRepository.getInstance());
        if (!(module instanceof DevKit)) {
          return false;
        }
        HashSet<SLanguage> burstDeps = new HashSet<>();
        final DevKit devKit = (DevKit) module;
        burstDeps.addAll(IterableUtil.asCollection(devKit.getAllExportedLanguageIds()));
        // if module is already there (e.g. explicitly imported), do not consider devkit with it as used/necessary
        burstDeps.removeAll(myExplicitUse);
        return CollectionUtil.intersects(burstDeps, myActualUse);
      }
      return false;
    }
  }

  private static class ComputeUsedLanguages implements Computable<Set<SLanguage>> {
    private final SModel myModel;

    public ComputeUsedLanguages(@NotNull SModel model) {
      myModel = model;
    }
    @Override
    public Set<SLanguage> compute() {
      final ModelDependencyScanner ms = new ModelDependencyScanner().usedLanguages(true).crossModelReferences(false);
      ms.walk(myModel);
      return ms.getUsedLanguages();
    }
  }
}
