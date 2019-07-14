@Override public BasicMappingBuilder withRequestBody(ContentPattern<?> bodyPattern){
  requestPatternBuilder.withRequestBody(bodyPattern);
  return this;
}
