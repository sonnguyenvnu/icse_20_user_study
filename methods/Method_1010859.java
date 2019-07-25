void reload(SModel model){
  clearChildren();
  for (  SNode root : model.getRootNodes()) {
    String rootName;
    rootName=extractName(root);
    MPSPsiRootNode rootNode;
    if (model.getSource() instanceof FilePerRootDataSource) {
      final IFile iFile=((FilePerRootDataSource)model.getSource()).getFile(rootName + MPSExtentions.DOT_MODEL_ROOT);
      VirtualFile virtualFile=VirtualFileUtils.getProjectVirtualFile(iFile);
      if (virtualFile == null)       virtualFile=VirtualFileUtils.getVirtualFile(iFile.getPath());
      PsiFile psiFile=virtualFile != null ? tryReuseRootPsiFile(virtualFile) : null;
      rootNode=psiFile != null && psiFile instanceof MPSPsiRootNode ? (MPSPsiRootNode)psiFile : new MPSPsiRootNode(root.getReference(),rootName,this,getManager(),virtualFile);
    }
 else {
      rootNode=new MPSPsiRootNode(root.getReference(),rootName,this,getManager());
    }
    addChildLast(rootNode);
    if (rootNode.getChildren().length == 0)     rootNode.addChildLast(convert(root));
 else {
      rootNode.updateChildren();
      fillNodes(rootNode);
    }
  }
  enumerateNodes();
  getSourceVirtualFile();
  if (mySourceVirtualFile == null || mySourceVirtualFile.getParent() == null)   myPsiDirectory=null;
 else   myPsiDirectory=new PsiDirectoryImpl((PsiManagerImpl)myManager,getSourceVirtualFile().getParent());
}
