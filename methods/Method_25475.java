RefactoringResult applyChanges(URI uri) throws Exception {
  Collection<DelegatingDescriptionListener> listeners=foundSources.removeAll(uri);
  if (listeners.isEmpty()) {
    return RefactoringResult.create("",RefactoringResultType.NO_CHANGES);
  }
  doApplyProcess(fileDestination,new FsFileSource(rootPath),listeners);
  return postProcess.apply(uri);
}
