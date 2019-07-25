public int index(long seqno){
  return (int)((seqno) & (capacity() - 1));
}
