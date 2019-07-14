private void addBodySection(List<ContentPattern<?>> bodyPatterns,Body body,ImmutableList.Builder<DiffLine<?>> builder){
  if (bodyPatterns != null && !bodyPatterns.isEmpty()) {
    for (    ContentPattern<?> pattern : bodyPatterns) {
      String formattedBody=formatIfJsonOrXml(pattern,body);
      if (StringValuePattern.class.isAssignableFrom(pattern.getClass())) {
        StringValuePattern stringValuePattern=(StringValuePattern)pattern;
        builder.add(new DiffLine<>("Body",stringValuePattern,formattedBody,pattern.getExpected()));
      }
 else {
        BinaryEqualToPattern nonStringPattern=(BinaryEqualToPattern)pattern;
        builder.add(new DiffLine<>("Body",nonStringPattern,formattedBody.getBytes(),pattern.getExpected()));
      }
    }
  }
}
