@Override public EdgeLabelMaker makeEdgeLabel(String name){
  return new StandardEdgeLabelMaker(this,name,indexSerializer,attributeHandler);
}
