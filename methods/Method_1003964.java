@Override public void initialize(AttributeSource attributeSource,TokenStreamSerializer.Version version) throws IOException {
  this.tokenTypeAttribute=attributeSource.addAttribute(TokenTypeAttribute.class);
}
