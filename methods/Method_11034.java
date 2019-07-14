@Override protected void onValueChanged(float value){
  if (colorPicker != null) {
    colorPicker.setAlphaValue(value);
  }
}
