private boolean createLayout(int width){
  if (text != null) {
    try {
      if (leftDrawable != null) {
        width-=leftDrawable.getIntrinsicWidth();
        width-=drawablePadding;
      }
      if (rightDrawable != null) {
        width-=rightDrawable.getIntrinsicWidth();
        width-=drawablePadding;
      }
      CharSequence string;
      if (scrollNonFitText) {
        string=text;
      }
 else {
        string=TextUtils.ellipsize(text,textPaint,width,TextUtils.TruncateAt.END);
      }
      layout=new StaticLayout(string,0,string.length(),textPaint,scrollNonFitText ? AndroidUtilities.dp(2000) : width + AndroidUtilities.dp(8),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      calcOffset(width);
    }
 catch (    Exception ignore) {
    }
  }
 else {
    layout=null;
    textWidth=0;
    textHeight=0;
  }
  invalidate();
  return true;
}
