public void update(AnActionEvent event){
  Project project=getEventProject(event);
  if (project == null || !Symfony2ProjectComponent.isEnabled(project)) {
    this.setStatus(event,false);
    return;
  }
  this.setStatus(event,BundleClassGeneratorUtil.getBundleDirContext(event) != null);
}
