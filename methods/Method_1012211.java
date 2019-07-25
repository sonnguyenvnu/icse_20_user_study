/** 
 * ???
 * @param context
 */
public void init(Context context){
  if (mClient == null) {
    mClient=new LocationClient(context.getApplicationContext());
    mClient.setLocOption(getDefaultLocationClientOption());
  }
}
