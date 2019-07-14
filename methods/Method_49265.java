public static boolean hasSimpleInternalVertexKeyIndex(PropertyKey key){
  InternalRelationType type=(InternalRelationType)key;
  for (  IndexType index : type.getKeyIndexes()) {
    if (index.getElement() == ElementCategory.VERTEX && index.isCompositeIndex()) {
      if (index.indexesKey(key))       return true;
    }
  }
  return false;
}
