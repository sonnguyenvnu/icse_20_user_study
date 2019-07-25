@Override public void navigate(boolean requestFocus){
  SNodeReference rootNodeRef=getValue().getSNodeReference();
  MPSNodeVirtualFile nodeVirtualFile=NodeVirtualFileSystem.getInstance().getFileFor(ProjectHelper.getProjectRepository(getProject()),rootNodeRef);
  FileEditorManager.getInstance(getProject()).openFile(nodeVirtualFile,requestFocus);
}
