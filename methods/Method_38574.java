/** 
 * Detaches entity by setting ID to <code>null</code>.
 */
default void detach(){
  setEntityId(null);
}
