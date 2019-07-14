static Kind regularAssignmentFromCompound(Kind kind){
switch (kind) {
case MULTIPLY_ASSIGNMENT:
    return Kind.MULTIPLY;
case DIVIDE_ASSIGNMENT:
  return Kind.DIVIDE;
case REMAINDER_ASSIGNMENT:
return Kind.REMAINDER;
case PLUS_ASSIGNMENT:
return Kind.PLUS;
case MINUS_ASSIGNMENT:
return Kind.MINUS;
case LEFT_SHIFT_ASSIGNMENT:
return Kind.LEFT_SHIFT;
case AND_ASSIGNMENT:
return Kind.AND;
case XOR_ASSIGNMENT:
return Kind.XOR;
case OR_ASSIGNMENT:
return Kind.OR;
case RIGHT_SHIFT_ASSIGNMENT:
return Kind.RIGHT_SHIFT;
case UNSIGNED_RIGHT_SHIFT_ASSIGNMENT:
return Kind.UNSIGNED_RIGHT_SHIFT;
default :
throw new IllegalArgumentException("Unexpected compound assignment kind: " + kind);
}
}
