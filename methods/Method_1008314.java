private static int merge(Edge[] intersections,int offset,int length,Edge[] holes,int numHoles){
  for (int i=0; i < length; i+=2) {
    Edge e1=intersections[offset + i + 0];
    Edge e2=intersections[offset + i + 1];
    if (e2.component > 0) {
      numHoles--;
      holes[e2.component - 1]=holes[numHoles];
      holes[numHoles]=null;
    }
    if (e1.intersect != Edge.MAX_COORDINATE && e2.intersect != Edge.MAX_COORDINATE && !(e1.next.next.coordinate.equals3D(e2.coordinate) && Math.abs(e1.next.coordinate.x) == DATELINE && Math.abs(e2.coordinate.x) == DATELINE)) {
      connect(e1,e2);
    }
  }
  return numHoles;
}
