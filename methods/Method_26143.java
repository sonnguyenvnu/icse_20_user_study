static String assignmentToString(Tree.Kind kind){
switch (kind) {
case MULTIPLY:
    return "*";
case DIVIDE:
  return "/";
case REMAINDER:
return "%";
case PLUS:
return "+";
case MINUS:
return "-";
case LEFT_SHIFT:
return "<<";
case AND:
return "&";
case XOR:
return "^";
case OR:
return "|";
case RIGHT_SHIFT:
return ">>";
case UNSIGNED_RIGHT_SHIFT:
return ">>>";
default :
throw new IllegalArgumentException("Unexpected operator assignment kind: " + kind);
}
}
