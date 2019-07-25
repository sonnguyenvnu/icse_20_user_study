/*
 * Copyright 2003-2015 JetBrains s.r.o.
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

package jetbrains.mps.idea.build;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import jetbrains.mps.extapi.model.GeneratableSModel;
import jetbrains.mps.fileTypes.FileIcons;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;

import javax.swing.Icon;
import java.util.Collections;

/**
 * Created by danilla on 21/10/15.
 */
public class GenerateModelInProcessAction extends AnAction {

  public GenerateModelInProcessAction(@Nullable String name, @Nullable String description, @Nullable Icon icon) {
    super(name, description, icon);
  }

  public GenerateModelInProcessAction() {
    this("Generate model", null, FileIcons.MODEL_ICON);
  }

  @Override
  public void update(AnActionEvent e) {
    SModel model = MPSCommonDataKeys.CONTEXT_MODEL.getData(e.getDataContext());
    e.getPresentation().setVisible(model instanceof GeneratableSModel);
  }

  @Override
  public void actionPerformed(AnActionEvent event) {
    Project project = CommonDataKeys.PROJECT.getData(event.getDataContext());
    SModel model = MPSCommonDataKeys.CONTEXT_MODEL.getData(event.getDataContext());
    new GenerateModelsInProcess(project, Collections.singletonList(model)).generate(getMakeConfigrator(event));
  }

  @Nullable
  protected MPSMakeConfigurator getMakeConfigrator(AnActionEvent event) {
    return null;
  }
}
