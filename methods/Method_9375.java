private void onFieldError(View field){
  if (field == null) {
    return;
  }
  Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
  if (v != null) {
    v.vibrate(200);
  }
  AndroidUtilities.shakeView(field,2,0);
  scrollToField(field);
}
