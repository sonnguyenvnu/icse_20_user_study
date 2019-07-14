/** 
 * Advances the position by the specified number of bytes read.
 * @param bytesRead The number of bytes read.
 */
private void commitBytesRead(int bytesRead){
  if (bytesRead != C.RESULT_END_OF_INPUT) {
    position+=bytesRead;
  }
}
