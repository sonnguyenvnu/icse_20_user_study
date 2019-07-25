/** 
 * {@inheritDoc}
 */
@Override public void update(final AbstractButton c,final String key,final Value value,final Object... data){
  final String text=getDefaultText(value,data);
  c.setText(text != null ? text : null);
  c.setMnemonic(text != null && value.getMnemonic() != null ? value.getMnemonic() : 0);
}
