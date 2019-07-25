@Override public void initialize() throws InitializationException {
  ContainerLoader.getDefaultContainer().addComponent(this,ReadHandler.class,"mysql");
}
