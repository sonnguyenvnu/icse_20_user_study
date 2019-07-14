/** 
 * ( begin auto-generated from Client_available.xml ) Returns the number of bytes available. When any client has bytes  available from the server, it returns the number of bytes. ( end auto-generated )
 * @webref client:client
 * @usage application
 * @brief Returns the number of bytes in the buffer waiting to be read
 */
public int available(){
synchronized (bufferLock) {
    return (bufferLast - bufferIndex);
  }
}
