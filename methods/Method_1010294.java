public void rename(FileDataSource dataSource,String newName) throws DataSourceFactoryNotFoundException, NoSourceRootsInModelRootException, SourceRootDoesNotExistException {
  IFile oldFile=dataSource.getFile();
  SourceRoot sourceRoot=findSourceRootOf(oldFile);
  CompositeResult<DataSource> result=getDataSourceFactoryBridge().createFileDataSource(new SModelName(newName),sourceRoot);
  FileDataSource source=(FileDataSource)result.getDataSource();
  IFile newFile=source.getFile();
  if (!newFile.equals(oldFile)) {
    newFile.getParent().mkdirs();
    newFile.createNewFile();
    dataSource.setFile(newFile);
    FileUtil.deleteWithAllEmptyDirs(oldFile);
  }
}
