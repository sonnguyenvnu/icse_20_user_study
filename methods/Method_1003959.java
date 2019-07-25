@Override public void serialize(TokenStreamSerializer.AttributeOutputStream output) throws IOException {
  output.writeVInt(termAtt.getOffset());
  output.writeVInt(termAtt.getLength());
}
