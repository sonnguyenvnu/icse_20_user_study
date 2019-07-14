private void setCurrentCaption(final CharSequence caption,boolean setAsIs){
  if (!TextUtils.isEmpty(caption)) {
    Theme.createChatResources(null,true);
    CharSequence result;
    if (setAsIs) {
      result=caption;
    }
 else {
      if (caption instanceof Spannable) {
        Spannable spannable=(Spannable)caption;
        TextPaintUrlSpan[] spans=spannable.getSpans(0,caption.length(),TextPaintUrlSpan.class);
        SpannableStringBuilder builder=new SpannableStringBuilder(caption.toString());
        result=builder;
        if (spans != null && spans.length > 0) {
          for (int a=0; a < spans.length; a++) {
            builder.setSpan(new URLSpan(spans[a].getUrl()){
              @Override public void onClick(              View widget){
                openWebpageUrl(getURL(),null);
              }
            }
,spannable.getSpanStart(spans[a]),spannable.getSpanEnd(spans[a]),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          }
        }
      }
 else {
        result=new SpannableStringBuilder(caption.toString());
      }
    }
    CharSequence str=Emoji.replaceEmoji(result,captionTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false);
    captionTextView.setTag(str);
    captionTextView.setText(str);
    captionTextView.setVisibility(View.VISIBLE);
  }
 else {
    captionTextView.setTag(null);
    captionTextView.setVisibility(View.GONE);
  }
}
