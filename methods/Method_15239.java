/** 
 * ??????Activity?Intent
 * @param title
 * @param url
 */
public static Intent createIntent(Context context,String title,String url){
  return new Intent(context,WebViewActivity.class).putExtra(WebViewActivity.INTENT_TITLE,title).putExtra(WebViewActivity.INTENT_URL,url);
}
