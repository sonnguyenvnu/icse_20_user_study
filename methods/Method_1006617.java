@Override public void startup() throws LifeCycleException {
  super.startup();
  this.connectionEventHandler.setConnectionManager(this);
  this.connectionEventHandler.setConnectionEventListener(connectionEventListener);
  this.connectionFactory.init(connectionEventHandler);
}
