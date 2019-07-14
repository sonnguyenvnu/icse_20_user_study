public static long getCompareId(Element element){
  Object id=element.id();
  if (id instanceof Long)   return (Long)id;
 else   if (id instanceof RelationIdentifier)   return ((RelationIdentifier)id).getRelationId();
 else   throw new IllegalArgumentException("Element identifier has unrecognized type: " + id);
}
