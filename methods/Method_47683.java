private void updateText(){
  if (realText.isEmpty() && !isFocused()) {
    setTextColor(exampleColor);
    setText(example);
    setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
  }
 else {
    setText(realText);
    setTextColor(color);
    setInputType(inputType);
  }
}
