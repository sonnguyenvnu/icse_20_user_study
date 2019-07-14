private void onPasscodeError(){
  Vibrator v=(Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
  if (v != null) {
    v.vibrate(200);
  }
  shakeTextView(2,0);
}
