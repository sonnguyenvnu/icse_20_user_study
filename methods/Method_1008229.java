public <Response>void execute(NodeListenerCallback<Response> callback,ActionListener<Response> listener){
  final List<DiscoveryNode> nodes=this.nodes;
  if (closed) {
    throw new IllegalStateException("transport client is closed");
  }
  ensureNodesAreAvailable(nodes);
  int index=getNodeNumber();
  RetryListener<Response> retryListener=new RetryListener<>(callback,listener,nodes,index,hostFailureListener);
  DiscoveryNode node=retryListener.getNode(0);
  try {
    callback.doWithNode(node,retryListener);
  }
 catch (  Exception e) {
    try {
      listener.onFailure(e);
    }
  finally {
      retryListener.maybeNodeFailed(node,e);
    }
  }
}
