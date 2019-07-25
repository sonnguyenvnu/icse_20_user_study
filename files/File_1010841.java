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

package jetbrains.mps.idea.core.facet.ui;

import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModifiableModuleModel;
import com.intellij.openapi.roots.ui.configuration.ProjectStructureConfigurable;
import com.intellij.ui.TabbedPaneWrapper;
import jetbrains.mps.idea.core.MPSBundle;
import jetbrains.mps.idea.core.facet.MPSConfigurationBean;
import jetbrains.mps.idea.core.icons.MPSIcons;
import jetbrains.mps.idea.core.ui.SModuleConfigurationTab;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

/**
 * evgeny, 10/26/11
 */
public class MPSFacetCommonTabUI implements SModuleConfigurationTab {

  private JPanel rootPanel;
  private JComponent myCentralComponent;
  private JTextField mySolutionNamespace;

  private final Disposable myParentDisposable;
  private final FacetEditorContext myContext;
  private List<SModuleConfigurationTab> myTabs;

  public MPSFacetCommonTabUI(FacetEditorContext context, Disposable parentDisposable) {
    myParentDisposable = parentDisposable;
    myContext = context;
  }

  @Override
  public void reset(MPSConfigurationBean data) {
    refreshSolutionDescriptorName();
    for (SModuleConfigurationTab tab : myTabs) {
      tab.reset(data);
    }
  }

  private void refreshSolutionDescriptorName() {
    ModifiableModuleModel moduleModel = ProjectStructureConfigurable.getInstance(myContext.getProject()).getContext().getModulesConfigurator().getModuleModel();
    String moduleName = moduleModel.getNewName(myContext.getModule());
    if (moduleName == null) {
      moduleName = myContext.getModule().getName();
    }
    mySolutionNamespace.setText(moduleName);
  }

  @Override
  public void apply(MPSConfigurationBean data) {
    for (SModuleConfigurationTab tab : myTabs) {
      tab.apply(data);
    }
  }

  @Override
  public boolean isModified(MPSConfigurationBean data) {
    for (SModuleConfigurationTab tab : myTabs) {
      if (tab.isModified(data)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public JPanel getRootPanel() {
    return rootPanel;
  }

  private void createUIComponents() {
    createCentralComponent();
  }

  private void createCentralComponent() {
    TabbedPaneWrapper tabbedPane = new TabbedPaneWrapper(myParentDisposable);
    MPSFacetSourcesTab mpsFacetSourcesTab = new MPSFacetSourcesTab(myContext, myParentDisposable);
    MPSFacetPathsTab mpsFacetPathsTab = new MPSFacetPathsTab(myContext);

    // can not make it final and init in declaration since idea forms generator does not like it and put $$$setupUI$$$ call before setting the field
    myTabs = new ArrayList<>();
    myTabs.add(mpsFacetSourcesTab);
    myTabs.add(mpsFacetPathsTab);

    tabbedPane.addTab(MPSBundle.message("facet.sources.tab.name"), MPSIcons.SOURCES_TAB_ICON, mpsFacetSourcesTab.getRootPanel(), null);
    tabbedPane.addTab(MPSBundle.message("facet.paths.tab.name"), MPSIcons.PATHS_TAB_ICON, mpsFacetPathsTab.getRootPanel(), null);

    myCentralComponent = tabbedPane.getComponent();
  }

  @Override
  public void onTabEntering() {
    refreshSolutionDescriptorName();
    myTabs.forEach(SModuleConfigurationTab::onTabEntering);
  }
}
