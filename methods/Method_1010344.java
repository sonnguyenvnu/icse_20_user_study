public void rebuild(T t,Runnable r){
  MyNodeReadListener listener=new MyNodeReadListener();
  NodeReadEventsCaster.setNodesReadListener(listener);
  try {
    r.run();
  }
  finally {
    NodeReadEventsCaster.removeNodesReadListener();
  }
  myObjectsToNodes.clearFirst(t);
  for (  SNode n : listener.getDependencies()) {
    myObjectsToNodes.addLink(t,n);
  }
}
