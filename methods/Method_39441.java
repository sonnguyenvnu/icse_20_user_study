/** 
 * Sets value on some profile.
 */
public void setValue(final String key,final String value,final String profile){
  if (profile == null) {
    data.putBaseProperty(key,value,false);
  }
 else {
    data.putProfileProperty(key,value,profile,false);
  }
  initialized=false;
}
