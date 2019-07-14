@Override public String toString(){
  GraphDatabaseConfiguration config=((StandardJanusGraph)this).getConfiguration();
  return StringFactory.graphString(this,config.getBackendDescription());
}
