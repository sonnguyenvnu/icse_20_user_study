public boolean hasFallbackMethodCommand(){
  return fallbackMethod != null && fallbackMethod.isAnnotationPresent(HystrixCommand.class);
}
