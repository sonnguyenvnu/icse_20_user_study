public final boolean pop(){
  if (m_dataLength == 0) {
    return false;
  }
  int offset=m_dataLength - 1;
  int count=m_data[offset];
  if (count == 0) {
    return false;
  }
  count-=1;
  offset-=2;
  m_data[offset]=count;
  offset-=(1 + count * 2);
  m_data[offset]=count;
  m_dataLength-=2;
  m_count-=1;
  return true;
}
