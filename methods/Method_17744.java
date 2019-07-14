static int getEdgeColor(int[] colorArray,YogaEdge edge){
  if (colorArray.length != EDGE_COUNT) {
    throw new IllegalArgumentException("Given wrongly sized array");
  }
  return colorArray[edgeIndex(edge)];
}
