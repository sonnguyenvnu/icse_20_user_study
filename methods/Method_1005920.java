void bind(BuildConfigValue buildConfigValue){
  logDateTextView.setText(buildConfigValue.getName());
  logMsgTextView.setText(buildConfigValue.getValue());
}
