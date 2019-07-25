private void extend(long length){
  long difference=length - buffer.length;
  if (difference < buffer.length * 0.25) {
    difference=(long)(buffer.length * 0.25);
  }
  if (difference < 4096) {
    difference=4096;
  }
  byte[] nb=new byte[(int)(difference + buffer.length)];
  System.arraycopy(buffer,0,nb,0,(int)size);
  buffer=nb;
}
