/** 
 * {@inheritDoc}
 */
@Override public void update(final WebCollapsiblePane c,final String key,final Value value,final Object... data){
  final String text=getDefaultText(value,data);
  c.setTitle(text != null ? text : null);
}
