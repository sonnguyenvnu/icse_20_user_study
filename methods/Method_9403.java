private void onPasscodeError(boolean clear){
  if (getParentActivity() == null) {
    return;
  }
  Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
  if (v != null) {
    v.vibrate(200);
  }
  if (clear) {
    inputFields[FIELD_PASSWORD].setText("");
  }
  AndroidUtilities.shakeView(inputFields[FIELD_PASSWORD],2,0);
}
