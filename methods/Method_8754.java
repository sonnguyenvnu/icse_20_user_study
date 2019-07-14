private boolean updateInputTextView(){
  String text=(mDisplayedValues == null) ? formatNumber(mValue) : mDisplayedValues[mValue - mMinValue];
  if (!TextUtils.isEmpty(text) && !text.equals(mInputText.getText().toString())) {
    mInputText.setText(text);
    return true;
  }
  return false;
}
