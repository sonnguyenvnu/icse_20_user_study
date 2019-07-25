/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.ide.codeStyle;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.JBUI.Borders;
import jetbrains.mps.baseLanguage.util.CodeStyleSettings;
import org.jetbrains.annotations.Nullable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Objects;

public class CodeStylePreferencesPage {
  private enum LineSeparatorOption {
    SYSTEM_DEPENDENT(null, "System Dependent"),
    UNIX("\n", "Unix"),
    WINDOWS("\r\n", "Windows"),
    MACINTOSH("\r", "Mac");
    private final String mySetting;
    private final String myName;

    LineSeparatorOption(String setting, String name) {
      mySetting = setting;
      myName = name;
    }

    public String toString() {
      return myName;
    }

    public String getSetting() {
      return mySetting;
    }
  }

  private final JPanel myPage;
  private final CodeStyleItem myFieldItem;
  private final CodeStyleItem myStaticField;
  private final CodeStyleItem myParameter;
  private final CodeStyleItem myLocalVariable;
  private final JComboBox<LineSeparatorOption> myLineSeparatorComboBox;
  private final CodeStyleSettings mySettings;

  CodeStylePreferencesPage(CodeStyleSettings settings) {
    mySettings = settings;

    myPage = new JPanel();
    myPage.setLayout(new BoxLayout(myPage, BoxLayout.Y_AXIS));

    JPanel namingPanel = new JPanel(new GridBagLayout());
    namingPanel.setBorder(BorderFactory.createTitledBorder("Naming"));
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.NORTHWEST;
    c.insets = getInsets();
    c.gridwidth = 1;
    c.gridy = 0;
    c.gridx = 1;
    namingPanel.add(new JBLabel("Name prefix:"), c);
    c.gridx = 2;
    namingPanel.add(new JBLabel("Name suffix:"), c);
    myFieldItem = new CodeStyleItem(namingPanel, "Field", 1);
    myStaticField = new CodeStyleItem(namingPanel, "Static field", 2);
    myParameter = new CodeStyleItem(namingPanel, "Parameter", 3);
    myLocalVariable = new CodeStyleItem(namingPanel, "Local variable", 4);

    namingPanel.setMaximumSize(namingPanel.getPreferredSize());
    namingPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

    JPanel lineSeparatorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    lineSeparatorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    lineSeparatorPanel.add(new JBLabel("Line separator: "));
    myLineSeparatorComboBox = new ComboBox<>(LineSeparatorOption.values());
    lineSeparatorPanel.add(myLineSeparatorComboBox);

    myPage.setBorder(Borders.empty(10));
    myPage.add(namingPanel);
    myPage.add(lineSeparatorPanel);
    myPage.add(Box.createVerticalGlue());

    update();
  }

  @Nullable
  private String getSelectedLineSeparator() {
    Object selectedItem = myLineSeparatorComboBox.getSelectedItem();
    for (LineSeparatorOption option : LineSeparatorOption.values()) {
      if (option == selectedItem) {
        return option.getSetting();
      }
    }
    return null;
  }

  private Insets getInsets() {
    return JBUI.insets(4);
  }

  public JComponent getComponent() {
    return myPage;
  }

  public void commit() {
    mySettings.setFieldPrefix(myFieldItem.getPrefix());
    mySettings.setFieldSuffix(myFieldItem.getSuffix());
    mySettings.setStaticFieldPrefix(myStaticField.getPrefix());
    mySettings.setStaticFieldSuffix(myStaticField.getSuffix());
    mySettings.setParameterPrefix(myParameter.getPrefix());
    mySettings.setParameterSuffix(myParameter.getSuffix());
    mySettings.setLocalVariablePrefix(myLocalVariable.getPrefix());
    mySettings.setLocalVariableSuffix(myLocalVariable.getSuffix());
    mySettings.setLineSeparator(getSelectedLineSeparator());
  }

  public boolean isModified() {
    return !(equals(mySettings.getFieldPrefix(), mySettings.getFieldSuffix(), myFieldItem) &&
             equals(mySettings.getStaticFieldPrefix(), mySettings.getStaticFieldSuffix(), myStaticField) &&
             equals(mySettings.getParameterPrefix(), mySettings.getParameterSuffix(), myParameter) &&
             equals(mySettings.getLocalVariablePrefix(), mySettings.getLocalVariableSuffix(), myLocalVariable) &&
             Objects.equals(mySettings.getLineSeparator(), getSelectedLineSeparator()));
  }

  private boolean equals(String prefix, String suffix, CodeStyleItem item) {
    return Objects.equals(prefix, item.getPrefix()) && Objects.equals(suffix, item.getSuffix());
  }

  public void update() {
    myFieldItem.setSettings(mySettings.getFieldPrefix(), mySettings.getFieldSuffix());
    myStaticField.setSettings(mySettings.getStaticFieldPrefix(), mySettings.getStaticFieldSuffix());
    myParameter.setSettings(mySettings.getParameterPrefix(), mySettings.getParameterSuffix());
    myLocalVariable.setSettings(mySettings.getLocalVariablePrefix(), mySettings.getLocalVariableSuffix());
    for (LineSeparatorOption option : LineSeparatorOption.values()) {
      if (Objects.equals(option.getSetting(), mySettings.getLineSeparator())) {
        myLineSeparatorComboBox.setSelectedItem(option);
      }
    }
  }

  private class CodeStyleItem {
    private final JLabel myName = new JLabel();
    private final JTextField myPrefix = new JBTextField(15);
    private final JTextField mySuffix = new JBTextField(15);

    CodeStyleItem(JComponent owner, String name, int index) {
      myName.setText(name + ":");
      myName.setHorizontalAlignment(SwingConstants.RIGHT);
      addComponent(owner, myName, 0, index);
      addComponent(owner, myPrefix, 1, index);
      addComponent(owner, mySuffix, 2, index);
    }

    String getPrefix() {
      return myPrefix.getText();
    }

    String getSuffix() {
      return mySuffix.getText();
    }

    void setSettings(String prefix, String suffix) {
      myPrefix.setText(prefix);
      mySuffix.setText(suffix);
    }

    private void addComponent(JComponent owner, JComponent child, int x, int y) {
      GridBagConstraints c = new GridBagConstraints();
      c.insets = getInsets();
      c.fill = GridBagConstraints.BOTH;
      c.gridx = x;
      c.gridy = y;
      owner.add(child, c);
    }
  }
}
