/*
 * Copyright 2003-2012 JetBrains s.r.o.
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

package jetbrains.mps.idea.core.usages;

import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.usages.TextChunk;
import com.intellij.usages.Usage;
import com.intellij.usages.UsagePresentation;
import com.intellij.usages.rules.MergeableUsage;
import com.intellij.usages.rules.UsageInModule;
import jetbrains.mps.ide.editor.MPSFileNodeEditor;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.usages.rules.UsageByCategory;
import jetbrains.mps.idea.core.usages.rules.UsageInMPS;
import jetbrains.mps.idea.core.usages.rules.UsageInModel;
import jetbrains.mps.idea.core.usages.rules.UsageInRoot;
import org.jetbrains.mps.openapi.model.SNode;import org.jetbrains.mps.openapi.model.SNodeId;import org.jetbrains.mps.openapi.model.SNodeReference;import org.jetbrains.mps.openapi.model.SReference;import org.jetbrains.mps.openapi.model.SModelId;import org.jetbrains.mps.openapi.model.SModel;import org.jetbrains.mps.openapi.model.SModel;import org.jetbrains.mps.openapi.model.SModelReference;import jetbrains.mps.smodel.*;
import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SRepository;

import javax.swing.Icon;
import java.util.ArrayList;

public class NodeUsage extends NodeNavigatable implements Usage, UsagePresentation, UsageInMPS, UsageInModule, MergeableUsage, UsageInRoot, UsageInModel, UsageByCategory, Comparable<NodeUsage> {
  private SModelReference myModel;
  private TextChunk[] myChunks;
  private boolean myIsValid;
  private String myParentPresentation;
  private String myRole;
  private String myCategory;

  public NodeUsage(@NotNull SNodeReference node, @NotNull Project project, String category) {
    super(node, project);
    SRepository repository = ProjectHelper.getProjectRepository(project);
    repository.getModelAccess().runReadAction(new Runnable() {
      @Override
      public void run() {
        myModel = myNode.getModelReference();
        SNode targetNode = myNode.resolve(repository);
        if (targetNode != null) {
          myParentPresentation = targetNode.getParent().getPresentation();
          myRole = targetNode.getRoleInParent();
        }
      }
    });
    myIsValid = isValid();
    myChunks = initChunks();
    myCategory = category;
  }

  @NotNull
  @Override
  public UsagePresentation getPresentation() {
    return this;
  }


  @Override
  public boolean isReadOnly() {
    return false;
  }

  @Override
  public FileEditorLocation getLocation() {
    VirtualFile virtualFile = getFile();
    if (virtualFile == null) return null;
    FileEditor editor = FileEditorManager.getInstance(myProject).getSelectedEditor(virtualFile);
    if (!(editor instanceof MPSFileNodeEditor)) return null;
    return editor.getCurrentLocation();
  }


  @Override
  public void selectInEditor() {

  }

  @Override
  public void highlightInEditor() {

  }


  @NotNull
  @Override
  public TextChunk[] getText() {
    final boolean currentIsValid = isValid();
    if (myIsValid != currentIsValid) {
      myIsValid = isValid();
      myChunks = initChunks();
    }
    return myChunks;
  }

  private TextChunk[] initChunks() {
    final ArrayList<TextChunk> result = new ArrayList<TextChunk>();
    TextAttributes attributes;
    if (myIsValid) {
      attributes = SimpleTextAttributes.SIMPLE_CELL_ATTRIBUTES.toTextAttributes();
    } else {
      attributes = SimpleTextAttributes.GRAY_ITALIC_ATTRIBUTES.toTextAttributes();
    }
    result.add(new TextChunk(attributes, myTextPresentation));
    result.add(new TextChunk(attributes, " ("));
    result.add(new TextChunk(attributes, "role: "));
    result.add(new TextChunk(attributes, myRole));
    result.add(new TextChunk(attributes, ";"));
    result.add(new TextChunk(attributes, " in: "));
    result.add(new TextChunk(attributes, myParentPresentation));
    result.add(new TextChunk(attributes, ")"));
    return result.toArray(new TextChunk[result.size()]);
  }

  @NotNull
  @Override
  public String getPlainText() {
    return myTextPresentation;
  }

  @Override
  public Icon getIcon() {
    return myItemPresentation.getIcon(false);
  }

  @Override
  public String getTooltipText() {
    return null;
  }

  @Override
  public Module getModule() {
    if (!isValid()) return null;
    VirtualFile virtualFile = getFile().getParent();
    if (virtualFile == null) return null;
    return ModuleUtil.findModuleForFile(virtualFile, myProject);
  }

  @Override
  public boolean merge(MergeableUsage mergeableUsage) {
    return mergeableUsage instanceof NodeUsage && myNode.equals(((NodeUsage) mergeableUsage).myNode);
  }

  @Override
  public void reset() {
  }

  @Override
  public boolean isValid() {
    SRepository repository = ProjectHelper.getProjectRepository(myProject);
    return new ModelAccessHelper(repository.getModelAccess()).runReadAction(new Computable<Boolean>() {
      @Override
      public Boolean compute() {
        SNode node = myNode.resolve(repository);
        return node != null && !(node.getModel() == null);
      }
    });
  }

  @Override
  public VirtualFile getFile() {
    return myFile;
  }

  @Override
  public SModel getModel() {
    return myModel.resolve(ProjectHelper.getProjectRepository(myProject));
  }

  @Override
  public SNodeReference getRoot() {
    return myRootNode;
  }

  @Override
  public String getCategory() {
    return myCategory;
  }

  @Override
  public int compareTo(NodeUsage usage) {
    VirtualFile myFile = getFile();
    VirtualFile hisFile = usage.getFile();
    if (!myFile.equals(hisFile)) {
      return myFile.getPresentableUrl().compareTo(hisFile.getPresentableUrl());
    }
    return 0;
  }
}
