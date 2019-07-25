/** 
 * Broadcast to all the links
 * @param v current node
 * @param node node
 */
private void broadcast(int v,String node){
  Set<String> children=fwdEdges.get(node);
  if (children != null) {
    for (    String c : children) {
      int order=count.get(node + ":" + c);
      grid[Integer.parseInt(c.substring(1)) - 1][c.charAt(0) - 'A']+=(v * order);
      broadcast(v,c);
    }
  }
}
