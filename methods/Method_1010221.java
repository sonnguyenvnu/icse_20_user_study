@Override public void execute(SRepository repository){
  ((AbstractModule)myReference.getSourceNode().getModel().getModule()).addDependency(targetModuleRef,false);
}
