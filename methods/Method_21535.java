public String getOpertatorSymbol() throws SqlParseException {
switch (opear) {
case EQ:
    return "==";
case GT:
  return ">";
case LT:
return "<";
case GTE:
return ">=";
case LTE:
return "<=";
case N:
return "<>";
case IS:
return "==";
case ISN:
return "!=";
default :
throw new SqlParseException(opear + " is err!");
}
}
