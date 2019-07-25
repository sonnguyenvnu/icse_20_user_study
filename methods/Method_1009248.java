@Override public boolean validate(){
  String projectRootPath=myProjectRootComponent.getText();
  if (StringUtil.isEmpty(projectRootPath)) {
    return false;
  }
  VirtualFile projectRoot=LocalFileSystem.getInstance().refreshAndFindFileByPath(projectRootPath);
  if (projectRoot == null) {
    return false;
  }
  if (myGetDepsCheckbox.isSelected() && !ApplicationManager.getApplication().isUnitTestMode()) {
    String workingDirectory=projectRoot.getCanonicalPath();
    assert workingDirectory != null;
    Sdk sdk=getWizardContext().getProjectJdk();
    updateHex(workingDirectory,sdk);
    fetchDependencies(workingDirectory,sdk);
  }
  Builder builder=getBuilder();
  builder.setIsImportingProject(getWizardContext().isCreatingNewProject());
  return builder.setProjectRoot(projectRoot);
}
