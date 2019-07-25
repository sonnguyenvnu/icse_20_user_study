/** 
 * {@inheritDoc}
 */
@Override public void update(final WebDockableFrame c,final String key,final Value value,final Object... data){
  c.setTitle(getDefaultText(value,data));
}
