/** 
 * Singleton placeholder that indicates a value is being loaded. 
 */
@SuppressWarnings("unchecked") static <K,V>ValueReference<K,V> unset(){
  return (ValueReference<K,V>)UNSET;
}
