@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    editText.openKeyboard();
  }
}
