private void onFieldError(TextView field,boolean clear){
  if (getParentActivity() == null) {
    return;
  }
  Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
  if (v != null) {
    v.vibrate(200);
  }
  if (clear) {
    field.setText("");
  }
  AndroidUtilities.shakeView(field,2,0);
}
