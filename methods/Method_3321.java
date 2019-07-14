@Override public boolean load(ByteArray byteArray){
  template=byteArray.nextUTF();
  int size=byteArray.nextInt();
  offsetList=new ArrayList<int[]>(size);
  for (int i=0; i < size; ++i) {
    offsetList.add(new int[]{byteArray.nextInt(),byteArray.nextInt()});
  }
  size=byteArray.nextInt();
  delimiterList=new ArrayList<String>(size);
  for (int i=0; i < size; ++i) {
    delimiterList.add(byteArray.nextUTF());
  }
  return true;
}
