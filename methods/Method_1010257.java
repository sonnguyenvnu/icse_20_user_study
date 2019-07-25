@Override public void rename(String newModelName,boolean changeFile){
  assertCanChange();
  SModelReference oldName=getReference();
  fireBeforeModelRenamed(new SModelRenamedEvent(this,oldName.getModelName(),newModelName));
  SModelReference newModelReference=PersistenceFacade.getInstance().createModelReference(getReference().getModuleReference(),getReference().getModelId(),newModelName);
  fireBeforeModelRenamed(newModelReference);
  changeModelReference(newModelReference);
  try {
    if (changeFile) {
      if (!(getSource() instanceof FileDataSource)) {
        throw new UnsupportedOperationException("cannot change model file on non-file data source");
      }
      IFile oldFile=((FileDataSource)getSource()).getFile();
      fireBeforeModelFileChanged(new SModelFileChangedEvent(this,oldFile,null));
      ModelRoot root=getModelRoot();
      if (root instanceof DefaultModelRoot) {
        ((DefaultModelRoot)root).rename(((FileDataSource)getSource()),newModelName);
        updateTimestamp();
      }
      final IFile newFile=((FileDataSource)getSource()).getFile();
      if (!oldFile.getPath().equals(newFile.getPath())) {
        fireModelFileChanged(new SModelFileChangedEvent(this,oldFile,newFile));
      }
    }
  }
 catch (  DataSourceFactoryNotFoundException|NoSourceRootsInModelRootException|SourceRootDoesNotExistException e) {
    LOG.error(e);
  }
  save();
  fireModelRenamed(new SModelRenamedEvent(this,oldName.getModelName(),newModelName));
  fireModelRenamed(oldName);
}
