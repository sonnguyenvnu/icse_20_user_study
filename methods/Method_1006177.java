private void unread(int character) throws IOException {
  if (character == '\n') {
    line--;
  }
  pushbackReader.unread(character);
  if (pureTextFromFile.getLast() == character) {
    pureTextFromFile.pollLast();
  }
}
