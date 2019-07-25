/*
 * Copyright 2003-2011 JetBrains s.r.o.
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

import com.intellij.ide.DataManager;
import com.intellij.ide.actions.RecentProjectsGroup;
import com.intellij.ide.ui.customization.CustomActionsSchema;
import com.intellij.openapi.actionSystem.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

import java.awt.event.InputEvent;

public class ActionUtils {
  private static final Logger LOG = LogManager.getLogger(ActionUtils.class);

  public static void updateGroup(ActionGroup group, AnActionEvent e) {
    try {
      group.update(e);
    } catch (Throwable t) {
      LOG.error(null, t);
    }
    for (AnAction child : group.getChildren(null)) {
      try {
        child.update(e);
      } catch (Throwable t) {
        LOG.error(null, t);
      }
      if (child instanceof ActionGroup) updateGroup((ActionGroup) child, e);
    }
  }

  public static BaseGroup getGroup(String id) {
    return ((BaseGroup) ActionManager.getInstance().getAction(id));
  }

  @Nullable
  public static ActionGroup getDefaultGroup(String id) {
    return (ActionGroup) CustomActionsSchema.getInstance().getCorrectedAction(id);
  }

  public static DefaultActionGroup groupFromActions(AnAction... actions) {
    DefaultActionGroup g = new DefaultActionGroup();
    for (AnAction action : actions) {
      if (action != null) {
        g.add(action);
      }
    }
    return g;
  }

  public static AnActionEvent createEvent(String place, DataContext context) {
    return new AnActionEvent(null, context, place, new Presentation(), ActionManager.getInstance(), 0);
  }

  public static AnActionEvent createEvent(InputEvent ie) {
    DataContext dataContext = DataManager.getInstance().getDataContext(ie.getComponent());
    return new AnActionEvent(ie, dataContext, ActionPlaces.UNKNOWN, new Presentation(), ActionManager.getInstance(), 0);
  }

  public static void updateAndPerformAction(AnAction action, AnActionEvent event) {
    action.update(event);
    if (event.getPresentation().isEnabled()) {
      action.actionPerformed(event);
    }
  }

  public static boolean contains(ActionGroup container, ActionGroup what) {
    if (container == what) return true;

    //todo this is a dirty hack. It is needed because this Idea group goes to FS and asks for file attributes, and this is performed too many times
    //todo the unregistration code should be rewritten in such a manner that additiona are registered and on unregistration we just remove our actions from the places we already know
    if (container instanceof RecentProjectsGroup) return false;

    for (AnAction child : container.getChildren(null)) {
      if (child instanceof ActionGroup) {
        if (contains((ActionGroup) child, what)) return true;
      }
    }
    return false;
  }
}
