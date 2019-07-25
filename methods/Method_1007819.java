@Nullable @Override public Integer apply(String input){
  if (input == null) {
    return null;
  }
  if (replacePattern != null) {
    input=replacePattern.matcher(input).replaceAll("");
  }
  if (caseSensitive) {
    return distance(baseString,input);
  }
 else {
    return distance(baseString,input.toLowerCase(Locale.ROOT));
  }
}
