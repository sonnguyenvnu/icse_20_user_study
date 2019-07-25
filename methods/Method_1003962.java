@Override public void serialize(TokenStreamSerializer.AttributeOutputStream output) throws IOException {
  output.writeVInt(posIncrAttribute.getPositionIncrement());
}
