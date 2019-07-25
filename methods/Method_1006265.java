public static Optional<ArXivIdentifier> parse(String value){
  Pattern identifierPattern=Pattern.compile("(arxiv|arXiv)?\\s?:?\\s?(?<id>\\d{4}.\\d{4,5}(v\\d+)?)\\s?(\\[(?<classification>\\S+)\\])?");
  Matcher identifierMatcher=identifierPattern.matcher(value);
  if (identifierMatcher.matches()) {
    String id=identifierMatcher.group("id");
    String classification=identifierMatcher.group("classification");
    if (classification == null) {
      classification="";
    }
    return Optional.of(new ArXivIdentifier(id,classification));
  }
  Pattern oldIdentifierPattern=Pattern.compile("(arxiv|arXiv)?\\s?:?\\s?(?<id>(?<classification>[a-z\\-]+(\\.[A-Z]{2})?)/\\d{7})");
  Matcher oldIdentifierMatcher=oldIdentifierPattern.matcher(value);
  if (oldIdentifierMatcher.matches()) {
    String id=oldIdentifierMatcher.group("id");
    String classification=oldIdentifierMatcher.group("classification");
    return Optional.of(new ArXivIdentifier(id,classification));
  }
  Pattern urlPattern=Pattern.compile("(http://arxiv.org/abs/)(?<id>\\S+)");
  Matcher urlMatcher=urlPattern.matcher(value);
  if (urlMatcher.matches()) {
    String id=urlMatcher.group("id");
    return Optional.of(new ArXivIdentifier(id));
  }
  return Optional.empty();
}
