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
package jetbrains.mps.ide.ui.tree.module;

import jetbrains.mps.ide.icons.IdeIcons;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.project.DevKit;
import jetbrains.mps.project.Project;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;


public class ProjectDevKitTreeNode extends ProjectModuleTreeNode {
  private final Project myProject;
  private boolean myShortNameOnly;
  private boolean myInitialized;

  protected ProjectDevKitTreeNode(@NotNull DevKit devkit, Project project, boolean shortNameOnly) {
    super(devkit);
    myProject = project;
    myShortNameOnly = shortNameOnly;

    setNodeIdentifier(devkit.getModuleId().toString()); // Generally, PersistenceFacade.asString() is better, but it's just an unique string, anyway
    setIcon(IdeIcons.DEVKIT_ICON);
  }

  @Override
  public boolean isInitialized() {
    return myInitialized;
  }

  @NotNull
  @Override
  public DevKit getModule() {
    return (DevKit) super.getModule();
  }

  @Override
  protected void doInit() {
    ModuleNodeChildrenProvider childrenProvider = getAncestor(ModuleNodeChildrenProvider.class);
    if (childrenProvider == null || !childrenProvider.populate(this, getModule())) {
      populate();
    }
    myInitialized = true;
  }

  @Override
  public String getModuleText() {
    String name = getModule().getModuleDescriptor().getNamespace();

    if (myShortNameOnly) {
      name = NameUtil.shortNameFromLongName(name);
    }

    if (name != null) {
      return name;
    }
    return "devKit";
  }

  private void populate() {
    TextTreeNode extendedDevkits = new TextTreeNode("extended devkits");
    for (DevKit d : getModule().getExtendedDevKits()) {
      extendedDevkits.add(ProjectModuleTreeNode.createFor(myProject, d));
    }
    add(extendedDevkits);

    TextTreeNode exportedLangs = new TextTreeNode("exported languages");
    for (Language l : getModule().getExportedLanguages()) {
      exportedLangs.add(ProjectModuleTreeNode.createFor(myProject, l));
    }
    add(exportedLangs);

    TextTreeNode exportedSolutions = new TextTreeNode("exported solutions");
    for (Solution s : getModule().getExportedSolutions()) {
      exportedSolutions.add(ProjectModuleTreeNode.createFor(myProject, s));
    }
    add(exportedSolutions);
  }
}
