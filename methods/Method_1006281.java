public static String format(String inField){
  Objects.requireNonNull(inField);
  String toFormat=underscoreMatcher.matcher(inField).replaceAll(replacementChar);
  toFormat=Normalizer.normalize(LaTeX2Unicode.convert(toFormat),Normalizer.Form.NFC);
  return underscorePlaceholderMatcher.matcher(toFormat).replaceAll("_");
}
