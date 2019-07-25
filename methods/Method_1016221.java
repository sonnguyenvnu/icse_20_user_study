/** 
 * {@inheritDoc}
 */
@Override public void update(final WebFormattedTextField c,final String key,final Value value,final Object... data){
  c.setText(getDefaultText(value,data));
  c.setInputPrompt(getDefaultText(INPUT_PROMPT,value,data));
}
