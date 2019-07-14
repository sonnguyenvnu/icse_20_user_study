/** 
 * this method is called when the text property is changed when the field is not focused (changed in code)
 * @param up direction of the prompt label
 */
private void animateFloatingLabel(boolean up,boolean animation){
  if (promptTextSupplier.get() == null) {
    return;
  }
  if (up) {
    if (promptTextSupplier.get().getTranslateY() != -contentHeight) {
      unfocusTimer.stop();
      runTimer(focusTimer,animation);
    }
  }
 else {
    if (promptTextSupplier.get().getTranslateY() != 0) {
      focusTimer.stop();
      runTimer(unfocusTimer,animation);
    }
  }
}
