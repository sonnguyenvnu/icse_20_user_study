private AStarNode search(){
  while (q.size() > 0) {
    AStarNode u=(AStarNode)q.extractMin();
    if (u.isFinal()) {
      return u;
    }
    SearchNode.NextNodeIterator i=u.getNextNodes();
    while (i.hasNext()) {
      AStarNode v=(AStarNode)i.nextNode();
      double priority=v.getCost() + v.completionCost();
      v.setPriority(priority);
      q.insert(v);
    }
  }
  return null;
}
