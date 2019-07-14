private static boolean isHit(Node node){
  return (node != null) && ((node.type == QueueType.T1) || (node.type == QueueType.T2));
}
