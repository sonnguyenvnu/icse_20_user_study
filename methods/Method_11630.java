private Selector compileSelector(){
switch (expressionType) {
case Css:
    if (expressionParams.length >= 1) {
      return $(expressionValue,expressionParams[0]);
    }
 else {
      return $(expressionValue);
    }
case XPath:
  return xpath(expressionValue);
case Regex:
if (expressionParams.length >= 1) {
  return regex(expressionValue,Integer.parseInt(expressionParams[0]));
}
 else {
  return regex(expressionValue);
}
case JsonPath:
return new JsonPathSelector(expressionValue);
default :
return xpath(expressionValue);
}
}
