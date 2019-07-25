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
import jetbrains.mps.logging.Logger;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.openapi.navigation.ProjectPaneNavigator;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.SNodeUtil;
import org.apache.log4j.LogManager;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import javax.swing.Icon;

public class NodeNodeData extends AbstractResultNodeData {
  private static final Logger LOG = Logger.wrap(LogManager.getLogger(NodeNodeData.class));

  private static final String NODE = "nodePtr";
  private static final String IS_ROOT = "isRoot";

  private SNodeReference myNodePointer;
  private boolean myIsRootNode;

  public NodeNodeData(PathItemRole role, @Nullable String caption, @Nullable String info, @NotNull SNode pathObject, boolean isPathTail,
                      boolean resultsSection) {
    super(role, caption != null ? caption : snodeRepresentation(pathObject), info != null ? info : nodeAdditionalInfo(pathObject), isPathTail,
          resultsSection);
    myNodePointer = pathObject.getReference();
    myIsRootNode = pathObject.getModel() != null && pathObject.getParent() == null;
  }

  public NodeNodeData(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  public SNodeReference getNodePointer() {
    return myNodePointer;
  }

  @Override
  public Icon getIcon(PresentationContext presentationContext) {
    final SNode node = myNodePointer.resolve(presentationContext.getRepository());
    return node == null ? null : GlobalIconManager.getInstance().getIconFor(node);
  }

  @Override
  public String createIdObject() {
    return myNodePointer.toString();
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    super.write(element, project);
    element.setAttribute(NODE, PersistenceFacade.getInstance().asString(myNodePointer));
    if (myIsRootNode) {
      element.setAttribute(IS_ROOT, Boolean.TRUE.toString());
    }
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    super.read(element, project);
    try {
      myNodePointer = PersistenceFacade.getInstance().createNodeReference(element.getAttributeValue(NODE));
      myIsRootNode = Boolean.parseBoolean(element.getAttributeValue(IS_ROOT)); // false for null if fine.
    } catch (Exception ex) {
      throw new CantLoadSomethingException(ex);
    }
  }

  private static String snodeRepresentation(final SNode node) {
    try {
      String presentation = SNodeUtil.getPresentation(node);
      String result = (presentation != null) ? presentation : node.toString();
      LOG.assertLog(result != null, "Node presentation is null.");
      return result;
    } catch (Throwable t) {
      LOG.error(t);
      return "Exception was thrown during node representation calculation " + t.getMessage();
    }
  }

  private static String nodeAdditionalInfo(final SNode node) {
    if (node.getParent() == null) {
      return "";
    }
    return String.format("(role: %s in: %s)", node.getContainmentLink().getName(), snodeRepresentation(node.getParent()));
  }

  public boolean isRootNode() {
    return myIsRootNode;
  }

  @Override
  public void navigate(Project mpsProject, boolean useProjectTree, boolean focus) {
    if (useProjectTree) {
      new ProjectPaneNavigator(mpsProject).shallFocus(focus).select(myNodePointer);
    } else {
      new EditorNavigator(mpsProject).shallFocus(focus).selectIfChild().open(myNodePointer);
    }
  }
}
