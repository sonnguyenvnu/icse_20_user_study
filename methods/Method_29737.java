/** 
 * {@inheritDoc}
 */
@Override public AccountPreferences putInt(String key,int value){
  return putString(key,Integer.toString(value));
}
