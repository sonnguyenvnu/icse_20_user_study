public void update(AnActionEvent event){
  Project project=event.getData(PlatformDataKeys.PROJECT);
  if (project == null || !Symfony2ProjectComponent.isEnabled(project)) {
    this.setStatus(event,false);
    return;
  }
  Pair<PsiFile,PhpClass> pair=findPhpClass(event);
  if (pair == null) {
    return;
  }
  PsiFile psiFile=pair.getFirst();
  if (!(psiFile instanceof YAMLFile) && !(psiFile instanceof XmlFile) && !(psiFile instanceof PhpFile)) {
    this.setStatus(event,false);
  }
}
