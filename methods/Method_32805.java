public boolean readSpecialButton(SpecialButton name){
  SpecialButtonState state=specialButtons.get(name);
  if (state == null)   throw new RuntimeException("Must be a valid special button (see source)");
  if (!state.isOn)   return false;
  if (state.button == null) {
    return false;
  }
  if (state.button.isPressed())   return true;
  if (!state.button.isChecked())   return false;
  state.button.setChecked(false);
  state.button.setTextColor(TEXT_COLOR);
  return true;
}
