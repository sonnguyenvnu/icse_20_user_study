public static boolean isPlayingMessageInPip(MessageObject object){
  return PipInstance != null && object != null && PipInstance.currentMessageObject != null && PipInstance.currentMessageObject.getId() == object.getId() && PipInstance.currentMessageObject.getDialogId() == object.getDialogId();
}
