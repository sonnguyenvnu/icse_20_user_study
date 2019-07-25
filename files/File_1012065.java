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
package jetbrains.mps.ide.ui.tree.module;

import com.intellij.icons.AllIcons.Nodes;
import jetbrains.mps.VisibleModuleRegistry;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.project.DevKit;
import jetbrains.mps.project.GlobalScope;
import jetbrains.mps.project.Project;
import jetbrains.mps.project.Solution;
import jetbrains.mps.project.structure.ProjectStructureModule;
import jetbrains.mps.scope.ConditionalScope;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.util.IterableUtil;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.mps.openapi.module.SModule;

import java.util.List;

public class ProjectModulesPoolTreeNode extends TextTreeNode {
  private Project myProject;
  private boolean myInitialized;

  public ProjectModulesPoolTreeNode(Project project) {
    super("Modules Pool");
    myProject = project;

    setIcon(Nodes.ModuleGroup);
  }

  @Override
  public boolean isLoadingEnabled() {
    return true;
  }

  @Override
  public boolean isInitialized() {
    return myInitialized;
  }

  @Override
  protected boolean propogateErrorUpwards() {
    return false;
  }

  @Override
  protected void doInit() {
    populate();
    myInitialized = true;
  }

  private void populate() {
    final VisibleModuleRegistry visibleModules = VisibleModuleRegistry.getInstance();
    // no condition for models as we are not going to ask anything but modules from the scope
    // XXX I wonder if there's true need to respect 'visibility' status for the pool
    // XXX Why it's project's repository, and not the one with 'deployed' modules only? On one hand, it's convenient to see
    //     whole project scope, OTOH, non-deployed project modules are not the same as deployed modules, and why do we see both under 'pool' then?
    final ConditionalScope scope = new ConditionalScope(new GlobalScope(myProject.getRepository()), visibleModules::isVisible, null);
    List<SModule> modules = IterableUtil.asList(scope.getModules());
    {
      ModulePoolNamespaceBuilder builder = new ModulePoolNamespaceBuilder();
      TextTreeNode solutions = new TextTreeNode("Solutions");
      for (SModule s : modules) {
        if (s instanceof Solution || s instanceof ProjectStructureModule) {
          builder.addNode(ProjectModuleTreeNode.createFor(myProject, s, true));
        }
      }
      builder.fillNode(solutions);
      add(solutions);
    }

    {
      ModulePoolNamespaceBuilder builder = new ModulePoolNamespaceBuilder();
      TextTreeNode languages = new TextTreeNode("Languages");
      for (SModule m : modules) {
        if (m instanceof Language) {
          builder.addNode(ProjectModuleTreeNode.createFor(myProject, m, true));
        }
        // Provided language module shows its *owned* generators, would be great to show language's associated
        // generators here. Just need an easy way to tell owned from standalone.
//        else if (m instanceof Generator) {
//          builder.addNode(ProjectModuleTreeNode.createFor(myProject, m, true));
//        }
      }
      builder.fillNode(languages);
      add(languages);
    }

    {
      ModulePoolNamespaceBuilder builder = new ModulePoolNamespaceBuilder();
      TextTreeNode devkits = new TextTreeNode("DevKits");
      for (SModule m : modules) {
        if (m instanceof DevKit) {
          builder.addNode(ProjectModuleTreeNode.createFor(myProject, m, true));
        }
      }
      builder.fillNode(devkits);
      add(devkits);
    }
  }

  @Override
  public boolean isLeaf() {
    return false;
  }

  private class ModulePoolNamespaceBuilder extends DefaultNamespaceTreeBuilder<ProjectModuleTreeNode> {
    @Override
    protected String getNamespace(ProjectModuleTreeNode node) {
      String fqName = node.getModule().getModuleName();
      if (node.getModule() instanceof Generator && fqName.indexOf('#') > 0) {
        fqName = fqName.substring(0, fqName.indexOf('#'));
      }

      return NameUtil.namespaceFromLongName(fqName);
    }
  }
}
