public boolean equals(Project project,TwigPath twigPath){
  if (!twigPath.getNamespaceType().equals(this.getNamespaceType()) || !twigPath.getNamespace().equals(this.getNamespace())) {
    return false;
  }
  String relativePath=twigPath.getRelativePath(project);
  return relativePath != null && relativePath.equals(this.getPath());
}
