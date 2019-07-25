/** 
 * {@inheritDoc}
 */
@Override public void update(final JTabbedPane c,final String key,final Value value,final Object... data){
  for (int i=0; i < c.getTabCount(); i++) {
    final String tabKey=useComponentNames ? c.getComponentAt(i).getName() : "" + i;
    final Value tabValue=LanguageManager.getNotNullValue(c,key,tabKey);
    final String text=getDefaultText(tabValue,data);
    c.setTitleAt(i,text != null ? text : null);
    c.setMnemonicAt(i,text != null && value.getMnemonic() != null ? value.getMnemonic() : 0);
  }
}
