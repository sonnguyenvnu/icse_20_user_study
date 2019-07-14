@Override protected void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && !backward && addingNewProxy) {
    inputFields[FIELD_IP].requestFocus();
    AndroidUtilities.showKeyboard(inputFields[FIELD_IP]);
  }
}
