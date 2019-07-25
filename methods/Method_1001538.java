public static RegexConcat build(String key,IRegex... partials){
  RegexConcat result=cache.get(key);
  if (result == null) {
    cache.putIfAbsent(key,buildInternal(partials));
    result=cache.get(key);
  }
  return result;
}
