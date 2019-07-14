/** 
 * ( begin auto-generated from Client_read.xml ) Returns a number between 0 and 255 for the next byte that's waiting in  the buffer. Returns -1 if there is no byte, although this should be  avoided by first cheacking <b>available()</b> to see if any data is available. ( end auto-generated )
 * @webref client:client
 * @usage application
 * @brief Returns a value from the buffer
 */
public int read(){
synchronized (bufferLock) {
    if (bufferIndex == bufferLast)     return -1;
    int outgoing=buffer[bufferIndex++] & 0xff;
    if (bufferIndex == bufferLast) {
      bufferIndex=0;
      bufferLast=0;
    }
    return outgoing;
  }
}
