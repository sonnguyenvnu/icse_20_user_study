/** 
 * {@inheritDoc}
 */
@Override public void update(final WebTextField c,final String key,final Value value,final Object... data){
  final String text=getDefaultText(value,data);
  if (text != null) {
    c.setText(text);
  }
  final String inputPrompt=getDefaultText(INPUT_PROMPT,value,data);
  if (inputPrompt != null) {
    c.setInputPrompt(inputPrompt);
  }
}
