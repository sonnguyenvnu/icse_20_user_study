public void setValue(int value){
  if (callChangeListener(value)) {
    selectedColor=value;
    persistInt(value);
    notifyChanged();
  }
}
