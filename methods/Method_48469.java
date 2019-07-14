public String getGraphName(){
  return getConfigurationAtOpen().getString(GRAPH_NAME.toStringWithoutRoot());
}
