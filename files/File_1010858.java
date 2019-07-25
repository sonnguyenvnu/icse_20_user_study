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

import com.intellij.ide.impl.ProjectViewSelectInTarget;
import com.intellij.ide.projectView.impl.ProjectViewPane;
import com.intellij.lang.FileASTNode;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.PsiManagerEx;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.psi.impl.file.impl.FileManager;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.containers.BidirectionalMap;
import jetbrains.mps.extapi.persistence.FileDataSource;
import jetbrains.mps.icons.MPSIcons.Nodes;
import jetbrains.mps.ide.icons.GlobalIconManager;
import jetbrains.mps.ide.vfs.VirtualFileUtils;
import jetbrains.mps.idea.core.MPSBundle;
import jetbrains.mps.nodefs.NodeVirtualFileSystem;
import jetbrains.mps.persistence.FilePerRootDataSource;
import jetbrains.mps.project.MPSExtentions;
import jetbrains.mps.smodel.DynamicReference;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.StaticReference;
import jetbrains.mps.util.JavaNameUtil;
import jetbrains.mps.vfs.IFile;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SAbstractLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.DataSource;

import javax.swing.Icon;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * evgeny, 1/25/13
 */
public class MPSPsiModel extends MPSPsiNodeBase implements PsiDirectory {

  private static final Logger LOG = Logger.getLogger(MPSPsiModel.class);

  private static final PsiDirectory[] EMPTY_PSI_DIRECTORIES = new PsiDirectory[0];
  private final SModelReference myModelReference;
  private final Map<SNodeId, MPSPsiNode> myNodes = new HashMap<>();
  private final BidirectionalMap<MPSPsiNodeBase, Integer> myNodesOrder = new BidirectionalMap<>();
  private VirtualFile mySourceVirtualFile;
  private PsiDirectoryImpl myPsiDirectory;

  public MPSPsiModel(SModelReference reference, PsiManager manager) {
    super(manager);
    this.myModelReference = reference;
  }

  @Override
  public boolean equals(Object obj) {
    /* TODO: remove after fix in platform:
    This override fixes check for equality of SmartPointerElementInfo and MPSPsiModel
    in SmartPsiElementPointerImpl.createElementInfo() */
    if (obj instanceof PsiDirectory && !(obj instanceof MPSPsiNodeBase)) {
      return this.getVirtualFile().equals(((PsiDirectory) obj).getVirtualFile());
    }
    return super.equals(obj);
  }

  @Override
  protected Icon getBaseIcon() {
    final GlobalIconManager globalIconManager = GlobalIconManager.getInstance();
    if (globalIconManager == null) {
      return Nodes.Model;
    }
    return new ModelAccessHelper(getProjectRepository()).runReadAction(() -> globalIconManager.getIconFor(myModelReference.resolve(getProjectRepository())));
  }

  @Nullable
  @Override
  public Icon getIcon(int flags) {
    return getBaseIcon();
  }

  @Nullable
  @Override
  protected Icon getElementIcon(@IconFlags int flags) {
    return getBaseIcon();
  }

  @NotNull
  @Override
  public String getName() {
    return JavaNameUtil.shortName(getQualifiedName());
  }

  public SModelReference getSModelReference() {
    return myModelReference;
  }

  @Override
  public boolean isValid() {
    if (myPsiDirectory == null || !(myPsiDirectory.isValid())) return false;
    final SRepository repository = getProjectRepository();
    final Ref<Boolean> result = new Ref<>(false);

    repository.getModelAccess().runReadAction(() -> {
      SModel model = myModelReference.resolve(repository);
      result.set(model != null);
    });

    return result.get();
  }

  @Override
  public boolean isPhysical() {
    // See SmartPsiElementPointerImpl.doCreateElementInfo() and LOG.error() in createElementInfo() in the same class
    // We implement PsiDirectory but don't want DirElementInfo to be created for us, because when it's restored
    // it creates not an MPSPsiModel obviously but a PsiDirectoryFactory.getInstance().findDirectory() which happens
    // to be PsiDirectoryImpl or something. Hence, LOG.error() is called.
    // It affects project pane tests.
    //
    // Currently waiting for the change element instanceof PsiDirectory && element.isPhysical() to happen in idea code
    return false;
  }


  @Override
  public String toString() {
    return "Model:" + myModelReference.toString();
  }

  public MPSPsiNode[] getRootNodes() {
    return getRootNodesOfType(MPSPsiNode.class);
  }

  public <T extends PsiElement> T[] getRootNodesOfType(Class<T> aClass) {
    PsiElement[] surrogateRoots = getChildren();
    List<T> result = new ArrayList<>();
    for (PsiElement r : surrogateRoots) {
      assert r instanceof MPSPsiRootNode; // a layer between model and real root
      for (PsiElement c : r.getChildren()) {
        if (aClass.isInstance(c)) {
          result.add((T) c);
        }
      }
    }
    return ArrayUtil.toObjectArray(result, aClass);
  }

  /* PsiFile */

  @Override
  public void checkSetName(String name) throws IncorrectOperationException {
    throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.not.implemented"));
  }

  @NotNull
  @Override
  public VirtualFile getVirtualFile() {
    SRepository repository = getProjectRepository();
    NodeVirtualFileSystem fs = NodeVirtualFileSystem.getInstance();
    return new ModelAccessHelper(repository).runReadAction(() -> fs.getFileFor(repository, myModelReference));
  }

  @Override
  public boolean processChildren(PsiElementProcessor<PsiFileSystemItem> processor) {
    return false;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  @Nullable
  @Override
  public PsiDirectory getParent() {
    return null;
  }

  @NotNull
  @Override
  public PsiDirectory[] getSubdirectories() {
    return EMPTY_PSI_DIRECTORIES;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @NotNull
  @Override
  public PsiFile[] getFiles() {
    return getChildren(PsiFile.class);
  }

  @Nullable
  @Override
  public PsiDirectory findSubdirectory(@NotNull String name) {
    return null;
  }

  @Nullable
  @Override
  public PsiFile findFile(@NotNull @NonNls String name) {
    return null;
  }

  @Override
  public final boolean isWritable() {
    return myPsiDirectory != null && myPsiDirectory.isWritable();
  }

  @NotNull
  @Override
  public PsiDirectory createSubdirectory(@NotNull String name) throws IncorrectOperationException {
    if (myPsiDirectory == null)
      throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.null.parent"));
    return myPsiDirectory.createSubdirectory(name);
  }

  @Override
  public void checkCreateSubdirectory(@NotNull String name) throws IncorrectOperationException {
    if (myPsiDirectory == null)
      throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.null.parent"));
    myPsiDirectory.checkCreateSubdirectory(name);
  }

  @NotNull
  @Override
  public PsiFile createFile(@NotNull @NonNls String name) throws IncorrectOperationException {
    if (myPsiDirectory == null)
      throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.null.parent"));
    return myPsiDirectory.createFile(name);
  }

  @NotNull
  @Override
  public PsiFile copyFileFrom(@NotNull String newName, @NotNull PsiFile originalFile) throws IncorrectOperationException {
    throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.not.implemented"));
  }

  @Override
  public void checkCreateFile(@NotNull String name) throws IncorrectOperationException {
    if (myPsiDirectory == null)
      throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.null.parent"));
    myPsiDirectory.checkCreateFile(name);
  }

  @Override
  public PsiElement add(@NotNull PsiElement element) throws IncorrectOperationException {
    if (myPsiDirectory == null)
      throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.null.parent"));
    return myPsiDirectory.add(element);
  }

  @Override
  public void checkAdd(@NotNull PsiElement element) throws IncorrectOperationException {
    if (myPsiDirectory == null)
      throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.null.parent"));
    myPsiDirectory.checkAdd(element);
  }

  @Override
  public FileASTNode getNode() {
    return null;
  }

  @NotNull
  @Override
  public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
    throw new IncorrectOperationException(MPSBundle.message("mps.psi.model.message.not.implemented"));
  }

  @Nullable
  @Override
  public PsiDirectory getParentDirectory() {
    VirtualFile parentFile = getSourceVirtualFile().getParent();
    if (parentFile == null) return null;
    return myManager.findDirectory(parentFile);
  }

  @Override
  public boolean canNavigateToSource() {
    return false;
  }

  @Override
  public boolean canNavigate() {
    return true;
  }

  @Override
  public void navigate(boolean requestFocus) {
    ProjectViewSelectInTarget.select(getProject(), this, ProjectViewPane.ID, null, getSourceVirtualFile(), requestFocus);
  }

  public String getQualifiedName() {
    return myModelReference.getModelName();
  }

  public VirtualFile getSourceVirtualFile() {
    if (mySourceVirtualFile == null) {
      final SRepository repo = getProjectRepository();
      repo.getModelAccess().runReadAction(() -> {
        SModel model = myModelReference.resolve(repo);
        // fixme when DefaultModelRoot.createModel() doesn't do register() anymore, it shouldn't be needed
        // happens in tests for creating file-per-root persisted model in a directory, when no languages is chosen
        // i.e. creation of model is cancelled
        if (model == null) {
          return;
        }
        DataSource source = model.getSource();
        if (source instanceof FileDataSource) {
          mySourceVirtualFile = VirtualFileUtils.getProjectVirtualFile(((FileDataSource) source).getFile());
        } else if (source instanceof FilePerRootDataSource) {
          // todo remove knowledge about particular PerRoot persistence, should be more generic
          mySourceVirtualFile = VirtualFileUtils.getProjectVirtualFile(((FilePerRootDataSource) source).getFolder()).findChild(MPSExtentions.DOT_MODEL_HEADER);
        }
      });
    }
    return mySourceVirtualFile;
  }

  public DataSource getSource() {
    SRepository repository = getProjectRepository();
    return new ModelAccessHelper(repository.getModelAccess()).runReadAction(() -> {
      SModel model = myModelReference.resolve(repository);
      return model.getSource();
    });
  }

  /* package */

  boolean isRoot(MPSPsiNode psiNode) {
    return psiNode.getParent() instanceof MPSPsiRootNode;
  }

  MPSPsiNode reload(SNodeId sNodeId) {
    SRepository repository = getProjectRepository();
    repository.getModelAccess().checkWriteAccess();
    MPSPsiNode mpsPsiNode = lookupNode(sNodeId);
    if (mpsPsiNode == null) {
      return null;
    }

    SNode sNode = mpsPsiNode.getSNodeReference().resolve(repository);
    MPSPsiNode replacement = convert(sNode);

    if (isRoot(mpsPsiNode)) {
      MPSPsiRootNode rootNode = (MPSPsiRootNode) mpsPsiNode.getParent();
      assert rootNode.getContainingModel().equals(this);

      MPSPsiRootNode replacementRoot;
      if (sNode.getContainingRoot() == sNode && sNode.getModel().getSource() instanceof FilePerRootDataSource) {
        final String name = extractName(sNode);
        final VirtualFile virtualFile = VirtualFileUtils.getProjectVirtualFile(((FilePerRootDataSource) sNode.getModel().getSource()).getFile(name + MPSExtentions.DOT_MODEL_ROOT));
        replacementRoot = new MPSPsiRootNode(sNode.getReference(), name, this, getManager(), virtualFile);
      } else {
        replacementRoot = new MPSPsiRootNode(sNode.getReference(), extractName(sNode), this, getManager());
      }
      replaceChild(rootNode, replacementRoot);
      ((PsiManagerEx) getManager()).getFileManager().setViewProvider(rootNode.getVirtualFile(), null);
      replacementRoot.addChildLast(replacement);
    } else {
      ((MPSPsiNodeBase) mpsPsiNode.getParent()).replaceChild(mpsPsiNode, replacement);
    }

    enumerateNodes();

    return replacement;
  }

  public void reloadAll() {
    final SRepository repository = getProjectRepository();
    repository.getModelAccess().checkReadAccess();
    SModel sModel = myModelReference.resolve(repository);
    for (SNode root : sModel.getRootNodes()) {
      MPSPsiNode mpsPsiNode = lookupNode(root.getNodeId());
      if (mpsPsiNode == null) continue;
      drop(mpsPsiNode);
    }
    myNodes.clear();
    reload(sModel);
  }

  private PsiFile tryReuseRootPsiFile(VirtualFile vfile) {
    // It's important that we only try to take cached psiFile. Otherwise we could end up creating psiFile
    // for this virtual file, which would require psiModel, but we're in the process of reloading it.
    FileManager fileManager = ((PsiManagerEx) PsiManagerEx.getInstance(getProject())).getFileManager();
    return fileManager.getCachedPsiFile(vfile);
  }

  void reload(SModel model) {
    clearChildren();
    for (SNode root : model.getRootNodes()) {
      String rootName;
      rootName = extractName(root);
      MPSPsiRootNode rootNode;
      if (model.getSource() instanceof FilePerRootDataSource) {
        final IFile iFile = ((FilePerRootDataSource) model.getSource()).getFile(rootName + MPSExtentions.DOT_MODEL_ROOT);
        VirtualFile virtualFile = VirtualFileUtils.getProjectVirtualFile(iFile);
        if (virtualFile == null) virtualFile = VirtualFileUtils.getVirtualFile(iFile.getPath());
        PsiFile psiFile = virtualFile != null ? tryReuseRootPsiFile(virtualFile) : null;
        rootNode = psiFile != null && psiFile instanceof MPSPsiRootNode
          ? (MPSPsiRootNode) psiFile :
          new MPSPsiRootNode(root.getReference(), rootName, this, getManager(), virtualFile);

      } else {
        rootNode = new MPSPsiRootNode(root.getReference(), rootName, this, getManager());
      }

      addChildLast(rootNode);
      if (rootNode.getChildren().length == 0)
        rootNode.addChildLast(convert(root));
      else {
        rootNode.updateChildren();
        fillNodes(rootNode);
      }
    }

    enumerateNodes();

    getSourceVirtualFile();
    if (mySourceVirtualFile == null || mySourceVirtualFile.getParent() == null)
      myPsiDirectory = null;
    else
      myPsiDirectory = new PsiDirectoryImpl((PsiManagerImpl) myManager, getSourceVirtualFile().getParent());
      /*MPSModuleRepository.getInstance().getModelAccess().runReadAction(new Runnable() {
        @Override
        public void run() {
          mySourceVirtualFile = ModelUtil.getFileByModel(myModelReference.resolve(MPSModuleRepository.getInstance()));
        }
      });*/

  }

  private void fillNodes(MPSPsiRootNode rootNode) {
    Queue<MPSPsiNode> psiNodes = new LinkedList<>();
    for (PsiElement element : rootNode.getChildren()) {
      if (element instanceof MPSPsiNode)
        psiNodes.add((MPSPsiNode) element);
    }
    while (!psiNodes.isEmpty()) {
      MPSPsiNode mpsPsiNode = psiNodes.poll();
      myNodes.put(mpsPsiNode.getId(), mpsPsiNode);
      for (PsiElement element : mpsPsiNode.getChildren()) {
        if (element instanceof MPSPsiNode)
          psiNodes.add((MPSPsiNode) element);
      }
    }
  }

  private MPSPsiNode convert(SNode node) {
    MPSPsiNode psiNode = MPSPsiProvider.getInstance(getProject()).create(node.getNodeId(), node.getConcept(), node.getContainmentLink() == null ? null : node.getContainmentLink().getName());
    myNodes.put(node.getNodeId(), psiNode);

    // properties
    for (SProperty property : node.getProperties()) {
      psiNode.setProperty(property.getName(), node.getProperty(property));
    }

    // refs
    for (SReference ref : node.getReferences()) {
      SAbstractLink link = ref.getLink();
      SAbstractConcept linkTargetConcept = null;
      if (link != null) {
        linkTargetConcept = link.getTargetConcept();
      }
      MPSPsiRef psiRef = null;
      if (ref instanceof StaticReference) {
        psiRef = MPSPsiProvider.getInstance(getProject()).createReferenceNode(ref.getLink(), linkTargetConcept, ref.getTargetSModelReference(), ref.getTargetNodeId());
      } else if (ref instanceof DynamicReference) {
        psiRef = MPSPsiProvider.getInstance(getProject()).createReferenceNode(ref.getLink(), linkTargetConcept, ((DynamicReference) ref).getResolveInfo());
      }
      if (psiRef != null) {
        psiNode.addChild(null, psiRef);
      }
    }

    // children
    for (SNode root : node.getChildren()) {
      MPSPsiNode psiChild = convert(root);
      MPSPsiNodeBase artificialParent = psiNode.getParentFor(psiChild);
      MPSPsiNodeBase wouldBeParent = artificialParent == null ? psiNode : artificialParent;
      wouldBeParent.addChildLast(psiChild);
    }
    return psiNode;
  }

  private void drop(MPSPsiNode psiNode) {
    myNodes.remove(psiNode.getId());

    for (MPSPsiNodeBase node : psiNode.children()) {
      if (node instanceof MPSPsiNode) {
        drop((MPSPsiNode) node);
      }
    }
  }

  MPSPsiNode lookupNode(SNodeId nodeId) {
    return myNodes.get(nodeId);
  }

  Integer getNodePosition(MPSPsiNodeBase node) {
    return myNodesOrder.get(node);
  }

  public MPSPsiNodeBase findNodeByPosition(int pos) {
    List<MPSPsiNodeBase> nodes = myNodesOrder.getKeysByValue(pos);
    if (nodes == null) {
      return null;
    }
    assert nodes.size() <= 1 : "Non-unique mapping from psi nodes to their positions in the tree";
    return nodes.isEmpty() ? null : nodes.get(0);
  }

  public int getMaxNodePosition() {
    // todo just store max index in a field
    return myNodesOrder.values().stream().mapToInt(i -> i).max().orElse(0);
  }

  private String extractName(SNode sNode) {
    // Take it directly as we must not depend on whether concept is currently valid (plugin loaded or not).
    // We're persisting into files with these names
    String nameProperty = sNode.getProperty(SNodeUtil.property_INamedConcept_name);
    return nameProperty != null && !nameProperty.isEmpty() ? nameProperty : sNode.getNodeId().toString();
  }

  // todo replace with depth-first; depth is never very big in real models, and memory consumption will be less,
  // also the order will be more natural
  private void enumerateNodes() {
    myNodesOrder.clear();
    Deque<MPSPsiNodeBase> stack = new ArrayDeque<>();
    stack.add(this);

    int k = 0;

    while (!stack.isEmpty()) {
      MPSPsiNodeBase curr = stack.pop();

      // remembering position
      myNodesOrder.put(curr, k++);

      PsiElement[] children = curr.getChildren();
      for (int i = children.length - 1; i >= 0; i--) {
        PsiElement c = children[i];
        if (c instanceof MPSPsiNodeBase) {
          stack.push((MPSPsiNodeBase) c);
        }
      }

    }
  }

  @Override
  public void checkDelete() throws IncorrectOperationException {
  }

  @Override
  public void delete() throws IncorrectOperationException {
    // todo unregister model
    try {
      getSourceVirtualFile().delete(getManager());
    } catch (IOException e) {
      throw new IncorrectOperationException(e.toString(), e);
    }
  }
}
