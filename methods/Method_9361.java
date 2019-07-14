private void onPasscodeError(){
  if (getParentActivity() == null) {
    return;
  }
  Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
  if (v != null) {
    v.vibrate(200);
  }
  AndroidUtilities.shakeView(titleTextView,2,0);
}
