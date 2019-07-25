public boolean open(@NotNull SNode node){
  CodeNavigationProvider[] navProviders=CodeNavigationProvider.EP_NAME.getExtensions();
  if (navProviders.length == 0) {
    return false;
  }
  SModel model=node.getModel();
  if (model == null || !(model.getModule() instanceof ReloadableModule)) {
    return false;
  }
  TraceablePositionInfo position=new TraceInfo().getPosition(node);
  assert position != null;
  final String projectPath=myProject.getProjectFile().getAbsolutePath();
  IFile file=getGeneratedFile(model,position);
  if (file == null) {
    return false;
  }
  for (  CodeNavigationProvider np : navProviders) {
    if (np.navigate(projectPath,file.getPath(),position.getStartLine(),position.getStartPosition(),position.getEndLine(),position.getEndPosition())) {
      return true;
    }
  }
  return false;
}
