public static boolean hasAnyIndex(PropertyKey key){
  InternalRelationType type=(InternalRelationType)key;
  return !Iterables.isEmpty(type.getKeyIndexes()) || Iterables.size(type.getRelationIndexes()) > 1;
}
