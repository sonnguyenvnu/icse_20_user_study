protected ParsingException makeException(String desc){
  int index=_token != null ? _token.start : _scanner.getIndex();
  return new ParsingException("Parsing error at offset " + index + ": " + desc);
}
