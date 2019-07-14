@Override public boolean load(ByteArray byteArray){
  int size=byteArray.nextInt();
  o=new char[size];
  for (int i=0; i < size; ++i) {
    o[i]=byteArray.nextChar();
  }
  size=byteArray.nextInt();
  w=new double[size];
  for (int i=0; i < size; ++i) {
    w[i]=byteArray.nextDouble();
  }
  return true;
}
