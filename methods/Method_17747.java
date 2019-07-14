private static void setEdgeValue(int[] edges,YogaEdge edge,int value){
switch (edge) {
case ALL:
    for (int i=0; i < EDGE_COUNT; ++i) {
      edges[i]=value;
    }
  break;
case VERTICAL:
edges[EDGE_TOP]=value;
edges[EDGE_BOTTOM]=value;
break;
case HORIZONTAL:
edges[EDGE_LEFT]=value;
edges[EDGE_RIGHT]=value;
break;
case LEFT:
case TOP:
case RIGHT:
case BOTTOM:
case START:
case END:
edges[edgeIndex(edge)]=value;
break;
}
}
