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
package jetbrains.mps.ide.findusages.view.treeholder.tree.nodedatatypes;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.path.PathItemRole;
import jetbrains.mps.ide.icons.GlobalIconManager;
import jetbrains.mps.ide.icons.IdeIcons;
import jetbrains.mps.openapi.navigation.ProjectPaneNavigator;
import jetbrains.mps.project.Project;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import javax.swing.Icon;

public class ModelNodeData extends AbstractResultNodeData {
  private static final String MODEL = "model";
  private static final String UID = "uid";

  public SModelReference myModelReference;

  public ModelNodeData(PathItemRole role, @Nullable String caption, @Nullable String info, @NotNull SModelReference modelReference, boolean isResult, boolean resultsSection) {
    super(role, caption != null ? caption : modelReference.getModelName(), info, isResult, resultsSection);
    myModelReference = modelReference;
  }

  public ModelNodeData(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  @Override
  public Icon getIcon(PresentationContext presentationContext) {
    SModel modelDescriptor = myModelReference.resolve(presentationContext.getRepository());
    if (modelDescriptor != null) {
      return GlobalIconManager.getInstance().getIconFor(modelDescriptor);
    }
    return IdeIcons.MODEL_ICON;
  }

  @Override
  protected String createIdObject() {
    // JFTR, getPlainText() was part of id here, see 59c49957 and https://youtrack.jetbrains.net/issue/MPS-15200 (MPS-10988)
    // however, I don't feel it make any sense now (getIdObject() is not in use for anything but MPSTreeNode identifier)
    return getModelReference().toString();
  }

  public SModelReference getModelReference() {
    return myModelReference;
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    super.write(element, project);
    Element modelXML = new Element(MODEL);
    modelXML.setAttribute(UID, PersistenceFacade.getInstance().asString(myModelReference));
    element.addContent(modelXML);
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    super.read(element, project);
    Element modelXML = element.getChild(MODEL);
    try {
      myModelReference = PersistenceFacade.getInstance().createModelReference(modelXML.getAttributeValue(UID));
    } catch (IllegalArgumentException ex) {
      throw new CantLoadSomethingException("cannot parse model reference", ex);
    }
  }

  @Override
  public void navigate(Project mpsProject, boolean useProjectTree, boolean focus) {
    new ProjectPaneNavigator(mpsProject).shallFocus(focus).select(myModelReference);
  }
}
