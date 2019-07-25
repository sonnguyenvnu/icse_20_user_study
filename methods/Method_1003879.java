@Override public void verify(File value,Annotation annotation){
  checkArgument(value.canWrite(),"File must be writable.");
}
