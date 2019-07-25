private final int find(int prefixOrUri,boolean prefix){
  if (m_dataLength == 0) {
    return -1;
  }
  int offset=m_dataLength - 1;
  for (int i=m_depth; i != 0; --i) {
    int count=m_data[offset];
    offset-=2;
    for (; count != 0; --count) {
      if (prefix) {
        if (m_data[offset] == prefixOrUri) {
          return m_data[offset + 1];
        }
      }
 else {
        if (m_data[offset + 1] == prefixOrUri) {
          return m_data[offset];
        }
      }
      offset-=2;
    }
  }
  return -1;
}
