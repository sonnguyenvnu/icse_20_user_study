@SuppressLint("DrawAllocation") @Override protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
  descriptionLayout=null;
  titleLayout=null;
  int viewWidth=MeasureSpec.getSize(widthMeasureSpec);
  int maxWidth=viewWidth - AndroidUtilities.dp(AndroidUtilities.leftBaseline) - AndroidUtilities.dp(8 + 20);
  try {
    String title=currentMessageObject.getMusicTitle();
    int width=(int)Math.ceil(Theme.chat_contextResult_titleTextPaint.measureText(title));
    CharSequence titleFinal=TextUtils.ellipsize(title.replace('\n',' '),Theme.chat_contextResult_titleTextPaint,Math.min(width,maxWidth),TextUtils.TruncateAt.END);
    titleLayout=new StaticLayout(titleFinal,Theme.chat_contextResult_titleTextPaint,maxWidth + AndroidUtilities.dp(4),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  try {
    String author=currentMessageObject.getMusicAuthor();
    int width=(int)Math.ceil(Theme.chat_contextResult_descriptionTextPaint.measureText(author));
    CharSequence authorFinal=TextUtils.ellipsize(author.replace('\n',' '),Theme.chat_contextResult_descriptionTextPaint,Math.min(width,maxWidth),TextUtils.TruncateAt.END);
    descriptionLayout=new StaticLayout(authorFinal,Theme.chat_contextResult_descriptionTextPaint,maxWidth + AndroidUtilities.dp(4),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),AndroidUtilities.dp(56) + (needDivider ? 1 : 0));
  int maxPhotoWidth=AndroidUtilities.dp(52);
  int x=LocaleController.isRTL ? MeasureSpec.getSize(widthMeasureSpec) - AndroidUtilities.dp(8) - maxPhotoWidth : AndroidUtilities.dp(8);
  radialProgress.setProgressRect(buttonX=x + AndroidUtilities.dp(4),buttonY=AndroidUtilities.dp(6),x + AndroidUtilities.dp(48),AndroidUtilities.dp(50));
  measureChildWithMargins(checkBox,widthMeasureSpec,0,heightMeasureSpec,0);
}
