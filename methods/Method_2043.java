public void updateCurrentValue(int progress){
  if (shouldPersist()) {
    persistInt(progress);
  }
  if (progress != mProgressValue) {
    mProgressValue=progress;
    notifyChanged();
  }
  if (mSeekBarValueTextView != null) {
    final String valueStr=getContext().getString(R.string.size_label_format,progress);
    mSeekBarValueTextView.setText(valueStr);
  }
}
