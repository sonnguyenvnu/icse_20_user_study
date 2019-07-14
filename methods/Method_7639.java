public void finishFragment(boolean animated){
  if (isFinished || parentLayout == null) {
    return;
  }
  finishing=true;
  parentLayout.closeLastFragment(animated);
}
