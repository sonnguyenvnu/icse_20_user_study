S find(final long key){
  final int index=HashOperations.hashSearch(keys_,lgCurrentCapacity_,key);
  if (index == -1) {
    return null;
  }
  return summaries_[index];
}
