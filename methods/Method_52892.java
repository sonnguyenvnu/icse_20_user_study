/** 
 * Adjust all the line and column numbers that comprise a node so that they are corrected for the string literals position within the template file. This is neccessary if an exception is thrown while processing the node so that the line and column position reported reflects the error position within the template and not just relative to the error position within the string literal.
 */
public void adjTokenLineNums(final AbstractVmNode node){
  Token tok=node.getFirstToken();
  while (tok != null && tok != node.getLastToken()) {
    if (tok.beginLine == 1) {
      tok.beginColumn+=getColumn();
    }
    if (tok.endLine == 1) {
      tok.endColumn+=getColumn();
    }
    tok.beginLine+=getLine() - 1;
    tok.endLine+=getLine() - 1;
    tok=tok.next;
  }
}
