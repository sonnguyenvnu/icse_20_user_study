/** 
 * {@inheritDoc}
 */
@Override public AccountPreferences remove(String key){
  return putString(key,null);
}
