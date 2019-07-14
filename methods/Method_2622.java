public boolean load(ByteArray byteArray,_ValueArray valueArray){
  for (int i=0; i < child.length; ++i) {
    int flag=byteArray.nextInt();
    if (flag == 1) {
      child[i]=new Node<V>();
      child[i].walkToLoad(byteArray,valueArray);
    }
  }
  size=valueArray.value.length;
  return true;
}
