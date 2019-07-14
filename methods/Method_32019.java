/** 
 * Serialization singleton
 */
private Object readResolve(){
  return INSTANCE;
}
