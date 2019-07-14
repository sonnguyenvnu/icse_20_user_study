/** 
 * Returns query for given key. In debug mode, props are reloaded every time before the lookup.
 */
@Override public String getQuery(final String key){
  return props.getValue(key);
}
