private void unFocus(){
  focusTimer.stop();
  scale.setX(initScale);
  focusedLine.setOpacity(0);
  if (control.isLabelFloat()) {
    animatedPromptTextFill.set(promptTextFill.get());
    Object text=getControlValue();
    if (text == null || text.toString().isEmpty()) {
      animating=true;
      runTimer(unfocusTimer,true);
    }
  }
}
