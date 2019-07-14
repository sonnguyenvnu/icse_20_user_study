@Override public boolean supportsReturnType(MethodParameter returnType){
  return Properties.class.equals(returnType.getMethod().getReturnType());
}
