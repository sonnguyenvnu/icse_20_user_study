/*
 * Copyright 2003-2013 JetBrains s.r.o.
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

package jetbrains.mps.idea.core.projectView;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ViewSettings;
import com.intellij.ide.projectView.impl.nodes.BasePsiNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.FileStatusManager;
import com.intellij.openapi.vfs.VirtualFile;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.psi.impl.MPSPsiNodeBase;
import jetbrains.mps.idea.core.psi.impl.MPSPsiRootNode;
import jetbrains.mps.nodefs.MPSNodeVirtualFile;
import jetbrains.mps.nodefs.NodeVirtualFileSystem;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.awt.Color;
import java.util.Collection;

/**
 * User: fyodor
 * Date: 2/28/13
 */
public class MPSPsiElementTreeNode extends BasePsiNode<MPSPsiRootNode> {

  protected MPSPsiElementTreeNode(Project project, MPSPsiRootNode value, ViewSettings viewSettings) {
    super(project, value, viewSettings);
  }

  @Nullable
  @Override
  public String getTestPresentation() {
    return extractPsiFromValue().getName();
  }

  @Nullable
  @Override
  protected MPSPsiNodeBase extractPsiFromValue() {
    return getValue();
  }

  @Nullable
  @Override
  protected Collection<AbstractTreeNode> getChildrenImpl() {
    return null;
  }

  @Override
  public void navigate(boolean requestFocus) {
    SNodeReference rootNodeRef = getValue().getSNodeReference();
    MPSNodeVirtualFile nodeVirtualFile = NodeVirtualFileSystem.getInstance().getFileFor(ProjectHelper.getProjectRepository(getProject()), rootNodeRef);
    FileEditorManager.getInstance(getProject()).openFile(nodeVirtualFile, requestFocus);
  }

  @Override
  protected void updateImpl(PresentationData presentation) {
    MPSPsiNodeBase psiNodeBase = extractPsiFromValue();
    presentation.setPresentableText(psiNodeBase.getName());
    presentation.setIcon(psiNodeBase.getIcon(getIconableFlags()));
    Color statusColor = getNodeColor(psiNodeBase);
    presentation.setForcedTextForeground(statusColor);
  }

  private Color getNodeColor(MPSPsiNodeBase psiNodeBase) {
    //TODO: implement getVirtualFile()?
    final FileStatusManager fileStatusManager = FileStatusManager.getInstance(myProject);
    VirtualFile virtualFile = null;
    if (fileStatusManager != null && psiNodeBase instanceof MPSPsiRootNode
      && (virtualFile = ((MPSPsiRootNode)psiNodeBase).getVirtualFile()) != null) {
      return fileStatusManager.getStatus(virtualFile).getColor();
    }
    return EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground();
  }

}
