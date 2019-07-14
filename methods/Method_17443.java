private static boolean isGhost(Node node){
  return (node != null) && ((node.type == QueueType.B1) || (node.type == QueueType.B2));
}
