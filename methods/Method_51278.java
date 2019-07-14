@Override public String dysfunctionReason(){
  return hasXPathExpression() ? null : "Missing xPath expression";
}
