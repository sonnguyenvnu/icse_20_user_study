@Override protected void update(VcsContext vcsContext,Presentation presentation){
  if (vcsContext.getProject() == null) {
    presentation.setEnabled(false);
    return;
  }
  List<VirtualFile> baseDirs=getRoots(vcsContext);
  VirtualFile selectedFile=calculateSelectedFile(vcsContext);
  if (selectedFile != null) {
    for (    VirtualFile baseDir : baseDirs) {
      if (canScroll(baseDir,selectedFile)) {
        presentation.setEnabled(true);
        return;
      }
    }
  }
  presentation.setEnabled(false);
}
