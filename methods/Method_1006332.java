@Override public boolean contains(int minimum,int supremum){
  int maximum=supremum - 1;
  int start=Util.advanceUntil(content,-1,cardinality,(short)minimum);
  int end=Util.advanceUntil(content,start - 1,cardinality,(short)maximum);
  return start < cardinality && end < cardinality && end - start == maximum - minimum && content[start] == (short)minimum && content[end] == (short)maximum;
}
