/** 
 * Disables buffering by transferring the output to original destinations.
 */
protected void disableBuffering(){
  if (buffer == null) {
    return;
  }
  buffer=null;
}
