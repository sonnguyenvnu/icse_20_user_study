/** 
 * {@inheritDoc}
 */
@Override public AccountPreferences putString(String key,String value){
  mAccountManager.setUserData(mAccount,key,value);
  notifyChanged(key);
  return this;
}
