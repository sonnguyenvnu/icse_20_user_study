public RequestPatternBuilder withAllRequestBodyParts(MultipartValuePatternBuilder multiPatternBuilder){
  return withRequestBodyPart(multiPatternBuilder.matchingType(MultipartValuePattern.MatchingType.ALL).build());
}
