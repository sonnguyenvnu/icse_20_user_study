public void setIconAndTextAndValue(String text,float value,int min,int max){
  if (valueAnimation != null) {
    valueAnimation.cancel();
    valueAnimation=null;
  }
  AndroidUtilities.cancelRunOnUIThread(hideValueRunnable);
  valueTextView.setTag(null);
  nameTextView.setText(text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase());
  if (value > 0) {
    valueTextView.setText("+" + (int)value);
  }
 else {
    valueTextView.setText("" + (int)value);
  }
  valueTextView.setAlpha(0.0f);
  nameTextView.setAlpha(1.0f);
  seekBar.setMinMax(min,max);
  seekBar.setProgress((int)value,false);
}
