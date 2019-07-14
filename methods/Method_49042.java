public static RelationIdentifier getEdgeId(Object id){
  if (null == id)   return null;
  try {
    if (id instanceof JanusGraphEdge)     return (RelationIdentifier)((JanusGraphEdge)id).id();
 else     if (id instanceof RelationIdentifier)     return (RelationIdentifier)id;
 else     if (id instanceof String)     return RelationIdentifier.parse((String)id);
 else     if (id instanceof long[])     return RelationIdentifier.get((long[])id);
 else     if (id instanceof int[])     return RelationIdentifier.get((int[])id);
  }
 catch (  IllegalArgumentException e) {
  }
  return null;
}
