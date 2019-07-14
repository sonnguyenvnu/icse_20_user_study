/** 
 * {@inheritDoc}
 */
@Override public boolean contains(String key){
  return getString(key,null) != null;
}
