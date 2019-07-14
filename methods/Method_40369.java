@Override public Object visitBoolOp(org.python.antlr.ast.BoolOp n) throws Exception {
  BoolOp.OpType op;
switch (n.getInternalOp()) {
case And:
    op=BoolOp.OpType.AND;
  break;
case Or:
op=BoolOp.OpType.OR;
break;
default :
op=BoolOp.OpType.UNDEFINED;
break;
}
return new BoolOp(op,convertListExpr(n.getInternalValues()),start(n),stop(n));
}
