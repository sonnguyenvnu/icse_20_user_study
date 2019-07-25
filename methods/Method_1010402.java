public void iterate(_FunctionTypes._return_P2_E0<? extends Boolean,? super IScript,? super Iterable<IResource>> iterator){
  for (  Cluster c : myClusters) {
    prepareSciptForCluster(c);
    if (!(iterator.invoke(c.getScript(),c.getResources()))) {
      break;
    }
  }
}
