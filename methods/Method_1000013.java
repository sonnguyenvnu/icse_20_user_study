public synchronized void discover(byte[] nodeId,int round,List<Node> prevTried){
  try {
    if (round == KademliaOptions.MAX_STEPS) {
      logger.debug("Node table contains [{}] peers",nodeManager.getTable().getNodesCount());
      logger.debug("{}",String.format("(KademliaOptions.MAX_STEPS) Terminating discover after %d rounds.",round));
      logger.trace("{}\n{}",String.format("Nodes discovered %d ",nodeManager.getTable().getNodesCount()),dumpNodes());
      return;
    }
    List<Node> closest=nodeManager.getTable().getClosestNodes(nodeId);
    List<Node> tried=new ArrayList<>();
    for (    Node n : closest) {
      if (!tried.contains(n) && !prevTried.contains(n)) {
        try {
          nodeManager.getNodeHandler(n).sendFindNode(nodeId);
          tried.add(n);
          wait(50);
        }
 catch (        Exception ex) {
          logger.error("Unexpected Exception " + ex,ex);
        }
      }
      if (tried.size() == KademliaOptions.ALPHA) {
        break;
      }
    }
    if (tried.isEmpty()) {
      logger.debug("{}",String.format("(tried.isEmpty()) Terminating discover after %d rounds.",round));
      logger.trace("{}\n{}",String.format("Nodes discovered %d ",nodeManager.getTable().getNodesCount()),dumpNodes());
      return;
    }
    tried.addAll(prevTried);
    discover(nodeId,round + 1,tried);
  }
 catch (  Exception ex) {
    logger.error("{}",ex);
  }
}
