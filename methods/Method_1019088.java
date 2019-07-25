/** 
 * Create label from string.
 * @param value String value.
 * @return Label of string.
 */
private static Label text(String value){
  Label text=new Label(value);
  text.getStyleClass().add("code-fmt");
  return text;
}
