private final int get(int index,boolean prefix){
  if (m_dataLength == 0 || index < 0) {
    return -1;
  }
  int offset=0;
  for (int i=m_depth; i != 0; --i) {
    int count=m_data[offset];
    if (index >= count) {
      index-=count;
      offset+=(2 + count * 2);
      continue;
    }
    offset+=(1 + index * 2);
    if (!prefix) {
      offset+=1;
    }
    return m_data[offset];
  }
  return -1;
}
