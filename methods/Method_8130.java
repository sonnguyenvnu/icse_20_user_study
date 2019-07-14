public void setChecked(boolean checked){
  if (optionsButton == null) {
    return;
  }
  optionsButton.setVisibility(checked ? VISIBLE : INVISIBLE);
}
