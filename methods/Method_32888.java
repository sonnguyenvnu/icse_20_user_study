/** 
 * Accept bytes (typically from the pseudo-teletype) and process them.
 * @param buffer a byte array containing the bytes to be processed
 * @param length the number of bytes in the array to process
 */
public void append(byte[] buffer,int length){
  for (int i=0; i < length; i++)   processByte(buffer[i]);
}
