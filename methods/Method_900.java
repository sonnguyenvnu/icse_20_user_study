private boolean variableNamingStartOrEndWithDollarAndUnderLine(String variable){
  return variable.startsWith(StringAndCharConstants.DOLLAR) || variable.startsWith(StringAndCharConstants.UNDERSCORE);
}
