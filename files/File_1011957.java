/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.ide.compiler;

import jetbrains.mps.compiler.JavaCompilerOptionsComponent.JavaVersion;
import jetbrains.mps.ide.compiler.CompilerSettingsComponent.CompilerState;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

public class CompilerSettingsPreferencePage {

  private final List<JavaVersionPresentationItem> myTargetVersionItems = new ArrayList<>();
  private static final JavaVersionPresentationItem DEFAULT_VERSION_ITEM = new JavaVersionPresentationItem(null, "JDK Default");
  private JPanel myMainPanel;
  private JComboBox myTargetJavaVersionComboBox;
  private JavaVersionPresentationItem myInitialTargetJavaVersion;

  {
    for (JavaVersion version : JavaVersion.values()) {
      myTargetVersionItems.add(new JavaVersionPresentationItem(version, version.getCompilerVersion()));
    }
    myTargetVersionItems.add(DEFAULT_VERSION_ITEM);
  }
  public CompilerSettingsPreferencePage(CompilerState options) {
    JavaVersion targetJavaVersion = JavaVersion.parse(options.getTargetVersion());
    if (targetJavaVersion == null) {
      myInitialTargetJavaVersion = DEFAULT_VERSION_ITEM;
    } else {
      myInitialTargetJavaVersion = new JavaVersionPresentationItem(targetJavaVersion, targetJavaVersion.getCompilerVersion());
    }
  }

  public JComponent getMainPanel() {
    if (myMainPanel == null) {
      createMainPanel();
    }
    return myMainPanel;
  }

  private void createMainPanel() {
    myMainPanel = new JPanel();
    myMainPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.weighty = 1;
    c.weightx = 1;
    c.anchor = GridBagConstraints.NORTHWEST;
    JPanel panel = createCompilerOptionsPanel();
    myMainPanel.add(panel, c);
  }

  private JPanel createCompilerOptionsPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.NORTHWEST;
    c.weighty = 1;
    c.weightx = 1;
    c.insets.top = 4;
    c.insets.left = 5;
    myTargetJavaVersionComboBox = new JComboBox(new DefaultComboBoxModel(myTargetVersionItems.toArray()));
    myTargetJavaVersionComboBox.setSelectedItem(myInitialTargetJavaVersion);

    panel.add(new JLabel("Project bytecode version"), c);
    c.gridx = 1;
    c.insets.top = 0;
    panel.add(myTargetJavaVersionComboBox, c);

    return panel;
  }


  JavaVersion getSelectedTargetJavaVersion() {
    JavaVersionPresentationItem selectedItem = (JavaVersionPresentationItem) myTargetJavaVersionComboBox.getSelectedItem();
    return selectedItem.getJavaVersion();
  }

  boolean isModified() {
    return !(myInitialTargetJavaVersion.equals(myTargetJavaVersionComboBox.getSelectedItem()));
  }

  void commit() {
    myInitialTargetJavaVersion = ((JavaVersionPresentationItem) myTargetJavaVersionComboBox.getSelectedItem());
  }

  void reset() {
    myTargetJavaVersionComboBox.setSelectedItem(myInitialTargetJavaVersion);
  }

  private static class JavaVersionPresentationItem {
    private JavaVersion myJavaVersion;
    private final String myDescription;

    JavaVersionPresentationItem(JavaVersion javaVersion, String description) {
      myJavaVersion = javaVersion;
      myDescription = description;
    }

    public JavaVersion getJavaVersion() {
      return myJavaVersion;
    }

    public String getDescription() {
      return myDescription;
    }

    @Override
    public String toString() {
      return myDescription;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof JavaVersionPresentationItem)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      if (myJavaVersion == null) {
        return ((JavaVersionPresentationItem) obj).myJavaVersion == null;
      }
      return myJavaVersion.equals(((JavaVersionPresentationItem) obj).myJavaVersion);
    }

    @Override
    public int hashCode() {
      return 17 + 37 * (myJavaVersion == null ? 0 : myJavaVersion.hashCode());
    }
  }
}
