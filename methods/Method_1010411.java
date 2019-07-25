@Override public boolean touch(String name){
  IFile file=getFile(name);
  myDelta.kept(file);
  return file.exists();
}
