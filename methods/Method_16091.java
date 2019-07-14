@Bean public TableSwitchStrategyMatcher alwaysNoMatchStrategyMatcher(){
  return new TableSwitchStrategyMatcher(){
    @Override public boolean match(    Class target,    Method method){
      return false;
    }
    @Override public Strategy getStrategy(    MethodInterceptorContext context){
      return null;
    }
  }
;
}
