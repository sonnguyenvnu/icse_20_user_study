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

package jetbrains.mps.idea.core.psi.impl;

import com.intellij.lang.FileASTNode;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiBinaryFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.util.IncorrectOperationException;
import jetbrains.mps.extapi.persistence.FileDataSource;
import jetbrains.mps.fileTypes.MPSFileTypeFactory;
import jetbrains.mps.icons.MPSIcons.Nodes;
import jetbrains.mps.ide.icons.GlobalIconManager;
import jetbrains.mps.ide.icons.IdeIcons;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import jetbrains.mps.idea.core.projectView.edit.SNodeDeleteProvider;
import jetbrains.mps.idea.core.psi.MPSNodeFileViewProvider;
import jetbrains.mps.nodefs.MPSNodeVirtualFile;
import jetbrains.mps.nodefs.NodeVirtualFileSystem;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.SNodePointer;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.DataSource;

import javax.swing.Icon;
import java.util.Collections;

/**
 * User: fyodor
 * Date: 3/5/13
 */
public class MPSPsiRootNode extends MPSPsiNodeBase implements PsiFile, PsiBinaryFile, MPSPsiRealNode {
  private final FileViewProvider myViewProvider;
  private final SNodeId myNodeId;
  private final String myName;
  @NotNull
  private final MPSPsiModel myModel;
  private VirtualFile mySeparateFile;

  public MPSPsiRootNode(SNodeReference nodeRef, @NotNull String name, @NotNull MPSPsiModel containingModel, PsiManager manager) {
    super(manager);
    myNodeId = nodeRef.getNodeId();
    myModel = containingModel;
    myName = name;
    MPSNodeVirtualFile nodeFile = NodeVirtualFileSystem.getInstance().getFileFor(ProjectHelper.getProjectRepository(manager.getProject()), nodeRef);
    myViewProvider = new MPSNodeFileViewProvider(manager, nodeFile);
  }

  public MPSPsiRootNode(SNodeReference nodeRef, @NotNull String name, @NotNull MPSPsiModel containingModel, PsiManager manager, @NotNull VirtualFile virtualFile) {
    this(nodeRef, name, containingModel, manager);
    mySeparateFile = virtualFile;
  }

  @Override
  protected Icon getBaseIcon() {
    SRepository repository = ProjectHelper.getProjectRepository(getProject());
    return new ModelAccessHelper(repository.getModelAccess()).runReadAction(() -> {
      final SNode node = getSNodeReference().resolve(repository);
      if (node == null) return IdeIcons.UNKNOWN_ICON;
      final GlobalIconManager globalIconManager = GlobalIconManager.getInstance();
      return globalIconManager == null ? Nodes.Node : globalIconManager.getIconFor(node);
    });
  }

  @Nullable
  @Override
  protected Icon getElementIcon(@IconFlags int flags) {
    return getBaseIcon();
  }

  @Override
  public boolean isValid() {
    if (!myModel.isValid() || mySeparateFile != null && !mySeparateFile.isValid()) return false;
    final SRepository repository = getProjectRepository();
    final Ref<Boolean> result = new Ref<>(false);
    final SNodeReference nodeRef = getSNodeReference();
    if (nodeRef == null) return false;

    repository.getModelAccess().runReadAction(() -> {
      SNode node = nodeRef.resolve(repository);
      result.set(node != null);
    });

    return result.get();
  }

  @Nullable
  @Override
  public VirtualFile getVirtualFile() {
//    return MPSNodesVirtualFileSystem.getInstance().getFileFor(getSNodeReference());
    if (mySeparateFile != null) {
      return mySeparateFile;
    }
    final SRepository repository = getProjectRepository();
    return new ModelAccessHelper(repository.getModelAccess()).runReadAction(() -> {
      SModel sModel = myModel.getSModelReference().resolve(repository);
      DataSource dataSource = sModel.getSource();
      if (dataSource instanceof FileDataSource) {
        return VirtualFileUtils.getProjectVirtualFile(((FileDataSource) dataSource).getFile());
      }
      return null;
    });
  }

  @Override
  public boolean processChildren(PsiElementProcessor<PsiFileSystemItem> processor) {
    return false;
  }

  @Override
  public MPSPsiModel getContainingModel() {
    return myModel;
  }

  @Nullable
  @Override
  public PsiDirectory getContainingDirectory() {
    return getContainingModel();
  }

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Nullable
  @Override
  public PsiDirectory getParent() {
    return getContainingModel();
  }

  @Override
  public String toString() {
    return "Root: " + myNodeId.toString();
  }

  @Override
  public long getModificationStamp() {
    VirtualFile file = getVirtualFile();
    return file != null ? file.getModificationStamp() : -1;
  }

  @NotNull
  @Override
  public PsiFile getContainingFile() {
    // PsiFileImpl does the same
    return this;
  }

  @NotNull
  @Override
  public PsiFile getOriginalFile() {
    return this;
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return MPSFileTypeFactory.MPS_NODE_FILE_TYPE;
  }

  @NotNull
  @Override
  public String getName() {
    return myName;
  }

  @NotNull
  @Override
  public PsiFile[] getPsiRoots() {
    return new PsiFile[]{this};
  }

  @NotNull
  @Override
  public FileViewProvider getViewProvider() {
    return myViewProvider;
  }

  @Override
  public FileASTNode getNode() {
    return null;
  }

  @Override
  public void navigate(final boolean requestFocus) {
    // XXX not clear why we keep node id and use myModel.getSModelReference when there's SNodeReference in cons
    new EditorNavigator(ProjectHelper.fromIdeaProject(getProject())).shallFocus(requestFocus).open(new SNodePointer(myModel.getSModelReference(), myNodeId));
  }

  @Override
  public boolean isPhysical() {
    // Honestly check that file is physical - per root RootNode will return true
    return this.getVirtualFile() != null && !this.getVirtualFile().equals(myModel.getSourceVirtualFile());
  }

  @Override
  public void subtreeChanged() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void checkSetName(String name) throws IncorrectOperationException {
    throw new IncorrectOperationException("Not implemented");
  }

  @Override
  public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
    throw new IncorrectOperationException("Not implemented");
  }

  public SNodeId getNodeId() {
    return myNodeId;
  }

  @Override
  public SNodeReference getSNodeReference() {
    MPSPsiModel psiModel = getContainingModel();
    if (psiModel == null) return null;
    return new SNodePointer(psiModel.getSModelReference(), myNodeId);
  }

  // added for idea search scope to work with our virtual files
  // see PsiSearchScopeUtil.isInScope
  // The main thing is: return some physical real file that lives well with idea's search scopes
  @Override
  public PsiElement getContext() {
    VirtualFile vFile = mySeparateFile != null ?
      mySeparateFile :
      myModel.getSourceVirtualFile();
    // TEMP FIX: Guarding vFile == null
    // model.getSourceFile() gives us null in case model is in a jar file, because its
    // source.getFile() returns not a virtual file, but a JarEntryFile
    // Proper solution, i think: exclude them from indexing by MPSFQNameJavaClassIndex and the like
    // Came here from MPSJavaClassFinder
    return vFile != null && vFile.isValid() ? PsiManager.getInstance(getProject()).findFile(vFile) : null;
  }

  @Override
  public boolean isWritable() {
    return true;
  }

  @Override
  public String getText() {
    return null;
  }

  @Override
  public void delete() throws IncorrectOperationException {
    SNodeDeleteProvider deleteProvider = new SNodeDeleteProvider(
      Collections.singletonList(getSNodeReference()),
      ProjectHelper.fromIdeaProject(getProject()));
    getProjectRepository().getModelAccess().executeUndoTransparentCommand(deleteProvider);
  }
}
