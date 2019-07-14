@Override public BasicMappingBuilder withMultipartRequestBody(MultipartValuePatternBuilder multipartPatternBuilder){
  requestPatternBuilder.withRequestBodyPart(multipartPatternBuilder.build());
  return this;
}
