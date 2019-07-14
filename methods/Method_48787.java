private synchronized void initialize(){
  if (null != instance) {
    final String errMsg="You may not instantiate a JanusGraphManager. The single instance should be handled by Tinkerpop's GremlinServer startup processes.";
    throw new JanusGraphManagerException(errMsg);
  }
  instance=this;
}
