/** 
 * @param context
 * @param spannable
 * @param title
 * @return
 */
public static SpannableStringBuilder getText(Activity context,Spannable spannable,String title){
  if (spannable == null || spannable.length() <= 0) {
    Log.e(TAG,"getText  spannable == null || spannable.length() <= 0 >> return new SpannableStringBuilder();");
    return new SpannableStringBuilder();
  }
  SpannableStringBuilder builder=new SpannableStringBuilder(spannable);
  builder.clearSpans();
  URLSpan[] urls=spannable.getSpans(0,spannable.length(),URLSpan.class);
  if (urls != null) {
    for (    URLSpan urlSpan : urls) {
      if (urlSpan != null) {
        MyURLSpan myURLSpan=new MyURLSpan(context,urlSpan.getURL(),title);
        builder.setSpan(myURLSpan,spannable.getSpanStart(urlSpan),spannable.getSpanEnd(urlSpan),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
      }
    }
  }
  return builder;
}
