/** 
 * Evaluates all item documents in a particular expression context. This specifies, among others, a row where the values of the variables will be read.
 * @param ctxt the context in which the schema should be evaluated.
 * @return
 */
public List<ItemUpdate> evaluateItemDocuments(ExpressionContext ctxt){
  List<ItemUpdate> result=new ArrayList<>();
  for (  WbItemDocumentExpr expr : itemDocumentExprs) {
    try {
      result.add(expr.evaluate(ctxt));
    }
 catch (    SkipSchemaExpressionException e) {
      continue;
    }
  }
  return result;
}
