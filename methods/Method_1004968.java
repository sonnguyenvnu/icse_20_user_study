private List<Walk> walk(final Object curr,final Object prev,final GraphWindow graphWindow,final LinkedList<Set<Edge>> edgeQueue,final LinkedList<Set<Entity>> entityQueue,final int hops){
  final List<Walk> walks=new ArrayList<>();
  if (null != prev && hops != edgeQueue.size()) {
    edgeQueue.offer(graphWindow.getAdjacencyMaps().get(edgeQueue.size()).getEdges(prev,curr));
  }
  entityQueue.offer(graphWindow.getEntityMaps().get(entityQueue.size()).get(curr));
  if (hops == edgeQueue.size()) {
    final Walk walk=buildWalk(edgeQueue,entityQueue);
    walks.add(walk);
  }
 else {
    for (    final Object obj : graphWindow.getAdjacencyMaps().get(edgeQueue.size()).getDestinations(curr)) {
      walks.addAll(walk(obj,curr,graphWindow,edgeQueue,entityQueue,hops));
    }
  }
  if (!edgeQueue.isEmpty()) {
    edgeQueue.pollLast();
  }
  if (!entityQueue.isEmpty()) {
    entityQueue.pollLast();
  }
  return walks;
}
