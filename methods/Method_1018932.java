/** 
 * Set new media, play it, and wait for it to start playing (or error).
 * @param mrl media resource locator
 * @param options zero or more options to attach to the new media
 * @return <code>true</code> if successful; <code>false</code> on error
 */
public boolean start(String mrl,String... options){
  if (prepare(mrl,options)) {
    return start();
  }
 else {
    return false;
  }
}
