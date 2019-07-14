private void updateCaptionTextForCurrentPhoto(Object object){
  CharSequence caption=null;
  if (object instanceof MediaController.PhotoEntry) {
    caption=((MediaController.PhotoEntry)object).caption;
  }
 else   if (object instanceof TLRPC.BotInlineResult) {
  }
 else   if (object instanceof MediaController.SearchImage) {
    caption=((MediaController.SearchImage)object).caption;
  }
  if (TextUtils.isEmpty(caption)) {
    captionEditText.setFieldText("");
  }
 else {
    captionEditText.setFieldText(caption);
  }
}
