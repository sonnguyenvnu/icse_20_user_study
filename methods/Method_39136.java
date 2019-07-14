/** 
 * {@inheritDoc}
 */
@Override public int match(final String actionPath){
  String[] values=process(actionPath,true);
  if (values == null) {
    return -1;
  }
  int macroChars=0;
  for (  String value : values) {
    if (value != null) {
      macroChars+=value.length();
    }
  }
  return actionPath.length() - macroChars;
}
