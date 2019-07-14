public byte[] readData(int count,boolean exception){
  byte[] arr=new byte[count];
  readBytes(arr,exception);
  return arr;
}
