protected boolean isVariable(String name){
  return StringUtil.isAnyOf(name,"variableName","methodName","className","packageName") || rule.getPropertyDescriptor(name) != null;
}
