private boolean usePromptText(){
  Object txt=getControlValue();
  String promptTxt=promptTextProperty.getValue();
  boolean isLabelFloat=control.isLabelFloat();
  return isLabelFloat || (promptTxt != null && (txt == null || txt.toString().isEmpty()) && !promptTxt.isEmpty() && !promptTextFill.get().equals(Color.TRANSPARENT));
}
