public Map<String,Object> parse(final String input){
  final Match m=instance.match(input);
  if (m == Match.EMPTY) {
    return ImmutableMap.of();
  }
  m.captures();
  return m.toMap();
}
