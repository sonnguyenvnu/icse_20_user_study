/** 
 * ????
 * @param context
 * @param webSite
 */
public static void openWebSite(Activity context,String webSite){
  if (context == null || StringUtil.isNotEmpty(webSite,true) == false) {
    Log.e(TAG,"openWebSite  context == null || StringUtil.isNotEmpty(webSite, true) == false >> return;");
    return;
  }
  toActivity(context,new Intent(Intent.ACTION_VIEW,Uri.parse(StringUtil.getCorrectUrl(webSite))));
}
