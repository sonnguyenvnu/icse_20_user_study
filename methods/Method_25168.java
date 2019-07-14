/** 
 * "Summary" method called by default for every  {@code ValueLiteralNode}. 
 */
Nullness visitValueLiteral(){
  return NULLABLE;
}
