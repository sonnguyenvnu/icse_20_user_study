@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    if (type == 1) {
      AndroidUtilities.showKeyboard(passwordEditText);
    }
 else     if (type == 0 && codeFieldCell != null && codeFieldCell.getVisibility() == View.VISIBLE) {
      AndroidUtilities.showKeyboard(codeFieldCell.getTextView());
    }
  }
}
