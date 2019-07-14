@Override public void apply(Traversal.Admin<?,?> traversal){
  if (traversal.getStartStep() instanceof IoStep) {
    IoStep ioStep=(IoStep)traversal.getStartStep();
    ioStep.configure(IO.registry,JanusGraphIoRegistry.getInstance());
  }
}
