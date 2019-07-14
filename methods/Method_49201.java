@Override public List<InternalVertex> getAllNew(){
  final List<InternalVertex> vertices=new ArrayList<>(10);
  for (  InternalVertex v : volatileVertices.values()) {
    if (v.isNew())     vertices.add(v);
  }
  return vertices;
}
