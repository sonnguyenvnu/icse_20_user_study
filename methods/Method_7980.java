@Override public void didSetImage(ImageReceiver imageReceiver,boolean set,boolean thumb){
  if (currentMessageObject != null && set && !thumb && !currentMessageObject.mediaExists && !currentMessageObject.attachPathExists && (currentMessageObject.type == 0 && (documentAttachType == DOCUMENT_ATTACH_TYPE_WALLPAPER || documentAttachType == DOCUMENT_ATTACH_TYPE_NONE || documentAttachType == DOCUMENT_ATTACH_TYPE_STICKER) || currentMessageObject.type == 1)) {
    currentMessageObject.mediaExists=true;
    updateButtonState(false,true,false);
  }
}
