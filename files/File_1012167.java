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
package jetbrains.mps.smodel;


import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class ModelValidationConfigurable implements SearchableConfigurable {
  @NotNull
  private ModelValidationSettings myModelValidationSettings;

  private JPanel myJPanel = new JPanel(new BorderLayout());
  private JCheckBox myCheckBoxOpenAPI = new JCheckBox("Enable nonpublic API usage check");
  private JCheckBox myCheckBoxTypeWasNotCalculated = new JCheckBox("Enable 'type was not calculated' check");

  public ModelValidationConfigurable(@NotNull ModelValidationSettings modelValidationSettings) {
    myModelValidationSettings = modelValidationSettings;
    Box box = Box.createVerticalBox();
    box.add(myCheckBoxOpenAPI);
    box.add(myCheckBoxTypeWasNotCalculated);
    myJPanel.add(box, BorderLayout.WEST);
  }

  @NotNull
  @Override
  public String getId() {
    return "mps.modelValidation.settings";
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "Model Validation";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return getId();
  }

  @Override
  public void apply() {
    myModelValidationSettings.setDisableCheckOpenAPI(!myCheckBoxOpenAPI.isSelected());
    myModelValidationSettings.setDisableTypeWasNotCalculated(!myCheckBoxTypeWasNotCalculated.isSelected());
  }

  @Override
  public void reset() {
    myCheckBoxOpenAPI.setSelected(!myModelValidationSettings.isDisableCheckOpenAPI());
    myCheckBoxTypeWasNotCalculated.setSelected(!myModelValidationSettings.isDisableTypeWasNotCalculated());
  }

  public boolean isModified() {
    // Shown value is inverted, so check for equality to avoid double negation
    return myModelValidationSettings.isDisableCheckOpenAPI() == myCheckBoxOpenAPI.isSelected() ||
           myModelValidationSettings.isDisableTypeWasNotCalculated() == myCheckBoxTypeWasNotCalculated.isSelected();
  }

  @Override
  public void disposeUIResources() {
    myCheckBoxOpenAPI = null;
    myCheckBoxTypeWasNotCalculated = null;
    myJPanel = null;
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return myJPanel;
  }


  @Nullable
  @Override
  public Runnable enableSearch(String option) {
    return null;
  }
}
