/** 
 * Calculate all intersections of line segments and a vertical line. The Array of edges will be ordered asc by the y-coordinate of the intersections of edges.
 * @param dateline x-coordinate of the dateline
 * @param edges set of edges that may intersect with the dateline
 * @return number of intersecting edges
 */
protected static int intersections(double dateline,Edge[] edges){
  int numIntersections=0;
  assert !Double.isNaN(dateline);
  for (int i=0; i < edges.length; i++) {
    Coordinate p1=edges[i].coordinate;
    Coordinate p2=edges[i].next.coordinate;
    assert !Double.isNaN(p2.x) && !Double.isNaN(p1.x);
    edges[i].intersect=Edge.MAX_COORDINATE;
    double position=intersection(p1,p2,dateline);
    if (!Double.isNaN(position)) {
      edges[i].intersection(position);
      numIntersections++;
    }
  }
  Arrays.sort(edges,INTERSECTION_ORDER);
  return numIntersections;
}
