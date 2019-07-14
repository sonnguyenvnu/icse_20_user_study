private void updateSecretTimeText(MessageObject messageObject){
  if (messageObject == null || !messageObject.needDrawBluredPreview()) {
    return;
  }
  String str=messageObject.getSecretTimeString();
  if (str == null) {
    return;
  }
  infoWidth=(int)Math.ceil(Theme.chat_infoPaint.measureText(str));
  CharSequence str2=TextUtils.ellipsize(str,Theme.chat_infoPaint,infoWidth,TextUtils.TruncateAt.END);
  infoLayout=new StaticLayout(str2,Theme.chat_infoPaint,infoWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
  invalidate();
}
