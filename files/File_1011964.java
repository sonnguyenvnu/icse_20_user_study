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
package jetbrains.mps.ide.ui.dialogs.properties.choosers;

import com.intellij.ide.util.gotoByName.ChooseByNameModel;
import com.intellij.ide.util.gotoByName.ChooseByNamePopupComponent.MultiElementsCallback;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.DumbService.DumbModeListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.Disposer;
import com.intellij.util.messages.MessageBusConnection;
import jetbrains.mps.workbench.goTo.ui.ChooseByNamePanel;
import jetbrains.mps.workbench.goTo.ui.MpsPopupFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ChooserDialog<T> extends DialogWrapper {

  protected final ChooseByNamePanel myChooser;
  protected final ChooseByNameModel myData;
  protected boolean myIsCancelled = true;
  protected boolean myOkDone = false;
  protected final Project myProject;
  private List<Object> mySelectedElements;

  ChooserDialog(Project project, @NotNull ChooseByNameModel data, boolean hasExtraScope, boolean multiSelection) {
    super(project);
    myProject = project;
    myData = data;
    // I have no idea why we call addNotify() here
    getContentPane().addNotify();
    setModal(true);

    myChooser = MpsPopupFactory.createPanelForPackage(project, data, hasExtraScope);
    // Although it's odd to have invoke() in the cons, we shall invoke it prior to super.init() otherwise there's no panel in the dialog
    myChooser.invoke(new MultiElementsCallback() {
      @Override
      public void elementsChosen(List<Object> elements) {
        if (!myOkDone) {
          // I've got no idea why do we guard with this flag, and what's use of its protected visibility
          myOkDone = true;
          mySelectedElements = elements;
          myIsCancelled = false;
          // according to ChooseByNamePopup, elementsChosen are invoked when selection
          // has to be processed (it was hard to express this contract in the method javadoc, one has to guess it from sources)
          // not when actual selection changes (i.e. on double click rather than on single click). As there's nothing else to do in
          // this dialog once element is picked, we close it.
          ChooserDialog.this.close(OK_EXIT_CODE);
        }
      }
    }, ModalityState.any(), multiSelection);
    Disposer.register(getDisposable(), myChooser);

    MessageBusConnection connection = project.getMessageBus().connect();
    Disposer.register(getDisposable(), connection);

    Runnable reactToDumbMode = () -> ChooserDialog.this.setErrorText("List of elements can be incomplete or empty while indexing is in progress");
    connection.subscribe(DumbService.DUMB_MODE, new DumbModeListener() {
      @Override
      public void enteredDumbMode() {
        reactToDumbMode.run();
      }

      @Override
      public void exitDumbMode() {
        ChooserDialog.this.setErrorText(null);
      }
    });
    // Can open dialog, while in dumb mode, so need additional check after subscribe to events.
    if (DumbService.isDumb(myProject)) {
      reactToDumbMode.run();
    }

    init();
  }

  /*
   * handle Ok dialog button. myChooser doesn't send out events when selection changes,
   * only when user double-clicks on an item. We need to handle scenario when user selects an element
   * and press 'Ok', that's why we consult chooser for selected elements here instead of using mySelectedElements value.
   */
  @Override
  protected void doOKAction() {
    myIsCancelled = false;
    mySelectedElements = myChooser.getChosenElements();
    super.doOKAction();
  }

  @NotNull
  public List<T> getResult() {
    if (myIsCancelled || mySelectedElements == null) {
      return Collections.emptyList();
    }
    List<T> result = new ArrayList<>();
    for (Object item : mySelectedElements) {
      if (item != null) {
        @SuppressWarnings("unchecked")
        T v = (T) item;
        result.add(v);
      }
    }
    return result;
  }

  @Override
  protected JComponent createCenterPanel() {
    return myChooser.getPanel();
  }

  @Override
  protected String getDimensionServiceKey() {
    return ChooserDialog.class.getCanonicalName();
  }

  @Override
  protected void dispose() {
    // I have no idea why we call dispose() here
    getContentPane().removeNotify();
    super.dispose();
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return myChooser.getPreferredFocusedComponent();
  }
}
