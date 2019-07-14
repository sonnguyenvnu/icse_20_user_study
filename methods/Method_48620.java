@Override public IndexJobFuture getIndexJobStatus(Index index){
  IndexIdentifier indexId=new IndexIdentifier(index);
  return graph.getBackend().getScanJobStatus(indexId);
}
