/** 
 * {@inheritDoc}
 */
@Override public void pushName(final String name,final boolean withComma){
  JsonValueContext valueContext=peekValueContext();
  if (valueContext != null) {
    valueContext.setPropertyName(name);
  }
  super.pushName(name,withComma);
}
