@Override protected void update(VcsContext vcsContext,Presentation presentation){
  ProjectPane pp=getProjectPane(vcsContext);
  VirtualFile selectedFile=getSelectedFile(vcsContext);
  presentation.setEnabled(pp != null && selectedFile != null && pp.createSelectInTarget().canSelect(new FileSelectInContext(pp.getProject(),selectedFile)));
}
