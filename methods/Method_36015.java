public RequestPatternBuilder withRequestBodyPart(MultipartValuePattern multiPattern){
  if (multiPattern != null) {
    multiparts.add(multiPattern);
  }
  return this;
}
