/** 
 * Clears a header for key requests made by the callback.
 * @param name The name of the header field.
 */
public void clearKeyRequestProperty(String name){
  Assertions.checkNotNull(name);
synchronized (keyRequestProperties) {
    keyRequestProperties.remove(name);
  }
}
