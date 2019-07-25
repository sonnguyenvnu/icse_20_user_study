/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.workbench.action;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import jetbrains.mps.InternalFlag;
import jetbrains.mps.ide.MPSCoreComponents;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.workbench.ActionPlace;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.ModelAccess;
import org.jetbrains.mps.util.Condition;

import javax.swing.Icon;
import java.util.Arrays;
import java.util.List;

public class BaseGroup extends DefaultActionGroup implements DumbAware {
  private final String myId;
  private boolean myIsInternal = false;
  private boolean myIsAlwaysVisible = true;

  public BaseGroup(String name) {
    this(name, name);
  }

  public BaseGroup(String text, String id) {
    this(text, id, null);
  }

  public BaseGroup(String text, String id, Icon icon) {
    super(text, false);
    myId = id;
    getTemplatePresentation().setIcon(icon);
  }

  public final void setIsAlwaysVisible(boolean isAlwaysVisible) {
    myIsAlwaysVisible = isAlwaysVisible;
  }

  public final void setIsInternal(boolean isInternal) {
    myIsInternal = isInternal;
  }

  public final void setMnemonic(char mnemonic) {
    String text = getTemplatePresentation().getText();
    int pos = text.indexOf(Character.toUpperCase(mnemonic));
    if (pos == -1) pos = text.indexOf(Character.toLowerCase(mnemonic));
    StringBuilder newText = new StringBuilder(text);
    newText.insert(pos, '_');
    getTemplatePresentation().setText(newText.toString());
  }

  public final String getId() {
    return myId;
  }

  public final void disable(Presentation p) {
    p.setEnabled(false);
    p.setVisible(myIsAlwaysVisible);
  }

  public final void enable(Presentation p) {
    p.setEnabled(true);
    p.setVisible(true);
  }

  protected final void setEnabledState(Presentation p, boolean state) {
    if (state) enable(p);
    else disable(p);
  }

  @Override
  public void update(final AnActionEvent e) {
    super.update(e);
    if (myIsInternal && !InternalFlag.isInternalMode()) {
      e.getPresentation().setEnabled(false);
      e.getPresentation().setVisible(false);
    } else {
      getModelAccess(e).runReadAction(() -> {
        try {
          e.getPresentation().setEnabled(true);
          e.getPresentation().setVisible(true);
          doUpdate(e);
        } catch (Throwable ex) {
          Logger.getLogger(BaseGroup.this.getClass()).error("Action group update failed", ex);
        }
      });
    }
  }

  public void addPlace(ActionPlace place, @Nullable Condition<BaseAction> condition) {
    List<AnAction> actionList = Arrays.asList(getChildren(null));
    addPlaceToActionList(actionList, place, condition);
  }

  protected void doUpdate(AnActionEvent e) {

  }

  public static void addPlaceToActionList(List<? extends AnAction> actions, ActionPlace place, @Nullable Condition<BaseAction> condition) {
    for (AnAction child : actions) {
      if (child instanceof ActionGroup) {
        List<AnAction> children = Arrays.asList(((ActionGroup) child).getChildren(null));
        addPlaceToActionList(children,place,condition);
      } else if (child instanceof BaseAction) {
        BaseAction action = (BaseAction) child;
        if (condition == null || condition.met(action)) {
          action.addPlace(place);
        }
      }
    }
  }

  // copied from BaseAction.getModelAccess()
  protected final ModelAccess getModelAccess(AnActionEvent event) {
    Project project = getEventProject(event);
    if (project != null) {
      return ProjectHelper.getModelAccess(project);
    } else {
      return ApplicationManager.getApplication().getComponent(MPSCoreComponents.class).getModuleRepository().getModelAccess();
    }
  }
}
