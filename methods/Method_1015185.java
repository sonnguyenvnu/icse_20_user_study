private void combine(LinearMap<K,?> m,LinearMap<K,V> result,IntPredicate indexPredicate){
  for (  long row : table) {
    if (Row.populated(row)) {
      int currKeyIndex=Row.keyIndex(row);
      K currKey=(K)entries[currKeyIndex];
      int entryIndex=m.tableIndex(Row.hash(row),currKey);
      if (indexPredicate.test(entryIndex)) {
        int resultKeyIndex=result.size << 1;
        result.putEntry(resultKeyIndex,currKey,(V)entries[currKeyIndex + 1]);
        result.putTable(Row.hash(row),resultKeyIndex);
        result.size++;
      }
    }
  }
}
