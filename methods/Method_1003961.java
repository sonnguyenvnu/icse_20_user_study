@Override public void initialize(AttributeSource attributeSource,TokenStreamSerializer.Version version) throws IOException {
  this.posIncrAttribute=attributeSource.addAttribute(PositionIncrementAttribute.class);
}
