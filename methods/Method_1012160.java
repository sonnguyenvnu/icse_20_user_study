@Override protected void update(FileSystemTree fileSystemTree,AnActionEvent e){
  final Presentation presentation=e.getPresentation();
  final VirtualFile path=getModulePath(e);
  presentation.setEnabled(path != null && fileSystemTree.isUnderRoots(path));
}
