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

import jetbrains.mps.icons.MPSIcons;
import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.view.treeholder.treeview.path.PathItemRole;
import jetbrains.mps.openapi.navigation.ProjectPaneNavigator;
import jetbrains.mps.project.Project;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import javax.swing.Icon;

/**
 * @author Artem Tikhomirov
 */
public final class DeployedLanguageNodeData extends AbstractResultNodeData {
  private SLanguage myLanguage;

  public DeployedLanguageNodeData(PathItemRole role, @Nullable String caption, @Nullable String additionalInfo, @NotNull SLanguage l, boolean isPathTail, boolean resultsSection) {
    super(role, caption == null ? l.getQualifiedName() : caption, additionalInfo, isPathTail, resultsSection);
    myLanguage = l;
  }

  @Override
  protected String createIdObject() {
    return myLanguage.toString();
  }

  @Override
  public Icon getIcon(PresentationContext presentationContext) {
    return MPSIcons.LanguageRuntime;
  }

  @Override
  public void navigate(Project mpsProject, boolean useProjectTree, boolean focus) {
    new ProjectPaneNavigator(mpsProject).shallFocus(focus).select(myLanguage);
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    super.write(element, project);
    element.setAttribute("l", PersistenceFacade.getInstance().asString(myLanguage));
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    super.read(element, project);
    myLanguage = PersistenceFacade.getInstance().createLanguage(element.getAttributeValue("l"));
  }
}
