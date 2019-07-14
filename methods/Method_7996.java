public void setCheckBoxVisible(boolean visible,boolean animated){
  if (visible && checkBox == null) {
    checkBox=new CheckBoxBase(this);
    if (attachedToWindow) {
      checkBox.onAttachedToWindow();
    }
  }
  if (visible && photoCheckBox == null && currentMessagesGroup != null && currentMessagesGroup.messages.size() > 1) {
    photoCheckBox=new CheckBoxBase(this);
    photoCheckBox.setUseDefaultCheck(true);
    if (attachedToWindow) {
      photoCheckBox.onAttachedToWindow();
    }
  }
  if (checkBoxVisible == visible) {
    if (animated != checkBoxAnimationInProgress && !animated) {
      checkBoxAnimationProgress=visible ? 1.0f : 0.0f;
      invalidate();
    }
    return;
  }
  checkBoxAnimationInProgress=animated;
  checkBoxVisible=visible;
  if (animated) {
    lastCheckBoxAnimationTime=SystemClock.uptimeMillis();
  }
 else {
    checkBoxAnimationProgress=visible ? 1.0f : 0.0f;
  }
  invalidate();
}
