/** 
 * Concatenate a set of points to a polygon
 * @param component component id of the polygon
 * @param direction direction of the ring
 * @param points list of points to concatenate
 * @param pointOffset index of the first point
 * @param edges Array of edges to write the result to
 * @param edgeOffset index of the first edge in the result
 * @param length number of points to use
 * @return the edges creates
 */
private static Edge[] concat(int component,boolean direction,Coordinate[] points,final int pointOffset,Edge[] edges,final int edgeOffset,int length){
  assert edges.length >= length + edgeOffset;
  assert points.length >= length + pointOffset;
  edges[edgeOffset]=new Edge(points[pointOffset],null);
  for (int i=1; i < length; i++) {
    if (direction) {
      edges[edgeOffset + i]=new Edge(points[pointOffset + i],edges[edgeOffset + i - 1]);
      edges[edgeOffset + i].component=component;
    }
 else     if (!edges[edgeOffset + i - 1].coordinate.equals(points[pointOffset + i])) {
      edges[edgeOffset + i - 1].next=edges[edgeOffset + i]=new Edge(points[pointOffset + i],null);
      edges[edgeOffset + i - 1].component=component;
    }
 else {
      throw new InvalidShapeException("Provided shape has duplicate consecutive coordinates at: " + points[pointOffset + i]);
    }
  }
  if (direction) {
    edges[edgeOffset].setNext(edges[edgeOffset + length - 1]);
    edges[edgeOffset].component=component;
  }
 else {
    edges[edgeOffset + length - 1].setNext(edges[edgeOffset]);
    edges[edgeOffset + length - 1].component=component;
  }
  return edges;
}
