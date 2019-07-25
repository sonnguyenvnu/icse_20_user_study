/** 
 * {@inheritDoc}
 */
@Override public void update(final JLabel c,final String key,final Value value,final Object... data){
  c.setText(getDefaultText(value,data));
}
