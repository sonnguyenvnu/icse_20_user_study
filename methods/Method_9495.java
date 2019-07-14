public static boolean isShowingImage(MessageObject object){
  boolean result=false;
  if (Instance != null) {
    result=!Instance.pipAnimationInProgress && Instance.isVisible && !Instance.disableShowCheck && object != null && Instance.currentMessageObject != null && Instance.currentMessageObject.getId() == object.getId() && Instance.currentMessageObject.getDialogId() == object.getDialogId();
  }
  if (!result && PipInstance != null) {
    result=PipInstance.isVisible && !PipInstance.disableShowCheck && object != null && PipInstance.currentMessageObject != null && PipInstance.currentMessageObject.getId() == object.getId() && PipInstance.currentMessageObject.getDialogId() == object.getDialogId();
  }
  return result;
}
