@Override public Set<JanusGraphVertex> getVertices(Change change){
  if (change.isProper())   return vertices.get(change);
  assert change == Change.ANY;
  final Set<JanusGraphVertex> all=new HashSet<>();
  for (  Change state : new Change[]{Change.ADDED,Change.REMOVED}) {
    all.addAll(vertices.get(state));
    for (    JanusGraphRelation rel : relations.get(state)) {
      InternalRelation internalRelation=(InternalRelation)rel;
      for (int p=0; p < internalRelation.getLen(); p++)       all.add(internalRelation.getVertex(p));
    }
  }
  return all;
}
