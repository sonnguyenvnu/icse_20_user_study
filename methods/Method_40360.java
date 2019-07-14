public String convOp(Object t){
  if (t instanceof operatorType) {
switch ((operatorType)t) {
case Add:
      return "+";
case Sub:
    return "-";
case Mult:
  return "*";
case Div:
return "/";
case Mod:
return "%";
case Pow:
return "**";
case LShift:
return "<<";
case RShift:
return ">>";
case BitOr:
return "|";
case BitXor:
return "^";
case BitAnd:
return "&";
case FloorDiv:
return "//";
default :
return null;
}
}
if (t instanceof boolopType) {
switch ((boolopType)t) {
case And:
return "and";
case Or:
return "or";
default :
return null;
}
}
if (t instanceof unaryopType) {
switch ((unaryopType)t) {
case Invert:
return "~";
case Not:
return "not";
case USub:
return "-";
case UAdd:
return "+";
default :
return null;
}
}
if (t instanceof cmpopType) {
switch ((cmpopType)t) {
case Eq:
return "==";
case NotEq:
return "!=";
case Gt:
return ">";
case GtE:
return ">=";
case Lt:
return "<";
case LtE:
return "<=";
case In:
return "in";
case NotIn:
return "not in";
case Is:
return "is";
case IsNot:
return "is not";
default :
return null;
}
}
return null;
}
