/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor.cells.contextAssistant;

import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.ui.popup.ListPopupStep;
import com.intellij.openapi.ui.popup.PopupStep;
import jetbrains.mps.openapi.editor.menus.transformation.ActionItem;
import jetbrains.mps.openapi.editor.menus.transformation.SubMenu;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItem;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItemVisitor;
import org.jetbrains.annotations.Nullable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

/**
 * A menu item visitor that creates a top-level button for the visited menu item.
 */
class TopLevelButtonCreatingVisitor implements TransformationMenuItemVisitor<JButton> {
  private final ListPopupStep<TransformationMenuItem> myStep;

  TopLevelButtonCreatingVisitor(ListPopupStep<TransformationMenuItem> step) {
    myStep = step;
  }

  @Override
  public JButton visit(final ActionItem actionItem) {
    return new MyButton(actionItem, new AbstractAction(actionItem.getLabelText("")) {
      @Override
      public void actionPerformed(ActionEvent e) {
        executeFinalChoice(myStep, actionItem);
      }
    });
  }

  private static <T> void executeFinalChoice(ListPopupStep<T> step, T value) {
    PopupStep popupStep = step.onChosen(value, true);
    if (popupStep != PopupStep.FINAL_CHOICE) {
      return;
    }

    Runnable finalRunnable = step.getFinalRunnable();
    if (finalRunnable == null) {
      return;
    }

    finalRunnable.run();
  }

  @Override
  public JButton visit(SubMenu subMenu) {
    PopupStep subStep = myStep.onChosen(subMenu, false);
    if (!(subStep instanceof ListPopupStep)) {
      throw new IllegalStateException("sub-step for " + subMenu + " must be a ListPopupStep but was " + subStep);
    }
    return new StepComboBoxButton(subMenu, (ListPopupStep<?>) subStep);
  }

  private static class MyButton extends JButton implements DataProvider {
    private TransformationMenuItem myItem;

    public MyButton(TransformationMenuItem item, Action action) {
      super(action);
      myItem = item;
    }

    @Nullable
    @Override
    public Object getData(String dataId) {
      if (dataId.equals(PlatformDataKeys.SELECTED_ITEM.getName())) {
        return myItem;
      }
      return null;
    }
  }
}
