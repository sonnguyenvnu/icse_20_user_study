protected void walkToLoad(ByteArray byteArray,_ValueArray<V> valueArray){
  c=byteArray.nextChar();
  status=ARRAY_STATUS[byteArray.nextInt()];
  if (status == Status.WORD_END_3 || status == Status.WORD_MIDDLE_2) {
    value=valueArray.nextValue();
  }
  int childSize=byteArray.nextInt();
  child=new BaseNode[childSize];
  for (int i=0; i < childSize; ++i) {
    child[i]=new Node<V>();
    child[i].walkToLoad(byteArray,valueArray);
  }
}
