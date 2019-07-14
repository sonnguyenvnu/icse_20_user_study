/** 
 * ( begin auto-generated from Client_readBytesUntil.xml ) Reads from the port into a buffer of bytes up to and including a  particular character. If the character isn't in the buffer, 'null' is  returned. The version with no <b>byteBuffer</b> parameter returns a byte  array of all data up to and including the <b>interesting</b> byte. This  is not efficient, but is easy to use. The version with the  <b>byteBuffer</b> parameter is more memory and time efficient. It grabs  the data in the buffer and puts it into the byte array passed in and  returns an int value for the number of bytes read. If the byte buffer is  not large enough, -1 is returned and an error is printed to the message  area. If nothing is in the buffer, 0 is returned. ( end auto-generated )
 * @webref client:client
 * @usage application
 * @brief Reads from the buffer of bytes up to and including a particular character
 * @param interesting character designated to mark the end of the data
 */
public byte[] readBytesUntil(int interesting){
  byte what=(byte)interesting;
synchronized (bufferLock) {
    if (bufferIndex == bufferLast)     return null;
    int found=-1;
    for (int k=bufferIndex; k < bufferLast; k++) {
      if (buffer[k] == what) {
        found=k;
        break;
      }
    }
    if (found == -1)     return null;
    int length=found - bufferIndex + 1;
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
