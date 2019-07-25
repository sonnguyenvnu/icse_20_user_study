protected boolean search(N startNode,N endNode,Heuristic<N> heuristic){
  initSearch(startNode,endNode,heuristic);
  do {
    current=openList.pop();
    current.category=CLOSED;
    if (current.node == endNode)     return true;
    visitChildren(endNode,heuristic);
  }
 while (openList.size > 0);
  return false;
}
