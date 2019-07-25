@Override public boolean supports(MethodParameter returnType,Class converterType){
  if (returnType.getMethod() == null) {
    return false;
  }
  return returnType.getMethod().getReturnType() == CommonResult.class;
}
