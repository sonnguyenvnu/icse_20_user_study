@Override public void initialize() throws InitializationException {
  ContainerLoader.getDefaultContainer().addComponent(this,WriteHandler.class,"mysql");
}
