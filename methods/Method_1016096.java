/** 
 * {@inheritDoc}
 */
@Override public void update(final JTextComponent c,final String key,final Value value,final Object... data){
  final String text=getDefaultText(value,data);
  if (text != null) {
    c.setText(text);
  }
}
