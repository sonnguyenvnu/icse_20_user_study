@Override protected ActualSocketServer createMergeServer(final ActualSocketServer thatServer){
  return newBaseServer(this.getPort().or(thatServer.getPort()).or(0));
}
