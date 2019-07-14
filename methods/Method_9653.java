private void updateKeyView(){
  if (VoIPService.getSharedInstance() == null)   return;
  IdenticonDrawable img=new IdenticonDrawable();
  img.setColors(new int[]{0x00FFFFFF,0xFFFFFFFF,0x99FFFFFF,0x33FFFFFF});
  TLRPC.EncryptedChat encryptedChat=new TLRPC.TL_encryptedChat();
  try {
    ByteArrayOutputStream buf=new ByteArrayOutputStream();
    buf.write(VoIPService.getSharedInstance().getEncryptionKey());
    buf.write(VoIPService.getSharedInstance().getGA());
    encryptedChat.auth_key=buf.toByteArray();
  }
 catch (  Exception checkedExceptionsAreBad) {
  }
  byte[] sha256=Utilities.computeSHA256(encryptedChat.auth_key,0,encryptedChat.auth_key.length);
  String[] emoji=EncryptionKeyEmojifier.emojifyForCall(sha256);
  emojiWrap.setContentDescription(LocaleController.getString("EncryptionKey",R.string.EncryptionKey) + ", " + TextUtils.join(", ",emoji));
  for (int i=0; i < 4; i++) {
    Drawable drawable=Emoji.getEmojiDrawable(emoji[i]);
    if (drawable != null) {
      drawable.setBounds(0,0,AndroidUtilities.dp(22),AndroidUtilities.dp(22));
      keyEmojiViews[i].setImageDrawable(drawable);
    }
  }
}
