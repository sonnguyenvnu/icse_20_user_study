@Override public boolean load(ByteArray byteArray){
  total=byteArray.nextInt();
  int size=byteArray.nextInt();
  Integer[] valueArray=new Integer[size];
  for (int i=0; i < valueArray.length; ++i) {
    valueArray[i]=byteArray.nextInt();
  }
  d.load(byteArray,valueArray);
  return true;
}
