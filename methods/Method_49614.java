@Override public void init(final ServerGremlinExecutor serverGremlinExecutor){
  this.serverGremlinExecutor=serverGremlinExecutor;
  super.init(serverGremlinExecutor);
  final GraphManager graphManager=serverGremlinExecutor.getGraphManager();
  Preconditions.checkArgument(graphManager instanceof JanusGraphManager,"Must use JanusGraphManager with a JanusGraphChannelizer.");
  ((JanusGraphManager)graphManager).configureGremlinExecutor(serverGremlinExecutor.getGremlinExecutor());
}
