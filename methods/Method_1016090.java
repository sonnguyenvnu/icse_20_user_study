/** 
 * {@inheritDoc}
 */
@Override public void update(final JFileChooser c,final String key,final Value value,final Object... data){
  c.setDialogTitle(getDefaultText(value,data));
}
