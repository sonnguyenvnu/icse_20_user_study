private void addHeaderSection(Map<String,MultiValuePattern> headerPatterns,HttpHeaders headers,ImmutableList.Builder<DiffLine<?>> builder){
  boolean anyHeaderSections=false;
  if (headerPatterns != null && !headerPatterns.isEmpty()) {
    anyHeaderSections=true;
    for (    String key : headerPatterns.keySet()) {
      HttpHeader header=headers.getHeader(key);
      MultiValuePattern headerPattern=headerPatterns.get(header.key());
      String operator=generateOperatorString(headerPattern.getValuePattern(),"");
      String printedPatternValue=header.key() + operator + ": " + headerPattern.getExpected();
      DiffLine<MultiValue> section=new DiffLine<>("Header",headerPattern,header,printedPatternValue);
      builder.add(section);
    }
  }
  if (anyHeaderSections) {
    builder.add(SPACER);
  }
}
