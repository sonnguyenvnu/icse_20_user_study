/** 
 * Determines whether two expressions refer to the same variable. Note that returning false doesn't necessarily mean the expressions do *not* refer to the same field. We don't attempt to do any complex analysis here, just catch the obvious cases.
 */
public static boolean sameVariable(ExpressionTree expr1,ExpressionTree expr2){
  requireNonNull(expr1);
  requireNonNull(expr2);
  if ((expr1.getKind() != Kind.IDENTIFIER && expr1.getKind() != Kind.MEMBER_SELECT) || (expr2.getKind() != Kind.IDENTIFIER && expr2.getKind() != Kind.MEMBER_SELECT)) {
    return false;
  }
  Symbol sym1=getSymbol(expr1);
  Symbol sym2=getSymbol(expr2);
  if (sym1 == null) {
    throw new IllegalStateException("Couldn't get symbol for " + expr1);
  }
 else   if (sym2 == null) {
    throw new IllegalStateException("Couldn't get symbol for " + expr2);
  }
  if (expr1.getKind() == Kind.IDENTIFIER && expr2.getKind() == Kind.IDENTIFIER) {
    return sym1.equals(sym2);
  }
 else   if (expr1.getKind() == Kind.MEMBER_SELECT && expr2.getKind() == Kind.MEMBER_SELECT) {
    return sym1.equals(sym2) && sameVariable(((JCFieldAccess)expr1).selected,((JCFieldAccess)expr2).selected);
  }
 else {
    ExpressionTree selected=null;
    if (expr1.getKind() == Kind.IDENTIFIER) {
      selected=((JCFieldAccess)expr2).selected;
    }
 else {
      selected=((JCFieldAccess)expr1).selected;
    }
    return selected.toString().equals("this") && sym1.equals(sym2);
  }
}
