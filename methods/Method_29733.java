/** 
 * {@inheritDoc}
 */
@Override public String getString(String key,String defaultValue){
  String value=mAccountManager.getUserData(mAccount,key);
  return value != null ? value : defaultValue;
}
