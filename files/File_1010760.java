/*
 * Copyright 2003-2013 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.FontComboBox;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.ui.components.panels.HorizontalLayout;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.UIUtil;
import jetbrains.mps.nodeEditor.cells.EditorCell_Basic;
import jetbrains.mps.nodeEditor.resources.EditorSettingsBundle;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

class EditorSettingsPreferencesPage implements Disposable {
  private static final double LINE_SPACING_MIN = 0.6;
  private static final double LINE_SPACING_MAX = 3.0;
  private static final double LINE_SPACING_STEP = 0.1;

  private static final int RIGHT_MARGIN_MIN = 1;
  private static final int RIGHT_MARGIN_MAX = 1000;
  private static final int RIGHT_MARGIN_STEP = 20;

  private static final int INDENT_SIZE_MIN = 0;
  private static final int INDENT_SIZE_MAX = 100;
  private static final int INDENT_SIZE_STEP = 2;

  private JPanel myEditorSettingsPanel;
  private final FontComboBox myFontsComboBox;
  private final JSpinner myLineSpacing;
  private final ComboBox myFontSizesComboBox;
  private final JSpinner myRightMargin;
  private final JSpinner myIndentSize;
  private final JCheckBox myAntialiasingCheckBox;
  private final JCheckBox myPowerSaveModeCheckBox;
  private final JCheckBox myAutoQuickFixCheckBox;
  private final JCheckBox myCompletionStylingCheckBox;
  private final JCheckBox myUseBraces;
  private final JCheckBox myUseTwoStepDeletion;
  private final JCheckBox myShowContextAssistant;
  private final JCheckBox myReflectiveEditorReadonly;
  private final JSpinner myCaretBlinkPeriod;
  private final JBRadioButton myDontShow;
  private final JBRadioButton myTabPerAspect;
  private final JBRadioButton myTabPerNode;
  private final JBRadioButton myAllTabs;
  private JBRadioButton myFirstSelection;

  private final EditorSettings mySettings;

  public EditorSettingsPreferencesPage(EditorSettings settings) {
    mySettings = settings;
    final int gap = 5;
    final JBInsets insets = new JBInsets(gap, gap, gap, gap);
    JPanel panel = new JPanel(new GridLayoutManager(4, 1, insets, gap, gap));

    ButtonGroup group = new ButtonGroup();

    JPanel editorTabsRB = new JPanel(new GridLayout(4, 1));
    editorTabsRB.setBorder(IdeBorderFactory.createTitledBorder(EditorSettingsBundle.message("border.title.aspect.tabs"), true));

    myDontShow = new JBRadioButton(EditorSettingsBundle.message("radiobutton.aspect.tabs.do.not.show"));
    editorTabsRB.add(myDontShow);
    group.add(myDontShow);

    myTabPerAspect = new JBRadioButton(EditorSettingsBundle.message("radiobutton.aspect.tabs.for.aspect"));
    editorTabsRB.add(myTabPerAspect);
    group.add(myTabPerAspect);

    myTabPerNode = new JBRadioButton(EditorSettingsBundle.message("radiobutton.aspect.tabs.for.node"));
    editorTabsRB.add(myTabPerNode);
    group.add(myTabPerNode);

    myAllTabs = new JBRadioButton(EditorSettingsBundle.message("radiobutton.aspect.tabs.for.non.existing"));
    editorTabsRB.add(myAllTabs);
    group.add(myAllTabs);

    myFirstSelection = myTabPerAspect;
    myFirstSelection.setSelected(true);

    panel.add(editorTabsRB,
              new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW,
                                  GridConstraints.SIZEPOLICY_FIXED, null, null, null));

    JPanel fontPropertiesPanel = new JPanel(new GridLayoutManager(5, 2, insets, gap, gap));

    fontPropertiesPanel.add(new JBLabel(EditorSettingsBundle.message("label.font.name")), getLabelConstraint(0));
    myFontsComboBox = new FontComboBox();
    fontPropertiesPanel.add(myFontsComboBox, getEditorConstraint(0));

    fontPropertiesPanel.add(new JLabel(EditorSettingsBundle.message("label.font.size")), getLabelConstraint(1));
    //noinspection unchecked
    myFontSizesComboBox = new ComboBox(new DefaultComboBoxModel(UIUtil.getStandardFontSizes()));
    myFontSizesComboBox.setEditable(true);
    fontPropertiesPanel.add(myFontSizesComboBox, getEditorConstraint(1));

    fontPropertiesPanel.add(new JLabel(EditorSettingsBundle.message("label.line.spacing")), getLabelConstraint(2));
    myLineSpacing = new JSpinner(new SpinnerNumberModel(LINE_SPACING_MIN, LINE_SPACING_MIN, LINE_SPACING_MAX, LINE_SPACING_STEP));
    ((JSpinner.DefaultEditor) myLineSpacing.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
    fontPropertiesPanel.add(myLineSpacing, getEditorConstraint(2));

    fontPropertiesPanel.add(new JLabel(EditorSettingsBundle.message("label.text.width")), getLabelConstraint(3));
    myRightMargin = new JSpinner(new SpinnerNumberModel(RIGHT_MARGIN_MIN, RIGHT_MARGIN_MIN, RIGHT_MARGIN_MAX, RIGHT_MARGIN_STEP));
    ((JSpinner.DefaultEditor) myRightMargin.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
    fontPropertiesPanel.add(myRightMargin, getEditorConstraint(3));

    fontPropertiesPanel.add(new JLabel(EditorSettingsBundle.message("label.indent.size")), getLabelConstraint(4));
    myIndentSize = new JSpinner(new SpinnerNumberModel(INDENT_SIZE_MIN, INDENT_SIZE_MIN, INDENT_SIZE_MAX, INDENT_SIZE_STEP));
    ((JSpinner.DefaultEditor) myIndentSize.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
    fontPropertiesPanel.add(myIndentSize, getEditorConstraint(4));

    panel.add(fontPropertiesPanel,
              new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW,
                                  GridConstraints.SIZEPOLICY_FIXED, null, null, null));

    JPanel checkboxes = new JPanel(new GridLayout(3, 1));
    myUseBraces = new JCheckBox(EditorSettingsBundle.message("checkbox.use.braces"));
    checkboxes.add(myUseBraces);

    myAntialiasingCheckBox = new JCheckBox(EditorSettingsBundle.message("checkbox.use.antialiasing"));
    checkboxes.add(myAntialiasingCheckBox);

    myPowerSaveModeCheckBox = new JCheckBox(EditorSettingsBundle.message("checkbox.power.save.mode"));
    checkboxes.add(myPowerSaveModeCheckBox);

    myAutoQuickFixCheckBox = new JCheckBox(EditorSettingsBundle.message("checkbox.auto.resolve.refs"));
    checkboxes.add(myAutoQuickFixCheckBox);

    myCompletionStylingCheckBox = new JCheckBox(EditorSettingsBundle.message("checkbox.completion.styling"));
    checkboxes.add(myCompletionStylingCheckBox);

    myShowContextAssistant = new JCheckBox(EditorSettingsBundle.message("checkbox.context.assistant"));
    checkboxes.add(myShowContextAssistant);

    myUseTwoStepDeletion = new JCheckBox(EditorSettingsBundle.message("checkbox.use.two.step.deletion"));
    checkboxes.add(myUseTwoStepDeletion);

    myReflectiveEditorReadonly = new JCheckBox(EditorSettingsBundle.message("checkbox.reflective.readonly"));
    checkboxes.add(myReflectiveEditorReadonly);

    panel.add(checkboxes,
              new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW,
                                  GridConstraints.SIZEPOLICY_FIXED, null, null, null));

    JPanel caretBlinkingPanel = new JPanel(new HorizontalLayout(gap));
    caretBlinkingPanel.add(new JLabel(EditorSettingsBundle.message("label.caret.blinking")));
    myCaretBlinkPeriod =
        new JSpinner(
                        new SpinnerNumberModel(mySettings.getCaretBlinkPeriod(), EditorSettings.MIN_CARET_BLINK_PERIOD, EditorSettings.MAX_CARET_BLINK_PERIOD,
                                               100));
    caretBlinkingPanel.add(myCaretBlinkPeriod);

    panel.add(caretBlinkingPanel,
              new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW,
                                  GridConstraints.SIZEPOLICY_FIXED, null, null, null));

    myEditorSettingsPanel = new JPanel(new BorderLayout());
    myEditorSettingsPanel.add(panel, BorderLayout.NORTH);
  }

  private GridConstraints getLabelConstraint(int row) {
    return new GridConstraints(row, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED,
                               GridConstraints.SIZEPOLICY_FIXED, null, null, null);
  }

  private GridConstraints getEditorConstraint(int row) {
    return new GridConstraints(row, 1, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL,
                               GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_CAN_SHRINK, GridConstraints.SIZEPOLICY_FIXED, null, null, null);
  }

  public JComponent getComponent() {
    return myEditorSettingsPanel;
  }

  public void commit() {
    String fontName = myFontsComboBox.getFontName();
    int fontSize = mySettings.getSpecifiedFontSize();
    try {
      fontSize = Integer.parseInt(myFontSizesComboBox.getSelectedItem().toString());
    } catch (NumberFormatException e) {
      // ignore wrong formatted value and reset combo box
      myFontSizesComboBox.setSelectedItem(Integer.toString(fontSize));
    }

    Font newFont = new Font(fontName, Font.PLAIN, fontSize);
    mySettings.setDefaultEditorFont(newFont);

    mySettings.setVerticalBound((Integer) myRightMargin.getModel().getValue());

    mySettings.setIndentSize((Integer) myIndentSize.getModel().getValue());

    mySettings.setCaretBlinkPeriod((Integer) myCaretBlinkPeriod.getModel().getValue());

    mySettings.setUseAntialiasing(myAntialiasingCheckBox.isSelected());
    mySettings.setUseBraces(myUseBraces.isSelected());
    mySettings.setShowContextAssistant(myShowContextAssistant.isSelected());
    mySettings.setUseTwoStepDeletion(myUseTwoStepDeletion.isSelected());
    mySettings.setReflectiveEditorReadonly(myReflectiveEditorReadonly.isSelected());

    mySettings.setPowerSaveMode(myPowerSaveModeCheckBox.isSelected());
    mySettings.setAutoQuickFix(myAutoQuickFixCheckBox.isSelected());

    mySettings.setCompletionStyling(myCompletionStylingCheckBox.isSelected());

    mySettings.setLineSpacing((Double) myLineSpacing.getModel().getValue());

    mySettings.setShow(myTabPerAspect.isSelected() || myTabPerNode.isSelected() || myAllTabs.isSelected());
    mySettings.setShowPlain(myTabPerNode.isSelected() || myAllTabs.isSelected());
    mySettings.setShowGrayed(myAllTabs.isSelected());
    applyState();

    mySettings.updateCachedValue();
    mySettings.updateGlobalScheme();
    mySettings.fireEditorSettingsChanged();
  }

  private void applyState() {
    if (!mySettings.isShow()) {
      myFirstSelection = myDontShow;
    } else if (!mySettings.isShowPlain()) {
      myFirstSelection = myTabPerAspect;
    } else if (!mySettings.isShowGrayed()) {
      myFirstSelection = myTabPerNode;
    } else {
      myFirstSelection = myAllTabs;
    }
  }

  public boolean isModified() {
    boolean sameTextWidth = myRightMargin.getModel().getValue().equals(mySettings.getVerticalBound());
    boolean sameIndentSize = myIndentSize.getModel().getValue().equals(mySettings.getIndentSize());
    boolean sameAntialiasing = myAntialiasingCheckBox.isSelected() == mySettings.isUseAntialiasing();
    boolean sameUseBraces = myUseBraces.isSelected() == mySettings.useBraces();
    boolean sameTwoStepBackspace = myUseTwoStepDeletion.isSelected() == mySettings.isUseTwoStepDeletion();
    boolean sameReflectiveEditorReadonly = myReflectiveEditorReadonly.isSelected() == mySettings.isReflectiveEditorReadonly();
    boolean samePowerSaveMode = myPowerSaveModeCheckBox.isSelected() == mySettings.isPowerSaveMode();
    boolean sameAutoQuickFix = myAutoQuickFixCheckBox.isSelected() == mySettings.isAutoQuickFix();
    boolean sameCompletionStyling = myCompletionStylingCheckBox.isSelected() == mySettings.isCompletionStyling();
    boolean sameFontSize = myFontSizesComboBox.getSelectedItem().equals(Integer.toString(mySettings.getSpecifiedFontSize()));
    boolean sameFontFamily = myFontsComboBox.getFontName().equals(mySettings.getFontFamily());
    boolean sameLineSpacing = myLineSpacing.getModel().getValue().equals(mySettings.getLineSpacing());
    boolean sameBlinkingRate = myCaretBlinkPeriod.getModel().getValue().equals(mySettings.getCaretBlinkPeriod());
    boolean sameTabs = myFirstSelection.isSelected();
    boolean sameUseContextAssistant = myShowContextAssistant.isSelected() == mySettings.isShowContextAssistant();

    return !(sameTextWidth && sameIndentSize && sameAntialiasing && sameUseBraces && samePowerSaveMode && sameTwoStepBackspace && sameReflectiveEditorReadonly
             && sameAutoQuickFix && sameCompletionStyling && sameFontSize && sameFontFamily && sameLineSpacing && sameBlinkingRate && sameTabs && sameUseContextAssistant);
  }

  public void reset() {
    myRightMargin.setValue(mySettings.getVerticalBound());

    myIndentSize.setValue(mySettings.getIndentSize());

    myAntialiasingCheckBox.setSelected(mySettings.isUseAntialiasing());

    myUseBraces.setSelected(mySettings.useBraces());

    myUseTwoStepDeletion.setSelected(mySettings.isUseTwoStepDeletion());

    myPowerSaveModeCheckBox.setSelected(mySettings.isPowerSaveMode());

    myAutoQuickFixCheckBox.setSelected(mySettings.isAutoQuickFix());

    myCompletionStylingCheckBox.setSelected(mySettings.isCompletionStyling());

    myShowContextAssistant.setSelected(mySettings.isShowContextAssistant());

    myReflectiveEditorReadonly.setSelected(mySettings.isReflectiveEditorReadonly());

    myFontSizesComboBox.setSelectedItem(Integer.toString(mySettings.getSpecifiedFontSize()));

    myFontsComboBox.setFontName(mySettings.getFontFamily());

    myLineSpacing.setValue(mySettings.getLineSpacing());

    myCaretBlinkPeriod.setValue(mySettings.getCaretBlinkPeriod());

    applyState();
    myFirstSelection.setSelected(true);
  }

  @Override
  public void dispose() {
    myEditorSettingsPanel = null;
  }
}
