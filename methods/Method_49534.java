private static Boolean isLoopAdded(Vertex vertex,String label){
  Iterator<Vertex> adjacentVertices=vertex.vertices(Direction.BOTH,label);
  while (adjacentVertices.hasNext()) {
    Vertex adjacentVertex=adjacentVertices.next();
    if (adjacentVertex.equals(vertex)) {
      return true;
    }
  }
  return false;
}
