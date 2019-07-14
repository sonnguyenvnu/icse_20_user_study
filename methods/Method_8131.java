public void setOnOptionsClick(OnClickListener listener){
  if (optionsButton == null) {
    return;
  }
  optionsButton.setOnClickListener(listener);
}
