static Long binop(Kind kind,long lhs,long rhs){
switch (kind) {
case MULTIPLY:
    return LongMath.checkedMultiply(lhs,rhs);
case DIVIDE:
  return lhs / rhs;
case REMAINDER:
return lhs % rhs;
case PLUS:
return LongMath.checkedAdd(lhs,rhs);
case MINUS:
return LongMath.checkedSubtract(lhs,rhs);
case LEFT_SHIFT:
return lhs << rhs;
case RIGHT_SHIFT:
return lhs >> rhs;
case UNSIGNED_RIGHT_SHIFT:
return lhs >>> rhs;
case AND:
return lhs & rhs;
case XOR:
return lhs ^ rhs;
case OR:
return lhs | rhs;
default :
return null;
}
}
