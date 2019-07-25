@NotNull public IFile calculate(){
  String fileName=calcFileName();
  IFile sourceRootFile=mySourceRoot.getAbsolutePath();
  return sourceRootFile.getDescendant(fileName);
}
