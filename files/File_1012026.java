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
import jetbrains.mps.openapi.navigation.ProjectPaneNavigator;
import jetbrains.mps.project.Project;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import javax.swing.Icon;

public class ModuleNodeData extends AbstractResultNodeData {
  private static final String MODULE_REF = "module_ref";

  private SModuleReference myModuleReference;

  public ModuleNodeData(PathItemRole role, @Nullable String caption, @Nullable String info, @NotNull SModuleReference moduleRef, boolean isResult, boolean resultsSection) {
    super(role, caption != null ? caption : getCaption(moduleRef), info, isResult, resultsSection);
    myModuleReference = moduleRef;
  }

  public ModuleNodeData(Element element, Project project) throws CantLoadSomethingException {
    read(element, project);
  }

  private static String getCaption(SModuleReference moduleRef) {
    return moduleRef.getModuleName();
  }


  @Override
  public Icon getIcon(PresentationContext presentationContext) {
    SModule module = myModuleReference.resolve(presentationContext.getRepository());
    return module == null ? null : GlobalIconManager.getInstance().getIconFor(module);
  }

  public SModuleReference getModuleReference() {
    return myModuleReference;
  }

  @Override
  public String createIdObject() {
    return getModuleReference().toString();
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    super.write(element, project);
    element.setAttribute(MODULE_REF, PersistenceFacade.getInstance().asString(myModuleReference));
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    super.read(element, project);
    myModuleReference = PersistenceFacade.getInstance().createModuleReference(element.getAttributeValue(MODULE_REF));
  }

  @Override
  public void navigate(Project mpsProject, boolean useProjectTree, boolean focus) {
    new ProjectPaneNavigator(mpsProject).shallFocus(focus).select(myModuleReference);
  }
}
