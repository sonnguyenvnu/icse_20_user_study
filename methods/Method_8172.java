@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    AndroidUtilities.runOnUIThread(new Runnable(){
      @Override public void run(){
        if (firstNameField != null) {
          firstNameField.requestFocus();
          AndroidUtilities.showKeyboard(firstNameField);
        }
      }
    }
,100);
  }
}
