/** 
 * {@inheritDoc}
 */
@Override public void writeComma(){
  JsonValueContext valueContext=peekValueContext();
  if (valueContext != null) {
    valueContext.incrementIndex();
  }
  super.writeComma();
}
