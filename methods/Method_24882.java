/** 
 * Dump the list of hidden tokens linked to from the token passed in.
 */
private void dumpHiddenTokens(CommonHiddenStreamToken t){
  for (; t != null; t=pdePreprocessor.getHiddenAfter(t)) {
    out.print(t.getText());
  }
}
