@Override public void deserialize(TokenStreamSerializer.AttributeInputStream input,CharSequence charSequence) throws IOException {
  termAtt.setCharSequence(charSequence);
  termAtt.setOffset(input.readVInt());
  termAtt.setLength(input.readVInt());
}
