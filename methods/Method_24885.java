/** 
 * Gets the best node in the subtree for printing.  This really means the next node which could potentially have hiddenBefore data.  It's usually the first printable leaf, but not always.
 * @param includeThisNode     Should this node be included in the search?If false, only descendants are searched.
 * @return the first printable leaf node in an AST
 */
private AST getBestPrintableNode(final AST ast,final boolean includeThisNode){
  AST child;
  if (includeThisNode) {
    child=ast;
  }
 else {
    child=ast.getFirstChild();
  }
  if (child != null) {
switch (child.getType()) {
case CLASS_DEF:
case ENUM_DEF:
case LITERAL_if:
case LITERAL_new:
case LITERAL_for:
case LITERAL_while:
case LITERAL_do:
case LITERAL_break:
case LITERAL_continue:
case LITERAL_return:
case LITERAL_switch:
case LITERAL_try:
case LITERAL_throw:
case LITERAL_synchronized:
case LITERAL_assert:
case BNOT:
case LNOT:
case INC:
case DEC:
case UNARY_MINUS:
case UNARY_PLUS:
      return child;
case MODIFIERS:
    if (child.getFirstChild() == null) {
      return getBestPrintableNode(child.getNextSibling(),false);
    }
  return getBestPrintableNode(child,false);
default :
return getBestPrintableNode(child,false);
}
}
return ast;
}
