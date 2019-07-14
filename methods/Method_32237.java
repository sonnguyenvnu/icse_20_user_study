/** 
 * Deserialize to the singleton.
 */
private Object readResolve(){
  return INSTANCE;
}
