private void updateLabelFloat(boolean animation){
  if (control.isLabelFloat()) {
    if (control.isFocused()) {
      animateFloatingLabel(true,animation);
    }
 else {
      Object text=getControlValue();
      animateFloatingLabel(!(text == null || text.toString().isEmpty()),animation);
    }
  }
}
