static Integer binop(Kind kind,int lhs,int rhs){
switch (kind) {
case MULTIPLY:
    return IntMath.checkedMultiply(lhs,rhs);
case DIVIDE:
  return lhs / rhs;
case REMAINDER:
return lhs % rhs;
case PLUS:
return IntMath.checkedAdd(lhs,rhs);
case MINUS:
return IntMath.checkedSubtract(lhs,rhs);
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
