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
package jetbrains.mps.typesystem.uiActions;

import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.Splitter;
import jetbrains.mps.errors.IErrorReporter;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;

public class MyBaseNodeDialog extends BaseNodeDialog {
  private final SNode myType;
  private SModel myModel;
  private final IErrorReporter myError;
  private boolean myWasRegistered = true;
  private Splitter myMainComponent;
  private JComponent mySupertypesViewComponent;

  public MyBaseNodeDialog(Project mpsProject, String title, SNode type, IErrorReporter error) {
    super(mpsProject, title);

    SupertypesViewTool supertypesView = mpsProject.getComponent(SupertypesViewTool.class);

    mySupertypesViewComponent = supertypesView.getComponent();
    myMainComponent = new Splitter(false);
    myMainComponent.setFirstComponent(super.getMainComponent());
    myMainComponent.setSecondComponent(LabeledComponent.create(mySupertypesViewComponent, "Supertypes"));

    myType = type;
    myModel = myType.getModel();

    myError = error;
    supertypesView.showItemInHierarchy(myType);

    //setHorizontalStretch(1f);
    //setHorizontalStretch(1f);

    init();
  }

  @Override
  protected JComponent createCenterPanel() {
    return myMainComponent;
  }

  @NotNull
  @Override
  protected Action[] createActions() {
    if(myError != null) {
      String s = new ModelAccessHelper(getProject().getModelAccess()).runReadAction(() -> myError.reportError());
      setErrorText(s);
      if (myError.getRuleNode() != null) {
        return new Action[]{getOKAction(), new AbstractAction("Go To Rule") {
          public void actionPerformed(ActionEvent e) {
            new EditorNavigator(getProject()).shallSelect(true).open(myError.getRuleNode());
          }
        }};
      }
    }
    return new Action[]{getOKAction()};
  }

  @Override
  protected SNode getNode() {
    return myType;
  }

  @Override
  protected void dispose() {
    if (mySupertypesViewComponent != null && mySupertypesViewComponent.getParent() != null) {
      mySupertypesViewComponent.getParent().remove(mySupertypesViewComponent);
    }
    getProject().getModelAccess().runWriteAction(() -> {
      if (!myWasRegistered) {
        myModel.removeRootNode(myType.getContainingRoot());
        myWasRegistered = true;
      }
      MyBaseNodeDialog.super.dispose();
    });
  }
}
