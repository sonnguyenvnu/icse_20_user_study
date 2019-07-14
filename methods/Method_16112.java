@Override public Strategy createStrategyIfMatch(Class target,Method method){
  if (switcher.isEmpty()) {
    return null;
  }
  String text=target.getName().concat(".").concat(method.getName());
  return switcher.entrySet().stream().filter(entry -> antPathMatcher.match(entry.getValue().getExpression(),text)).peek(entry -> entry.getValue().setId(entry.getKey())).map(Map.Entry::getValue).findFirst().orElse(null);
}
