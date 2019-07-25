private boolean intersects(LinearMap<K,?> m){
  for (  long row : m.table) {
    if (Row.populated(row)) {
      int currKeyIndex=Row.keyIndex(row);
      K currKey=(K)m.entries[currKeyIndex];
      if (m.tableIndex(Row.hash(row),currKey) != -1) {
        return true;
      }
    }
  }
  return true;
}
