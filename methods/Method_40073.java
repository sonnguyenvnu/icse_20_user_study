public String getArgumentExpr(){
  StringBuilder argExpr=new StringBuilder();
  argExpr.append("(");
  boolean first=true;
  for (  Node n : args) {
    if (!first) {
      argExpr.append(", ");
    }
    first=false;
    argExpr.append(n.toDisplay());
  }
  if (vararg != null) {
    if (!first) {
      argExpr.append(", ");
    }
    first=false;
    argExpr.append("*" + vararg.toDisplay());
  }
  if (kwarg != null) {
    if (!first) {
      argExpr.append(", ");
    }
    argExpr.append("**" + kwarg.toDisplay());
  }
  argExpr.append(")");
  return argExpr.toString();
}
