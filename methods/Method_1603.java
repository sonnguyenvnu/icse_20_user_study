private synchronized byte[] allocateByteArray(int size){
  mByteArraySoftRef.clear();
  byte[] byteArray=new byte[size];
  mByteArraySoftRef.set(byteArray);
  return byteArray;
}
