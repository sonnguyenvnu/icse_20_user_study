private static String convertTermType(String termType){
  if (termType == null) {
    return TermType.eq;
  }
switch (termType) {
case "=":
    return TermType.eq;
case ">":
  return TermType.gt;
case "<":
return TermType.lt;
case ">=":
return TermType.gte;
case "<=":
return TermType.lte;
default :
return termType;
}
}
