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
package jetbrains.mps.ide.project.facets.ui;

import com.intellij.icons.AllIcons.Nodes;
import com.intellij.ui.components.JBLabel;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import jetbrains.mps.ide.project.facets.IdeaPluginModuleFacetImpl;
import jetbrains.mps.ide.ui.dialogs.properties.tabs.BaseTab;
import org.jetbrains.mps.openapi.module.SModuleFacet;
import org.jetbrains.mps.openapi.ui.persistence.FacetTab;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;

public class IdeaPluginModuleFacetTab extends BaseTab implements FacetTab {

  private final IdeaPluginModuleFacetImpl myIdeaPluginModuleFacet;

  private JTextField myTextField;

  public IdeaPluginModuleFacetTab(IdeaPluginModuleFacetImpl moduleFacet) {
    super(moduleFacet.getFacetPresentation(), Nodes.Plugin, "Idea Plugin Properties");

    myIdeaPluginModuleFacet = moduleFacet;
  }

  @Override
  public void init() {
    JPanel content = new JPanel();
    content.setLayout(new GridLayoutManager(1, 2, JBUI.emptyInsets(), -1, -1));

    JBLabel label = new JBLabel("Plugin ID:");
    content.add(label, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

    myTextField = new JTextField(myIdeaPluginModuleFacet.getPluginId());

    content.add(myTextField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW,
        GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

    JPanel outerPanel = new JPanel();
    outerPanel.setLayout(new GridLayoutManager(1, 1, new JBInsets(10, 10, 10, 10), -1, -1));
    outerPanel.add(content,
        new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));

    setTabComponent(outerPanel);
  }

  @Override
  public boolean isModified() {
    return !myTextField.getText().equals(myIdeaPluginModuleFacet.getPluginId());
  }

  @Override
  public void apply() {
    myIdeaPluginModuleFacet.setPluginId(myTextField.getText());
  }

  @Override
  public SModuleFacet getFacet() {
    return myIdeaPluginModuleFacet;
  }
}
