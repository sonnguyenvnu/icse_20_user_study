public void update(AnActionEvent event){
  Project project=event.getData(PlatformDataKeys.PROJECT);
  if (project == null || !Symfony2ProjectComponent.isEnabled(project)) {
    setStatus(event,false);
  }
}
