@Override public void updateDrawState(TextPaint ds){
  super.updateDrawState(ds);
  if (currentType == 2) {
    ds.setColor(0xffffffff);
  }
 else   if (currentType == 1) {
    ds.setColor(Theme.getColor(Theme.key_chat_messageLinkOut));
  }
 else {
    ds.setColor(Theme.getColor(Theme.key_chat_messageLinkIn));
  }
  ds.setUnderlineText(false);
}
