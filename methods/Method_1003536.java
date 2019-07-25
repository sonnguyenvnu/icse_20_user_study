@Override public boolean matches(List<Type> sourceTypes,Type targetType){
  if (!targetType.equals(returnType)) {
    return false;
  }
  if (parameters.size() != sourceTypes.size()) {
    return false;
  }
  Iterator<Type> srcTypeIt=sourceTypes.iterator();
  Iterator<Parameter> paramIt=parameters.iterator();
  while (srcTypeIt.hasNext() && paramIt.hasNext()) {
    Type sourceType=srcTypeIt.next();
    Parameter param=paramIt.next();
    if (!sourceType.equals(param.getType())) {
      return false;
    }
  }
  return true;
}
