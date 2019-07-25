/** 
 * Encode HTML
 * @param html
 * @param imageGetter
 * @return html
 */
public static CharSequence encode(final Context context,final String html,final ImageGetter imageGetter){
  if (TextUtils.isEmpty(html))   return "";
  return Html.fromHtml(context,html,imageGetter);
}
