/** 
 * ( begin auto-generated from Client_clear.xml ) Empty the buffer, removes all the data stored there. ( end auto-generated )
 * @webref client:client
 * @usage application
 * @brief Clears the buffer
 */
public void clear(){
synchronized (bufferLock) {
    bufferLast=0;
    bufferIndex=0;
  }
}
