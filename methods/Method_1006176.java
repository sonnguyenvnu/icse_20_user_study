private int read() throws IOException {
  int character=pushbackReader.read();
  if (!isEOFCharacter(character)) {
    pureTextFromFile.offerLast((char)character);
  }
  if (character == '\n') {
    line++;
  }
  return character;
}
