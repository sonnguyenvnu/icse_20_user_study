private void showFingerprintError(CharSequence error){
  fingerprintImageView.setImageResource(R.drawable.ic_fingerprint_error);
  fingerprintStatusTextView.setText(error);
  fingerprintStatusTextView.setTextColor(0xfff4511e);
  Vibrator v=(Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
  if (v != null) {
    v.vibrate(200);
  }
  AndroidUtilities.shakeView(fingerprintStatusTextView,2,0);
}
