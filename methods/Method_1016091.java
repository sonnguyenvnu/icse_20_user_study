/** 
 * {@inheritDoc}
 */
@Override public void update(final JInternalFrame c,final String key,final Value value,final Object... data){
  c.setTitle(getDefaultText(value,data));
}
