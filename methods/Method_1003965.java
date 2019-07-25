@Override public void serialize(TokenStreamSerializer.AttributeOutputStream output) throws IOException {
  output.writeByte(tokenTypeAttribute.getType().ordinal());
}
