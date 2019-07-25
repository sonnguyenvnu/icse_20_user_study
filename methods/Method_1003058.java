private void read(int tokenType){
  if (tokenType != currentTokenType) {
    addExpected(tokenType);
    throw getSyntaxError();
  }
  read();
}
