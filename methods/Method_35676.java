@Override public List<TextFile> listFilesRecursively(){
  assertExistsAndIsDirectory();
  List<File> fileList=newArrayList();
  recursivelyAddFilesToList(rootDirectory,fileList);
  return toTextFileList(fileList);
}
