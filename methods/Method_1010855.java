@NotNull @Override public Collection<AbstractTreeNode> modify(@NotNull final AbstractTreeNode treeNode,@NotNull final Collection<AbstractTreeNode> children,final ViewSettings settings){
  final Ref<Collection<AbstractTreeNode>> result=new Ref<>(children);
  MPSProject mpsProject=ProjectHelper.fromIdeaProject(treeNode.getProject());
  mpsProject.getModelAccess().runReadAction(new Runnable(){
    @Override public void run(){
      List<AbstractTreeNode> updatedChildren=null;
      final MPSPsiProvider mpsPsiProvider=MPSPsiProvider.getInstance(treeNode.getProject());
      FolderDataSource currentDirectoryDataSource=null;
      final SModelFileTracker modelFileTracker=SModelFileTracker.getInstance(mpsProject.getRepository());
      if (treeNode instanceof PsiDirectoryNode) {
        SModel sModel=modelFileTracker.findModel(mpsProject.getFileSystem().fromVirtualFile(((PsiDirectoryNode)treeNode).getVirtualFile()));
        if (sModel != null) {
          List<MPSPsiElementTreeNode> rootsTreeNodes=new ArrayList<>();
          for (          SNode root : sModel.getRootNodes()) {
            rootsTreeNodes.add(new MPSPsiElementTreeNode(treeNode.getProject(),(MPSPsiRootNode)mpsPsiProvider.getPsi(root).getContainingFile(),settings));
          }
          if (!rootsTreeNodes.isEmpty()) {
            updatedChildren=new ArrayList<>(children);
            updatedChildren.addAll(rootsTreeNodes);
          }
          DataSource dataSource=sModel.getSource();
          if (dataSource instanceof FolderDataSource) {
            currentDirectoryDataSource=(FolderDataSource)dataSource;
          }
        }
      }
 else       if (treeNode instanceof MPSPsiModelTreeNode) {
        MPSPsiModel psiModel=((MPSPsiModelTreeNode)treeNode).extractPsiFromValue();
        updatedChildren=new ArrayList<>();
        for (        PsiElement psiElement : psiModel.getChildren()) {
          updatedChildren.add(new MPSPsiElementTreeNode(treeNode.getProject(),(MPSPsiRootNode)psiElement,settings));
        }
      }
      for (      final AbstractTreeNode child : children) {
        if (child instanceof PsiFileNode) {
          VirtualFile vFile=((PsiFileNode)child).getVirtualFile();
          if (vFile == null) {
            continue;
          }
          final IFile modelFile=mpsProject.getFileSystem().fromVirtualFile(vFile);
          final SModel sModel=modelFileTracker.findModel(modelFile);
          if (sModel != null) {
            if (updatedChildren == null)             updatedChildren=new ArrayList<>(children);
            int idx=updatedChildren.indexOf(child);
            updatedChildren.remove(idx);
            updatedChildren.add(idx,new MPSPsiModelTreeNode(treeNode.getProject(),mpsPsiProvider.getPsi(sModel),settings));
            continue;
          }
          if (currentDirectoryDataSource != null && currentDirectoryDataSource.isIncluded(modelFile)) {
            if (updatedChildren == null)             updatedChildren=new ArrayList<>(children);
            int idx=updatedChildren.indexOf(child);
            updatedChildren.remove(idx);
          }
        }
 else         if (child instanceof PsiDirectoryNode) {
          final SModel perRootModel=modelFileTracker.findModel(mpsProject.getFileSystem().fromVirtualFile(((PsiDirectoryNode)child).getVirtualFile()));
          if (perRootModel != null) {
            if (updatedChildren == null)             updatedChildren=new ArrayList<>(children);
            int idx=updatedChildren.indexOf(child);
            updatedChildren.remove(idx);
            updatedChildren.add(idx,new PsiDirectoryNode(treeNode.getProject(),((PsiDirectoryNode)child).getValue(),settings){
              @Override public boolean canNavigate(){
                return true;
              }
              @Override public String getNavigateActionText(              boolean focusEditor){
                return MPSBundle.message("open.model.properties.action");
              }
              @Override public void navigate(              boolean requestFocus){
                MPSPropertiesConfigurable configurable=new ModelPropertiesConfigurable(perRootModel,mpsProject,true);
                final SingleConfigurableEditor dialog=new SingleConfigurableEditor(myProject,configurable);
                configurable.setParentForCallBack(dialog);
                ApplicationManager.getApplication().invokeLater(dialog::show,ModalityState.current());
              }
            }
);
          }
        }
      }
      if (updatedChildren != null) {
        result.set(updatedChildren);
      }
    }
  }
);
  return result.get();
}
