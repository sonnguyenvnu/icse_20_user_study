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
package jetbrains.mps.workbench.structureview;

import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import jetbrains.mps.ide.editor.NodeStructureViewProvider;
import jetbrains.mps.plugins.projectplugins.ProjectPluginManager;
import jetbrains.mps.plugins.relations.RelationDescriptor;
import jetbrains.mps.project.MPSProject;
import jetbrains.mps.nodefs.MPSNodeVirtualFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.ArrayList;
import java.util.List;

public class NodeStructureViewProviderImpl implements ApplicationComponent, NodeStructureViewProvider {
  private static final Logger LOG = LogManager.getLogger(NodeStructureViewProviderImpl.class);
  public NodeStructureViewProviderImpl() {
  }

  public StructureViewBuilder create(MPSProject mpsProject, SNodeReference np) {
    mpsProject.getModelAccess().checkReadAccess();

    SNode node = np.resolve(mpsProject.getRepository());

    List<RelationDescriptor> tabs = new ArrayList<>();
    for (RelationDescriptor tab : mpsProject.getProject().getComponent(ProjectPluginManager.class).getTabDescriptors()) {
      try {
        if (tab.getBaseNode(node) != null || tab.isApplicable(node)) {
          tabs.add(tab);
        }
      } catch (Throwable t){
        LOG.error("Exception in extension: ", t);
      }
    }

    for (RelationDescriptor tab : tabs) {
      SNode baseNode = null;
      try {
        baseNode = tab.getBaseNode(node);
      } catch (Throwable t){
        LOG.error("Exception in extension: ", t);
      }

      if (baseNode != null && baseNode.getName() != null) {
        return new NodeStructureViewBuilder(mpsProject, baseNode.getReference());
      }
    }

    for (RelationDescriptor tab : tabs) {
      List<SNode> nodes = null;
      try {
        nodes = tab.getNodes(node);
      } catch (Throwable t){
        LOG.error("Exception in extension: ", t);
      }
      if (node != null && !nodes.isEmpty()) {
        return new NodeStructureViewBuilder(mpsProject, new jetbrains.mps.smodel.SNodePointer(node));
      }
    }

    return null;
  }

  @Override
  public StructureViewBuilder getStructureViewBuilder(@NotNull MPSNodeVirtualFile file, @NotNull Project project) {
    SNodeReference nodePointer = file.getSNodePointer();
    return create(project.getComponent(MPSProject.class), nodePointer);
  }

  @Override
  public void initComponent() {
    // TODO move to plugin.xml
    Extensions.getArea(null).getExtensionPoint(NodeStructureViewProvider.EP_NODE_STRUCTURE_VIEW_PROVIDER).registerExtension(this);
  }

  @Override
  public void disposeComponent() {
    Extensions.getArea(null).getExtensionPoint(NodeStructureViewProvider.EP_NODE_STRUCTURE_VIEW_PROVIDER).unregisterExtension(this);
  }

  @Override
  @NotNull
  public String getComponentName() {
    return NodeStructureViewProviderImpl.class.getName();
  }
}
