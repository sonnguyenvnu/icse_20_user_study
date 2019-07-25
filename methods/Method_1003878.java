@Override public void verify(File value,Annotation annotation){
  checkArgument(value.canRead(),"File must be readable");
}
