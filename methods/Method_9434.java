private void shakeView(View view){
  Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
  if (v != null) {
    v.vibrate(200);
  }
  AndroidUtilities.shakeView(view,2,0);
}
