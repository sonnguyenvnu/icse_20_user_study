/** 
 * Helper which indicates that some bytes were just parsed. This should only be used (for efficiency sake) if the parse is known to be observed.
 * @param length {@code >= 0;} number of bytes parsed
 * @param message {@code non-null;} associated message
 */
private void parsed(int length,String message){
  observer.parsed(bytes,parseCursor,length,message);
  parseCursor+=length;
}
