@Override public void verify(File value,Annotation annotation){
  checkArgument(value.isDirectory(),"Must be a directory.");
}
