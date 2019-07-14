private static String element2String(Object elementId){
  Preconditions.checkArgument(elementId instanceof Long || elementId instanceof RelationIdentifier);
  if (elementId instanceof Long)   return longID2Name((Long)elementId);
 else   return ((RelationIdentifier)elementId).toString();
}
