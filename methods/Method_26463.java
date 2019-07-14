private static String suggestedNameFollowedWithT(String identifier){
  Preconditions.checkArgument(!identifier.isEmpty());
  if (identifier.length() > 2 && identifier.charAt(0) == 'T' && Ascii.isUpperCase(identifier.charAt(1)) && Ascii.isLowerCase(identifier.charAt(2))) {
    ImmutableList<String> tokens=NamingConventions.splitToLowercaseTerms(identifier.substring(1));
    return Streams.concat(tokens.stream(),Stream.of("T")).map(TypeParameterNaming::upperCamelToken).collect(Collectors.joining());
  }
  ImmutableList<String> tokens=NamingConventions.splitToLowercaseTerms(identifier);
  if (tokens.size() == 1) {
    String token=tokens.get(0);
    if (Ascii.toUpperCase(token).equals(identifier)) {
      return upperCamelToken(token) + "T";
    }
  }
  if (Iterables.getLast(tokens).equals("type")) {
    return Streams.concat(tokens.subList(0,tokens.size() - 1).stream(),Stream.of("T")).map(TypeParameterNaming::upperCamelToken).collect(Collectors.joining());
  }
  return identifier + "T";
}
