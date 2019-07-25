@Override public void apply(ModelRoot root){
  assert root instanceof JavaSourceStubModelRoot;
  ((JavaSourceStubModelRoot)root).setContentRoot(myPath);
}
