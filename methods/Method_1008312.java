/** 
 * This method sets the component id of all edges in a ring to a given id and shifts the coordinates of this component according to the dateline
 * @param edge An arbitrary edge of the component
 * @param id id to apply to the component
 * @param edges a list of edges to which all edges of the component will be added (could be <code>null</code>)
 * @return number of edges that belong to this component
 */
private static int component(final Edge edge,final int id,final ArrayList<Edge> edges){
  Edge any=edge;
  while (any.coordinate.x == +DATELINE || any.coordinate.x == -DATELINE) {
    if ((any=any.next) == edge) {
      break;
    }
  }
  double shiftOffset=any.coordinate.x > DATELINE ? DATELINE : (any.coordinate.x < -DATELINE ? -DATELINE : 0);
  if (debugEnabled()) {
    LOGGER.debug("shift: [{}]",shiftOffset);
  }
  int length=0, connectedComponents=0;
  int splitIndex=1;
  Edge current=edge;
  Edge prev=edge;
  HashMap<Coordinate,Tuple<Edge,Edge>> visitedEdge=new HashMap<>();
  do {
    current.coordinate=shift(current.coordinate,shiftOffset);
    current.component=id;
    if (edges != null) {
      if (visitedEdge.containsKey(current.coordinate)) {
        if (connectedComponents > 0 && current.next != edge) {
          throw new InvalidShapeException("Shape contains more than one shared point");
        }
        final int visitID=-id;
        Edge firstAppearance=visitedEdge.get(current.coordinate).v2();
        Edge temp=firstAppearance.next;
        firstAppearance.next=current.next;
        current.next=temp;
        current.component=visitID;
        do {
          prev.component=visitID;
          prev=visitedEdge.get(prev.coordinate).v1();
          ++splitIndex;
        }
 while (!current.coordinate.equals(prev.coordinate));
        ++connectedComponents;
      }
 else {
        visitedEdge.put(current.coordinate,new Tuple<Edge,Edge>(prev,current));
      }
      edges.add(current);
      prev=current;
    }
    length++;
  }
 while (connectedComponents == 0 && (current=current.next) != edge);
  return (splitIndex != 1) ? length - splitIndex : length;
}
