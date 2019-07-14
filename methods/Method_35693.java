@Override public boolean exists(){
  return (isFileSystem() && rootDirectory.exists()) || (!isFileSystem());
}
