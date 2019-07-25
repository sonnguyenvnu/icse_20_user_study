@Override public void reset(Project project,ModelRoot root){
  assert root instanceof JavaSourceStubModelRoot;
  this.myProject=project;
  this.myPath=((JavaSourceStubModelRoot)root).getContentRoot();
}
