private void postBackspaceRunnable(final int time){
  AndroidUtilities.runOnUIThread(() -> {
    if (!backspacePressed) {
      return;
    }
    if (delegate != null && delegate.onBackspace()) {
      backspaceButton.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
    }
    backspaceOnce=true;
    postBackspaceRunnable(Math.max(50,time - 100));
  }
,time);
}
