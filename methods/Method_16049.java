protected String createLockName(String expression){
  try {
    if (StringUtils.isEmpty(expression)) {
      return interceptorHolder.getMethod().getName().concat("_").concat(interceptorHolder.getId());
    }
    return ExpressionUtils.analytical(expression,interceptorHolder.getArgs(),"spel");
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
