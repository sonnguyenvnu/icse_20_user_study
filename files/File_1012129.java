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
package jetbrains.mps.ide.generator;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.IdeBorderFactory;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBEmptyBorder;
import com.intellij.util.ui.JBUI;
import jetbrains.mps.generator.GenerationOptions;
import jetbrains.mps.generator.GenerationSettingsProvider;
import jetbrains.mps.generator.IModifiableGenerationSettings;
import jetbrains.mps.icons.MPSIcons.Nodes;
import jetbrains.mps.ide.MPSCoreComponents;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class GenerationSettingsPreferencesPage implements SearchableConfigurable {
  private final JPanel myPage;
  private final JCheckBox mySaveTransientModelsCheckBox = new JCheckBox("Save transient models on generation");
  private final JCheckBox myCheckModelsBeforeGenerationCheckBox = new JCheckBox("Check models for errors before generation");
  private final JCheckBox myStrictMode = new JCheckBox("Strict mode");
  private final JCheckBox myUseNewGenerator = new JCheckBox("Generate in parallel.");
  private final JFormattedTextField myNumberOfParallelThreads = new JFormattedTextField(new RangeDecimalFormatter(2, 32));
  private final JCheckBox myInplaceTransform = new JCheckBox("Apply transformations in place");
  private final JCheckBox myAvoidDynamicRefs = new JCheckBox("Resort to static references");

  private JRadioButton myTraceNone = new JRadioButton("None");
  private JRadioButton myTraceSteps = new JRadioButton("Generation steps only");
  private JRadioButton myTraceLanguages = new JRadioButton("Time spent in language generators");
  private JRadioButton myTraceTypes = new JRadioButton("Time spent in types calculation");

  private JCheckBox myShowInfo = new JCheckBox("Show informational messages");
  private JCheckBox myShowWarnings = new JCheckBox("Show warnings");
  private JCheckBox myKeepModelsWithWarnings = new JCheckBox("Keep transient models with warnings");
  private JCheckBox myShowBadChildWarnings = new JCheckBox("Warn when child cannot be placed into role");
  private JCheckBox myLimitNumberOfModels = new JCheckBox("Maximum number of transient models to keep:");
  private JFormattedTextField myNumberOfModelsToKeep = new JFormattedTextField(new RangeDecimalFormatter(0, 1000));

  private JCheckBox myGenerateDebugInfo = new JCheckBox("Generate debug information");

  private JLabel myStatusLabel;
  private final ItemListener myStatusUpdater = e -> updateStatus();

  private final IModifiableGenerationSettings myGenerationSettings;
  private final ButtonSelectStateTracker myButtonState = new ButtonSelectStateTracker();

  public GenerationSettingsPreferencesPage(MPSCoreComponents coreComponents) {
    myGenerationSettings = coreComponents.getPlatform().findComponent(GenerationSettingsProvider.class).getGenerationSettings();
    reset();
    myPage = createPage();
    myButtonState.reset();
    myAvoidDynamicRefs.setToolTipText("Best effort to use static references, not dynamic, when target is referenced by name/resolveInfo");
  }

  public String getName() {
    return "Generation";
  }

  public Icon getIcon() {
    return Nodes.Generator;
  }

  @Override
  public JComponent createComponent() {
    return myPage;
  }

  public JPanel createPage() {
    JPanel myMainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.weightx = 1;
    c.fill = GridBagConstraints.BOTH;

    c.gridy = 0;
    myMainPanel.add(createOptionsPanel(), c);

    c.gridy++;
    myMainPanel.add(createReportingPanel(), c);

    c.gridy++;
    myMainPanel.add(createTraceLevelPanel(), c);

    c.gridy++;
    myMainPanel.add(createTextGenPanel(), c);

    c.gridy++;
    c.weighty = 1;
    myMainPanel.add(new JPanel(), c);
    c.gridy++;
    c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.WEST;
    myStatusLabel = new JLabel();
    myStatusLabel.setBorder(new JBEmptyBorder(JBUI.insets(5)));
    myMainPanel.add(myStatusLabel, c);
    updateStatus();
    return myMainPanel;
  }

  private JPanel createOptionsPanel() {
    JPanel optionsPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 1;
    c.gridx = 0;
    c.ipady = 2;
    c.fill = GridBagConstraints.BOTH;
    optionsPanel.add(mySaveTransientModelsCheckBox, c);
    optionsPanel.add(myCheckModelsBeforeGenerationCheckBox, c);
    optionsPanel.add(myStrictMode, c);
    c.ipady = 0;
    optionsPanel.add(createParallelGenerationGroup(), c);
    c.ipady = 2;
    optionsPanel.add(myInplaceTransform, c);

    optionsPanel.add(myAvoidDynamicRefs, c);

    myButtonState.track(mySaveTransientModelsCheckBox, myCheckModelsBeforeGenerationCheckBox, myStrictMode, myInplaceTransform);
    myButtonState.track(myAvoidDynamicRefs);

    optionsPanel.setBorder(IdeBorderFactory.createTitledBorder("General"));

    mySaveTransientModelsCheckBox.addItemListener(myStatusUpdater);
    myInplaceTransform.addItemListener(myStatusUpdater);
    return optionsPanel;
  }

  private JPanel createParallelGenerationGroup() {
    JPanel parallelGen = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 0;
    parallelGen.add(myUseNewGenerator, c);
    final ChangeListener listener = e -> {
      myUseNewGenerator.setEnabled(myStrictMode.isSelected());
      myNumberOfParallelThreads.setEditable(myUseNewGenerator.isSelected() && myStrictMode.isSelected());
    };
    myStrictMode.addChangeListener(listener);
    myUseNewGenerator.addChangeListener(listener);
    c.insets.left = 7;
    parallelGen.add(new JLabel("Use"), c);
    c.insets.left = 3;
    myNumberOfParallelThreads.setColumns(2);
    parallelGen.add(myNumberOfParallelThreads, c);
    c.insets.left = 2;
    parallelGen.add(new JLabel("cores"), c);
    c.weightx = 1;
    parallelGen.add(new JPanel(), c);

    parallelGen.setToolTipText(String.format("This computer has %d processors", Runtime.getRuntime().availableProcessors()));

    myButtonState.track(myUseNewGenerator);

    return parallelGen;
  }

  private JPanel createReportingPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.weightx = 1;
    c.gridx = 0;
    c.ipady = 2;
    c.fill = GridBagConstraints.BOTH;
    panel.add(myShowInfo, c);
    panel.add(myShowWarnings, c);
    c.insets.left = 16;
    panel.add(myKeepModelsWithWarnings, c);
    panel.add(myShowBadChildWarnings, c);
    myButtonState.track(myShowInfo, myShowWarnings, myKeepModelsWithWarnings, myShowBadChildWarnings);

    final ChangeListener listener = e -> {
      myKeepModelsWithWarnings.setEnabled(myShowWarnings.isSelected());
      myShowBadChildWarnings.setEnabled(myShowWarnings.isSelected());
    };
    myShowWarnings.addChangeListener(listener);

    c.insets.left = 0;
    c.ipady = 0;
    panel.add(createLinkErrorsGroup(), c);
    panel.setBorder(IdeBorderFactory.createTitledBorder("Error reporting"));
    return panel;
  }

  private JPanel createLinkErrorsGroup() {
    JPanel group = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.gridy = 0;
    group.add(myLimitNumberOfModels, c);
    final ChangeListener listener = e -> myNumberOfModelsToKeep.setEditable(myLimitNumberOfModels.isSelected());
    myLimitNumberOfModels.addChangeListener(listener);
    group.add(myLimitNumberOfModels, c);
    myNumberOfModelsToKeep.setColumns(3);
    c.insets.left = 5;
    group.add(myNumberOfModelsToKeep, c);
    c.weightx = 1;
    group.add(new JPanel(), c);
    myButtonState.track(myLimitNumberOfModels);
    return group;
  }

  private JPanel createTraceLevelPanel() {
    final ButtonGroup group = new ButtonGroup();
    group.add(myTraceNone);
    group.add(myTraceSteps);
    group.add(myTraceLanguages);
    group.add(myTraceTypes);

    myButtonState.track(myTraceNone, myTraceSteps, myTraceLanguages, myTraceTypes);

    JPanel gotoPanel = new JPanel();
    gotoPanel.setLayout(new BoxLayout(gotoPanel, BoxLayout.Y_AXIS));
    gotoPanel.add(myTraceNone);
    gotoPanel.add(myTraceSteps);
    gotoPanel.add(myTraceLanguages);
    gotoPanel.add(myTraceTypes);
    gotoPanel.setBorder(IdeBorderFactory.createTitledBorder("Model generation performance report"));
    return gotoPanel;
  }

  private JPanel createTextGenPanel() {
    JPanel textgenPanel = new JPanel();
    textgenPanel.setLayout(new BoxLayout(textgenPanel, BoxLayout.Y_AXIS));
    textgenPanel.add(myGenerateDebugInfo);
    textgenPanel.setBorder(IdeBorderFactory.createTitledBorder("TextGen options"));
    myButtonState.track(myGenerateDebugInfo);
    return textgenPanel;
  }

  public boolean validate() {
    return true;
  }

  @Override
  public void apply() {
    myGenerationSettings.setSaveTransientModels(mySaveTransientModelsCheckBox.isSelected());
    myGenerationSettings.setCheckModelsBeforeGeneration(myCheckModelsBeforeGenerationCheckBox.isSelected());
    myGenerationSettings.setParallelGenerator(myUseNewGenerator.isSelected());
    myGenerationSettings.setStrictMode(myStrictMode.isSelected());
    myGenerationSettings.setNumberOfParallelThreads((Integer) myNumberOfParallelThreads.getValue());
    myGenerationSettings.setPerformanceTracingLevel(getTracingLevel());
    myGenerationSettings.setShowInfo(myShowInfo.isSelected());
    myGenerationSettings.setShowWarnings(myShowWarnings.isSelected());
    myGenerationSettings.setKeepModelsWithWarnings(myKeepModelsWithWarnings.isSelected());
    myGenerationSettings.setShowBadChildWarning(myShowBadChildWarnings.isSelected());
    myGenerationSettings.setNumberOfModelsToKeep(getNumberOfModelsToKeep());
    myGenerationSettings.enableInplaceTransformations(myInplaceTransform.isSelected());
    myGenerationSettings.setCreateStaticReferences(myAvoidDynamicRefs.isSelected());
    myGenerationSettings.setGenerateDebugInfo(myGenerateDebugInfo.isSelected());

    myButtonState.reset(); // memorize the new state

    UISettings.getInstance().fireUISettingsChanged();
  }

  private int getTracingLevel() {
    return
      myTraceTypes.isSelected() ? GenerationOptions.TRACE_TYPES :
        myTraceLanguages.isSelected() ? GenerationOptions.TRACE_LANGS :
          myTraceSteps.isSelected() ? GenerationOptions.TRACE_STEPS
            : GenerationOptions.TRACE_OFF;
  }

  private int getNumberOfModelsToKeep() {
    return myLimitNumberOfModels.isSelected() ? (Integer) myNumberOfModelsToKeep.getValue() : -1;
  }

  @Override
  public boolean isModified() {
    return myButtonState.isStateModified() ||
      myGenerationSettings.getNumberOfModelsToKeep() != getNumberOfModelsToKeep() ||
      myGenerationSettings.getNumberOfParallelThreads() != (Integer) myNumberOfParallelThreads.getValue();
  }

  @Override
  public void disposeUIResources() {

  }

  @Override
  public void reset() {
    mySaveTransientModelsCheckBox.setSelected(myGenerationSettings.isSaveTransientModels());
    myCheckModelsBeforeGenerationCheckBox.setSelected(myGenerationSettings.isCheckModelsBeforeGeneration());
    myUseNewGenerator.setSelected(myGenerationSettings.isParallelGenerator());
    myInplaceTransform.setSelected(myGenerationSettings.useInplaceTransformations());
    myAvoidDynamicRefs.setSelected(myGenerationSettings.createStaticReferences());

    myStrictMode.setSelected(myGenerationSettings.isStrictMode());
    myUseNewGenerator.setEnabled(myGenerationSettings.isStrictMode());
    myNumberOfParallelThreads.setEditable(myGenerationSettings.isParallelGenerator() && myGenerationSettings.isStrictMode());
    myNumberOfParallelThreads.setValue(myGenerationSettings.getNumberOfParallelThreads());

    myShowInfo.setSelected(myGenerationSettings.isShowInfo());
    myShowWarnings.setSelected(myGenerationSettings.isShowWarnings());
    myKeepModelsWithWarnings.setEnabled(myGenerationSettings.isShowWarnings());
    myKeepModelsWithWarnings.setSelected(myGenerationSettings.isKeepModelsWithWarnings());
    myShowBadChildWarnings.setEnabled(myGenerationSettings.isShowWarnings());
    myShowBadChildWarnings.setSelected(myGenerationSettings.isShowBadChildWarning());
    myNumberOfModelsToKeep.setEditable(myGenerationSettings.getNumberOfModelsToKeep() != -1);
    myNumberOfModelsToKeep.setValue(myGenerationSettings.getNumberOfModelsToKeep() == -1 ? 16 : myGenerationSettings.getNumberOfModelsToKeep());
    myLimitNumberOfModels.setSelected(myGenerationSettings.getNumberOfModelsToKeep() != -1);

    myGenerateDebugInfo.setSelected(myGenerationSettings.isGenerateDebugInfo());

    final JRadioButton[] allbuttons = {myTraceNone, myTraceSteps, myTraceLanguages, myTraceTypes};
    allbuttons[myGenerationSettings.getPerformanceTracingLevel()].setSelected(true);

    myButtonState.reset(); // memorize the new state
  }

  void updateStatus() {
    myStatusLabel.setVisible(false);
    ArrayList<String> messages = new ArrayList<>();
    if (myInplaceTransform.isSelected() && mySaveTransientModelsCheckBox.isSelected()) {
      messages.add(String.format("<h2>Warning:</h2>Using <strong>%s</strong><br>together with <strong>%s</strong>may slow down generation process significantly",
                                 myInplaceTransform.getText(), mySaveTransientModelsCheckBox.getText()));
    }
    if (!messages.isEmpty()) {
      myStatusLabel.setText(String.format("<html>%s</html>", String.join("<br/>", messages)));
      myStatusLabel.setBackground(JBColor.ORANGE);
      myStatusLabel.setOpaque(true);
      myStatusLabel.setVisible(true);
    }
  }

  @NotNull
  @Override
  public String getId() {
    return "generator.manager";
  }

  @Nullable
  @Override
  public Runnable enableSearch(String option) {
    return null;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return  "Generator";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return "preferences.generator";
  }

  private class RangeDecimalFormatter extends DefaultFormatter {
    private final int myLo;
    private final int myHi;

    private RangeDecimalFormatter(int lo, int hi) {
      super();
      setAllowsInvalid(true);
      setCommitsOnValidEdit(true);
      myLo = lo;
      myHi = hi;
    }

    @Override
    public Object stringToValue(String text) throws ParseException {
      try {
        int i = Integer.parseInt(text);
        if (i < myLo || i > myHi) {
          throw new ParseException(text, text.length() - 1);
        }
        return i;
      } catch (NumberFormatException e) {
        throw new ParseException(text, 0);
      }
    }

    @Override
    public String valueToString(@Nullable Object value) {
      if (value == null) return null;
      return Integer.toString((Integer) value);
    }
  }

  private static class ButtonSelectStateTracker {
    private final Map<AbstractButton,Boolean> myButtonStates = new HashMap<>();

    public ButtonSelectStateTracker track(AbstractButton... buttons) {
      for (AbstractButton btn : buttons) {
        myButtonStates.put(btn, btn.isSelected());
      }
      return this;
    }

    public void reset() {
      for (Map.Entry<AbstractButton, Boolean> e : myButtonStates.entrySet()) {
        e.setValue(e.getKey().isSelected());
      }
    }

    public boolean isStateModified() {
      for (Map.Entry<AbstractButton, Boolean> e : myButtonStates.entrySet()) {
        if (e.getKey().isSelected() != e.getValue()) {
          return true;
        }
      }
      return false;
    }
  }
}
