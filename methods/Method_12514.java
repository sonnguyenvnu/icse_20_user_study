private static Pattern[] createPatterns(String... keys){
  return Arrays.stream(keys).map(key -> Pattern.compile(key,Pattern.CASE_INSENSITIVE)).toArray(Pattern[]::new);
}
