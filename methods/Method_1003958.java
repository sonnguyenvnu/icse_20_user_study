@Override public void initialize(AttributeSource attributeSource,TokenStreamSerializer.Version version) throws IOException {
  termAtt=attributeSource.addAttribute(CharSequenceTermAttribute.class);
  this.encodingVersion=version;
}
