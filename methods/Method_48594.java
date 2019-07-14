private static String composeRelationTypeIndexName(RelationType type,String name){
  return String.valueOf(type.longId()) + RELATION_INDEX_SEPARATOR + name;
}
