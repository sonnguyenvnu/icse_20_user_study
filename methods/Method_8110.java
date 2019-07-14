public void setLink(MessageObject messageObject,boolean divider){
  needDivider=divider;
  resetPressedLink();
  message=messageObject;
  requestLayout();
}
