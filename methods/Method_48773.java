private static Iterable<MixedIndexType> getMixedIndexes(RelationType type){
  if (!type.isPropertyKey()) {
    return Collections.emptyList();
  }
  return Iterables.filter(Iterables.filter(((InternalRelationType)type).getKeyIndexes(),MIXED_INDEX_FILTER),MixedIndexType.class);
}
