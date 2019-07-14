public boolean isShowingImage(MessageObject object){
  return isVisible && !disableShowCheck && object != null && currentMessageObject != null && currentMessageObject.getId() == object.getId();
}
