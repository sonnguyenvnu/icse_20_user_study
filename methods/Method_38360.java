/** 
 * Finds macros end.
 */
protected int findMacroEnd(final String template,final int fromIndex){
  int endIndex=template.indexOf('}',fromIndex);
  if (endIndex == -1) {
    throw new DbSqlBuilderException("Template syntax error, some macros are not closed. Error at: '..." + template.substring(fromIndex));
  }
  return endIndex;
}
