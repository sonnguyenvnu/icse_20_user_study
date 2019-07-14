@NotNull @Override public Type transform(State s){
  Type valueType=transformExpr(operand,s);
  if (op == Op.Add) {
    if (valueType.isNumType()) {
      return valueType;
    }
 else {
      Analyzer.self.putProblem(this,"+ can't be applied to type: " + valueType);
      return Type.INT;
    }
  }
  if (op == Op.Sub) {
    if (valueType.isIntType()) {
      return valueType.asIntType().negate();
    }
 else {
      Analyzer.self.putProblem(this,"- can't be applied to type: " + valueType);
      return Type.INT;
    }
  }
  if (op == Op.Not) {
    if (valueType.isTrue()) {
      return Type.FALSE;
    }
    if (valueType.isFalse()) {
      return Type.TRUE;
    }
    if (valueType.isUndecidedBool()) {
      return valueType.asBool().swap();
    }
  }
  Analyzer.self.putProblem(this,"operator " + op + " cannot be applied to type: " + valueType);
  return Type.UNKNOWN;
}
