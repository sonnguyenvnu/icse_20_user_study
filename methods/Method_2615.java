protected void walkToLoad(ObjectInput byteArray) throws IOException, ClassNotFoundException {
  c=byteArray.readChar();
  status=ARRAY_STATUS[byteArray.readInt()];
  if (status == Status.WORD_END_3 || status == Status.WORD_MIDDLE_2) {
    value=(V)byteArray.readObject();
  }
  int childSize=byteArray.readInt();
  child=new BaseNode[childSize];
  for (int i=0; i < childSize; ++i) {
    child[i]=new Node<V>();
    child[i].walkToLoad(byteArray);
  }
}
