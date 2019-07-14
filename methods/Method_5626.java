static String parseNextToken(ParsableByteArray input,StringBuilder stringBuilder){
  skipWhitespaceAndComments(input);
  if (input.bytesLeft() == 0) {
    return null;
  }
  String identifier=parseIdentifier(input,stringBuilder);
  if (!"".equals(identifier)) {
    return identifier;
  }
  return "" + (char)input.readUnsignedByte();
}
