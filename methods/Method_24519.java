/** 
 * <h3>Advanced</h3> Return a byte array of anything that's in the serial buffer up to the specified maximum number of bytes. Not particularly memory/speed efficient, because it creates a byte array on each read, but it's easier to use than readBytes(byte b[]) (see below).
 * @param max the maximum number of bytes to read
 */
public byte[] readBytes(int max){
synchronized (bufferLock) {
    if (bufferIndex == bufferLast)     return null;
    int length=bufferLast - bufferIndex;
    if (length > max)     length=max;
    byte outgoing[]=new byte[length];
    System.arraycopy(buffer,bufferIndex,outgoing,0,length);
    bufferIndex+=length;
    if (bufferIndex == bufferLast) {
      bufferIndex=0;
      bufferLast=0;
    }
    return outgoing;
  }
}
