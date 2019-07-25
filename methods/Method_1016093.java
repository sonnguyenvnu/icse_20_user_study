/** 
 * {@inheritDoc}
 */
@Override public void update(final JProgressBar c,final String key,final Value value,final Object... data){
  c.setString(getDefaultText(value,data));
}
