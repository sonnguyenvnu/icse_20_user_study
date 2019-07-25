@Override protected CheckBoxTreeItem<FileNodeWrapper> call(){
  UnlinkedPDFFileFilter unlinkedPDFFileFilter=new UnlinkedPDFFileFilter(fileFilter,databaseContext);
  return searchDirectory(directory.toFile(),unlinkedPDFFileFilter);
}
