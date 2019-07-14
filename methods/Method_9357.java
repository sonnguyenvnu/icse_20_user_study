@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && type != 0) {
    AndroidUtilities.showKeyboard(passwordEditText);
  }
}
