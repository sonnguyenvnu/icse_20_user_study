@Override public boolean contains(int minimum,int supremum){
  int maximum=supremum - 1;
  int start=BufferUtil.advanceUntil(content,-1,cardinality,(short)minimum);
  int end=BufferUtil.advanceUntil(content,start - 1,cardinality,(short)maximum);
  return start < cardinality && end < cardinality && end - start == maximum - minimum && content.get(start) == (short)minimum && content.get(end) == (short)maximum;
}
