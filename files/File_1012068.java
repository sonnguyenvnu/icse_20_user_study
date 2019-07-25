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

import jetbrains.mps.generator.TransientModelsModule;
import jetbrains.mps.generator.TransientModelsModule.TransientSModelDescriptor;
import jetbrains.mps.icons.MPSIcons.Nodes;
import jetbrains.mps.ide.ui.tree.SortUtil.SModelComparator;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode.LongModelNameText;
import jetbrains.mps.project.Project;
import jetbrains.mps.util.IterableUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;

import java.util.ArrayList;
import java.util.Collections;

public class TransientModelsTreeNode extends ProjectModuleTreeNode {

  public TransientModelsTreeNode(@NotNull Project project, @NotNull TransientModelsModule module) {
    super(module);
    setNodeIdentifier(module.getModuleReference().toString());
    populate();
  }

  @Override
  protected void doUpdatePresentation() {
    super.doUpdatePresentation();
    setIcon(Nodes.TransientModule);
  }

  @Override
  public String getModuleText() {
    String name = getModule().getModuleName();

    if (name != null) {
      return name;
    }
    return "transient";
  }

  @NotNull
  @Override
  public TransientModelsModule getModule() {
    return (TransientModelsModule) super.getModule();
  }

  private void populate() {
    final LongModelNameText fullModelName = new LongModelNameText();
    GroupByForkBranchNamespaceBuilder treeBuilder = new GroupByForkBranchNamespaceBuilder();
    ArrayList<SModel> sortedModels = new ArrayList<>(IterableUtil.asCollection(getModule().getModels()));
    Collections.sort(sortedModels, new SModelComparator());
    sortedModels.stream().map(m -> new SModelTreeNode(m, fullModelName)).forEach(treeBuilder::addNode);
    treeBuilder.fillNode(this);
  }

  // FIXME refactor NamespaceTreeBuilder to take Supplier and get rid of subclassing
  static final class GroupByForkBranchNamespaceBuilder extends DefaultNamespaceTreeBuilder<SModelTreeNode> {
    @Override
    protected String getNamespace(SModelTreeNode node) {
      final SModel m = node.getModel();
      // FIXME I know I'm not supposed to use TransientSModelDescriptor directly, rather extapi.TransientSModel
      //       but there's no mechanism to access serial through that interface yet
      final int branchSerial;
      if (m instanceof TransientSModelDescriptor) {
        branchSerial = ((TransientSModelDescriptor) m).getBranchSerial();
      } else {
        branchSerial = 0;
      }
      return branchSerial == 0 ? "" : String.format("Transformation branch #%d", branchSerial);
    }
  }
}
