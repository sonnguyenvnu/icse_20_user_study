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
package jetbrains.mps.nodeEditor.hintsSettings;

import com.intellij.ui.IdeBorderFactory;
import jetbrains.mps.openapi.editor.descriptor.ConceptEditorHint;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Semen Alperovich
 * 05 15, 2013
 */
public class ConceptEditorHintPreferencesPage {
  private JPanel myPreferencesPanel;
  private final ConceptEditorHintSettings currentSettings = new ConceptEditorHintSettings();
  private final ConceptEditorHintSettings registrySettings;

  public ConceptEditorHintPreferencesPage(ConceptEditorHintSettings state) {
    registrySettings = state != null ? state : new ConceptEditorHintSettings();
  }

  public JComponent getComponent() {
    if (myPreferencesPanel == null) {
      myPreferencesPanel = new JPanel(new GridBagLayout());
      update();
    }
    return myPreferencesPanel;
  }

  public boolean isModified() {
    for (String langName : currentSettings.getLanguagesNames()) {
      for (ConceptEditorHint hint : currentSettings.getHints(langName)) {
        if (!registrySettings.containsKey(langName, hint)) {
          continue;
        }
        if (!registrySettings.get(langName, hint).equals(currentSettings.get(langName, hint))) {
          return true;
        }
      }
    }
    return false;
  }

  public void reset() {
    update();
  }

  private void update() {
    myPreferencesPanel.removeAll();
    syncSettings(registrySettings, currentSettings);
    myPreferencesPanel.setLayout(new BoxLayout(myPreferencesPanel, BoxLayout.Y_AXIS));
    ArrayList<String> names = new ArrayList<>(currentSettings.getLanguagesNames());
    Collections.sort(names);
    for (String langName : names) {
      if (langName != null) {
        JPanel languagePanel = new JPanel();
        languagePanel.setLayout(new BoxLayout(languagePanel, BoxLayout.Y_AXIS));
        languagePanel.add(Box.createHorizontalGlue());
        languagePanel.setBorder(IdeBorderFactory.createTitledBorder(langName, false));
        languagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        for (ConceptEditorHint hint : currentSettings.getHints(langName)) {
          addHintCheckbox(languagePanel, langName, hint, currentSettings.get(langName, hint));
        }
        myPreferencesPanel.add(languagePanel);
      }
    }
  }

  private void syncSettings(ConceptEditorHintSettings from, ConceptEditorHintSettings where) {
    where.clear();
    where.putAll(from);
  }

  private void addHintCheckbox(JPanel panel, final String lang, final ConceptEditorHint hint, boolean state) {
    JCheckBox item = new JCheckBox(hint.getId() + ": " + hint.getPresentation());
    item.setSelected(state);
    panel.add(item);
    item.addItemListener(e -> {
      assert currentSettings.containsKey(lang, hint);
      currentSettings.put(lang, hint, e.getStateChange() == ItemEvent.SELECTED);
    });
  }

  public void commit() {
    syncSettings(currentSettings, registrySettings);
  }
}
