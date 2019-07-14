/** 
 * Enlarges this byte vector so that it can receive 'size' more bytes.
 * @param size number of additional bytes that this byte vector should be able to receive.
 */
private void enlarge(final int size){
  int doubleCapacity=2 * data.length;
  int minimalCapacity=length + size;
  byte[] newData=new byte[doubleCapacity > minimalCapacity ? doubleCapacity : minimalCapacity];
  System.arraycopy(data,0,newData,0,length);
  data=newData;
}
