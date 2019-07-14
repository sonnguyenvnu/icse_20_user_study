/** 
 * ( begin auto-generated from Client_readBytes.xml ) Reads a group of bytes from the buffer. The version with no parameters  returns a byte array of all data in the buffer. This is not efficient,  but is easy to use. The version with the <b>byteBuffer</b> parameter is  more memory and time efficient. It grabs the data in the buffer and puts  it into the byte array passed in and returns an int value for the number  of bytes read. If more bytes are available than can fit into the  <b>byteBuffer</b>, only those that fit are read. ( end auto-generated ) <h3>Advanced</h3> Return a byte array of anything that's in the serial buffer. Not particularly memory/speed efficient, because it creates a byte array on each read, but it's easier to use than readBytes(byte b[]) (see below).
 * @webref client:client
 * @usage application
 * @brief Reads everything in the buffer
 */
public byte[] readBytes(){
synchronized (bufferLock) {
    if (bufferIndex == bufferLast)     return null;
    int length=bufferLast - bufferIndex;
    byte outgoing[]=new byte[length];
    System.arraycopy(buffer,bufferIndex,outgoing,0,length);
    bufferIndex=0;
    bufferLast=0;
    return outgoing;
  }
}
