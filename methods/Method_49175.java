@Override public VertexLabelMaker makeVertexLabel(String name){
  StandardVertexLabelMaker maker=new StandardVertexLabelMaker(this);
  maker.name(name);
  return maker;
}
