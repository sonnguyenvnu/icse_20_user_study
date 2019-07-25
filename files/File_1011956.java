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

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import jetbrains.mps.compiler.JavaCompilerOptionsComponent.JavaVersion;
import jetbrains.mps.ide.compiler.CompilerSettingsComponent.CompilerState;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;

public class CompilerSettingsConfigurable implements SearchableConfigurable {
  private CompilerSettingsPreferencePage myCompilerSettingsPreferencePage;
  private Project myProject;

  public CompilerSettingsConfigurable(Project project) {
    myProject = project;
  }

  @NotNull
  @Override
  public String getId() {
    return "mps.compiler";
  }

  @Nullable
  @Override
  public Runnable enableSearch(String option) {
    return null;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "Compiler";
  }

  @Nullable
  @Override
  public String getHelpTopic() {
    return "Compiler._Java_Compiler";
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return getPreferencePage().getMainPanel();
  }

  @Override
  public boolean isModified() {
    return getPreferencePage().isModified();
  }

  @Override
  public void apply() {
    getPreferencePage().commit();
    CompilerState compilerState = new CompilerState();
    JavaVersion selectedTargetJavaVersion = getPreferencePage().getSelectedTargetJavaVersion();
    if (selectedTargetJavaVersion != null) {
      compilerState.setTargetVersion(selectedTargetJavaVersion.getCompilerVersion());
    } else {
      compilerState.setTargetVersion(null);
    }
    CompilerSettingsComponent.getInstance(myProject).loadState(compilerState);
  }

  @Override
  public void reset() {
    getPreferencePage().reset();
  }

  @Override
  public void disposeUIResources() {
  }

  private CompilerSettingsPreferencePage getPreferencePage() {
    if (myCompilerSettingsPreferencePage == null) {
      myCompilerSettingsPreferencePage = new CompilerSettingsPreferencePage(CompilerSettingsComponent.getInstance(myProject).getState());
    }
    return myCompilerSettingsPreferencePage;
  }
}
