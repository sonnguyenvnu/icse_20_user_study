public CharSequence getFieldText(){
  if (hasText()) {
    return messageEditText.getText();
  }
  return null;
}
