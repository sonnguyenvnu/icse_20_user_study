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
package jetbrains.mps.nodeEditor.hintsSettings;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.Configurable.Composite;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import jetbrains.mps.ide.editor.util.EditorComponentUtil;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.nodeEditor.hintsSettings.ConceptEditorHintSettingsComponent.HintsState;
import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRegistryListener;
import jetbrains.mps.smodel.language.LanguageRuntime;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * Semen Alperovich
 * 05 15, 2013
 */
public class ConceptEditorHintConfigurable implements SearchableConfigurable, Composite {
  private ConceptEditorHintSettings mySettings;
  private ConceptEditorHintPreferencesPage myPage;
  private final Project myProject;
  private LanguageRegistryListener myLanguageReloadListener;
  private boolean myLanguageRegistryChanged = false;
  private final LanguageRegistry myLanguageRegistry;

  public ConceptEditorHintConfigurable(@NotNull Project project) {
    myProject = project;
    myLanguageRegistry = ProjectHelper.fromIdeaProject(myProject).getComponent(LanguageRegistry.class);
  }

  @NotNull
  @Override
  public String getId() {
    return "editor.settings.hints";
  }

  @Nullable
  @Override
  public Runnable enableSearch(String option) {
    return null;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "Editor hints";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return "preferences.editor.hints";
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return getPage().getComponent();
  }

  private ConceptEditorHintPreferencesPage getPage() {
    if (myPage == null) {
      mySettings = myLanguageRegistry == null ? new ConceptEditorHintSettings() : new ConceptEditorHintSettings(myLanguageRegistry);
      mySettings.updateSettings(ConceptEditorHintSettingsComponent.getInstance(myProject).getState().getEnabledHints());
      myPage = new ConceptEditorHintPreferencesPage(mySettings);
      if (myLanguageRegistry != null) {
        myLanguageRegistry.addRegistryListener(myLanguageReloadListener = new LanguageRegistryListener() {
          @Override
          public void afterLanguagesLoaded(Iterable<LanguageRuntime> languages) {
            myLanguageRegistryChanged = true;
          }

          @Override
          public void beforeLanguagesUnloaded(Iterable<LanguageRuntime> languages) {
            myLanguageRegistryChanged = true;
          }
        });
      }
    }
    return myPage;
  }

  @Override
  public boolean isModified() {
    return myPage.isModified() || myLanguageRegistryChanged;
  }

  @Override
  public void apply() {
    if (myLanguageRegistryChanged) {
      showRegistryChangedDialog();
      return;
    }
    myPage.commit();
    HintsState newState = new HintsState();
    newState.setEnabledHints(mySettings.getEnabledHints());
    ConceptEditorHintSettingsComponent.getInstance(myProject).loadState(newState);
    for (EditorComponent component : EditorComponentUtil.getAllEditorComponents(FileEditorManager.getInstance(myProject), true)) {
      component.getEditorContext().getRepository().getModelAccess().runReadAction(component::rebuildEditorContent);
    }
  }

  private void showRegistryChangedDialog() {
    JOptionPane.showMessageDialog(myPage.getComponent(), "Some languages have been reloaded.\nPlease reopen settings page", "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void reset() {
    if (myLanguageRegistryChanged) {
      showRegistryChangedDialog();
      return;
    }
    myPage.reset();
  }

  @Override
  public void disposeUIResources() {
    myPage = null;
    if (myLanguageRegistry != null && myLanguageReloadListener != null) {
      myLanguageRegistry.removeRegistryListener(myLanguageReloadListener);
      myLanguageReloadListener = null;
    }
  }

  @NotNull
  @Override
  public Configurable[] getConfigurables() {
    return new Configurable[0];
  }
}
