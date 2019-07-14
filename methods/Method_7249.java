/** 
 * Convenience method to launch a Custom Tabs Activity.
 * @param context The source Context.
 * @param url The URL to load in the Custom Tab.
 */
public void launchUrl(Context context,Uri url){
  intent.setData(url);
  ContextCompat.startActivity(context,intent,startAnimationBundle);
}
