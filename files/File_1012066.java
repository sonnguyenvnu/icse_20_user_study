/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
import jetbrains.mps.ide.ui.tree.module.SModelsSubtree.StubsTreeNode;
import jetbrains.mps.ide.ui.tree.module.SModelsSubtree.TestsTreeNode;
import jetbrains.mps.project.AbstractModule;
import jetbrains.mps.project.Project;
import jetbrains.mps.project.Solution;
import jetbrains.mps.smodel.SModelStereotype;
import jetbrains.mps.smodel.tempmodel.TemporaryModels;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;

import java.util.ArrayList;
import java.util.List;

public class ProjectSolutionTreeNode extends ProjectModuleTreeNode {
  private boolean myShortNameOnly;

  private boolean myInitialized;

  protected ProjectSolutionTreeNode(@NotNull AbstractModule solution, Project project, boolean shortNameOnly) {
    super(solution);
    myShortNameOnly = shortNameOnly;
    setNodeIdentifier(solution.getModuleId().toString());
    setIcon(IdeIcons.SOLUTION_ICON);
  }

  @Override
  public String getModuleText() {
    String name = getModule().getModuleName();

    if (myShortNameOnly) {
      name = NameUtil.shortNameFromLongName(name);
    }

    if (name != null) {
      return name;
    }
    return "solution";
  }

  @Override
  public boolean isInitialized() {
    return myInitialized;
  }

  @Override
  protected void doInit() {
    if (getModule() instanceof Solution) {
      ModuleNodeChildrenProvider childrenProvider = getAncestor(ModuleNodeChildrenProvider.class);
      if (childrenProvider == null || !childrenProvider.populate(this, (Solution) getModule())) {
        populate();
      }
    } else {
      // there are other module implementations beside Solution (namely, ProjectStructureModule, TempModule, EvaluationModule)
      // that we don't care to control, and default implementation with all models is sufficient
      populate();
    }
    myInitialized = true;
  }

  private void populate() {
    List<SModel> regularModels = new ArrayList<>();
    List<SModel> tests = new ArrayList<>();
    List<SModel> stubs = new ArrayList<>();

    for (SModel modelDescriptor : getModule().getModels()) {
      if (TemporaryModels.isTemporary(modelDescriptor)) {
        continue;
      }
      if (SModelStereotype.isDescriptorModel(modelDescriptor)) {
        // for now, don't expose solution@descriptor.
        // Reveal once there's an option to hide/expose these and there's runtime class generated from them.
        continue;
      }

      if (SModelStereotype.isStubModel(modelDescriptor)) {
        stubs.add(modelDescriptor);
      } else if (SModelStereotype.isTestModel(modelDescriptor)) {
        tests.add(modelDescriptor);
      } else {
        regularModels.add(modelDescriptor);
      }
    }
    if (!regularModels.isEmpty()) {
      new SModelsSubtree(this).create(regularModels);
    }
    if (!tests.isEmpty()) {
      TestsTreeNode testsNode = new TestsTreeNode();
      new SModelsSubtree(testsNode).create(tests);
      add(testsNode);
    }
    if (!stubs.isEmpty()) {
      StubsTreeNode stubsNode = new StubsTreeNode();
      new SModelsSubtree(stubsNode).create(stubs);
      add(stubsNode);
    }
  }

}
