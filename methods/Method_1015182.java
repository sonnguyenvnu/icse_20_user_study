@Override public List<LinearMap<K,V>> split(int parts){
  parts=Math.min(parts,size);
  List<LinearMap<K,V>> list=new List<LinearMap<K,V>>().linear();
  if (parts <= 1) {
    return list.addLast(this).forked();
  }
  int partSize=table.length / parts;
  for (int p=0; p < parts; p++) {
    int start=p * partSize;
    int finish=(p == (parts - 1)) ? table.length : start + partSize;
    LinearMap<K,V> m=new LinearMap<>(finish - start);
    for (int i=start; i < finish; i++) {
      long row=table[i];
      if (Row.populated(row)) {
        int keyIndex=Row.keyIndex(row);
        int resultKeyIndex=m.size << 1;
        m.putEntry(resultKeyIndex,(K)entries[keyIndex],(V)entries[keyIndex + 1]);
        m.putTable(Row.hash(row),resultKeyIndex);
        m.size++;
      }
    }
    if (m.size > 0) {
      list.addLast(m);
    }
  }
  return list.forked();
}
