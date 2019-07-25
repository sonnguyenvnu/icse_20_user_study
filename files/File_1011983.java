/*
 * Copyright 2003-2019 JetBrains s.r.o.
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

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.openapi.wm.ex.StatusBarEx;
import gnu.trove.THashMap;
import jetbrains.mps.core.platform.Platform;
import jetbrains.mps.ide.MPSCoreComponents;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.make.MakeServiceComponent;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.UndoRunnable;
import jetbrains.mps.util.Computable;
import jetbrains.mps.workbench.ActionPlace;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.ModelAccess;

import javax.swing.Icon;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BaseAction extends AnAction {
  private boolean myIsAlwaysVisible = true;
  private boolean myExecuteOutsideCommand = false;
  private boolean myDisableOnNoProject = true;
  private Set<ActionPlace> myPlaces = null;

  public BaseAction() {
    this(null, null, null);
  }

  public BaseAction(String text) {
    this(text, null, null);
  }

  public BaseAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
    super(text, description, icon);
    setEnabledInModalContext(true);
  }

  public void setExecuteOutsideCommand(boolean executeOutsideCommand) {
    myExecuteOutsideCommand = executeOutsideCommand;
  }

  public boolean isExecuteOutsideCommand() {
    return myExecuteOutsideCommand;
  }

  public void setIsAlwaysVisible(boolean isAlwaysVisible) {
    myIsAlwaysVisible = isAlwaysVisible;
  }

  public void setDisableOnNoProject(boolean disableOnNoProject) {
    myDisableOnNoProject = disableOnNoProject;
  }

  public boolean isApplicable(final AnActionEvent event, final Map<String, Object> _params) {
    return false;
  }

  public void setMnemonic(char mnemonic) {
    String text = getTemplatePresentation().getText();
    int pos = text.indexOf(Character.toUpperCase(mnemonic));
    if (pos == -1) {
      pos = text.indexOf(Character.toLowerCase(mnemonic));
    }
    StringBuilder newText = new StringBuilder(text);
    newText.insert(pos, '_');
    getTemplatePresentation().setText(newText.toString());
  }

  @Override
  public final void update(final AnActionEvent e) {
    super.update(e);

    ActionPlace place = e.getData(MPSCommonDataKeys.PLACE);

    if (e.getInputEvent() instanceof KeyEvent) {
      if (!getPlaces().contains(null)) {
        if (!getPlaces().contains(place)) {
          disable(e.getPresentation());
          return;
        }
      }
    }

    final Project eventProject = getEventProject(e);

    if (myDisableOnNoProject && eventProject == null) {
      disable(e.getPresentation());
      return;
    }
    if (eventProject != null && eventProject.isDisposed()) {
      // I feel it's IDEA's responsibility not to ask actions for update when project is disposed,
      // nevertheless, https://youtrack.jetbrains.com/issue/MPS-26399 suggests it doesn't care enough.
      disable(e.getPresentation());
      return;
    }

    getModelAccess(e).runReadAction(new Runnable() {
      @Override
      public void run() {
        Map<String, Object> params = new CollectActionData(e).compute();
        if (params == null) {
          disable(e.getPresentation());
          return;
        }
        try {
          doUpdate(e, params);
        } catch (RuntimeException ex) {
          final Logger log = LogManager.getLogger(getClass());
          if (log.isEnabledFor(Level.ERROR)) {
            log.error(String.format("User's action doUpdate method failed. Action: %s. Class: %s", getTemplatePresentation().getText(), BaseAction.this.getClass().getName()), ex);
          }
          disable(e.getPresentation());
        }
      }
    });
  }

  @Override
  public final void actionPerformed(final AnActionEvent event) {
    if (!myExecuteOutsideCommand && isMakeSessionActive()) {
      notifyNoCommandDuringMake(event);
      return;
    }
    final Map<String, Object> params = new ModelAccessHelper(getModelAccess(event)).runReadAction(new CollectActionData(event));

    final Runnable r = new UndoRunnable.Base(getTemplatePresentation().getText(), null) {
      @Override
      public void run() {
        try {
          doExecute(event, params);
        } catch (RuntimeException ex) {
          final Logger log = LogManager.getLogger(getClass());
          if (log.isEnabledFor(Level.ERROR)) {
            log.error(String.format("User's action execute method failed. Action: %s. Class: %s", getName(), BaseAction.this.getClass().getName()), ex);
          }
        }
      }
    };
    if (myExecuteOutsideCommand) {
      r.run();
    } else {
      Project project = getEventProject(event);
      if (project != null) {
        // XXX project != null shall become assert once we've found all actions that require command but run without project
        getModelAccess(event).executeCommand(r);
      } else {
        Logger.getLogger(BaseAction.class).error(String.format("Action %s needs a command but is executed without project.", getClass().getName()));
        // it's odd to have an action that runs without a project, but still wants a command.
        // Present implementation of openapi.ModelAccess in global repository doesn't support commands,
        // thus we run it as a mere write action
        getModelAccess(event).runWriteAction(r);
      }
    }
  }

  protected final ModelAccess getModelAccess(AnActionEvent event) {
    Project project = getEventProject(event);
    if (project != null && !project.isDisposed()) {
      return ProjectHelper.getModelAccess(project);
    } else {
      return ApplicationManager.getApplication().getComponent(MPSCoreComponents.class).getModuleRepository().getModelAccess();
    }
  }

  protected void disable(Presentation p) {
    p.setEnabled(false);
    p.setVisible(myIsAlwaysVisible);
  }

  protected void enable(final Presentation p) {
    p.setEnabled(true);
    p.setVisible(true);
  }

  //made public just to use in MPS classifiers, workaround on MPS-3472

  public void setEnabledState(Presentation p, boolean state) {
    if (state) {
      enable(p);
    } else {
      disable(p);
    }
  }

  public void addPlace(ActionPlace place) {
    if (myPlaces == null) {
      myPlaces = new HashSet<>(8);
    }
    myPlaces.add(place);
  }

  public Set<ActionPlace> getPlaces() {
    if (myPlaces != null) {
      return myPlaces;
    }
    Set<ActionPlace> result = new HashSet<>();
    result.add(null);
    return result;
  }

  protected boolean collectActionData(AnActionEvent e, Map<String, Object> params) {
    return true;
  }

  protected void doUpdate(AnActionEvent e, Map<String, Object> params) {
    e.getPresentation().setVisible(true);
    e.getPresentation().setEnabled(true);
  }

  public String getActionId() {
    return getClass().getName();
  }

  protected abstract void doExecute(AnActionEvent e, Map<String, Object> params);

  protected final boolean isMakeSessionActive() {
    final Platform mpsPlaf = ApplicationManager.getApplication().getComponent(MPSCoreComponents.class).getPlatform();
    final MakeServiceComponent makeService = mpsPlaf.findComponent(MakeServiceComponent.class);
    return makeService != null && makeService.isSessionActive();
  }

  // this method is protected to help complex actions that may grab model write/command later
  protected final void notifyNoCommandDuringMake(final AnActionEvent event) {
    final Project project = getEventProject(event);
    if (project == null) {
      return;
    }
    final String actionText = event.getPresentation().getText();
    String msg;
    if (actionText == null || actionText.trim().isEmpty()) {
      msg = "This action";
    } else {
      msg = String.format("Action '%s'", actionText);
    }
    msg = String.format("%s requires model command and can not run during make", msg);
    showNotification(project, MessageType.WARNING, msg);
  }

  // requires EDT
  protected final void showNotification(Project project, MessageType kind, String htmlMessage) {
    // stolen from DumbServiceImpl#showDumbModeNotification
    IdeFrame ideFrame = WindowManager.getInstance().getIdeFrame(project);
    if (ideFrame != null) {
      StatusBarEx statusBar = (StatusBarEx)ideFrame.getStatusBar();
      statusBar.notifyProgressByBalloon(kind, htmlMessage);
    }
  }

  /**
   * Produce initialized map with action parameters, or null if any required parameter is missing
   */
  private class CollectActionData implements Computable<Map<String,Object>> {
    private final AnActionEvent myEvent;

    public CollectActionData(AnActionEvent event) {
      myEvent = event;
    }

    @Override
    public Map<String, Object> compute() {
      THashMap<String, Object> params = new THashMap<>();
      if (collectActionData(myEvent, params)) {
        return params;
      }
      return null;
    }
  }
}
