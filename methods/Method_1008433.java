@Override public String text() throws IOException {
  if (currentToken().isValue()) {
    return parser.getText();
  }
  throw new IllegalStateException("Can't get text on a " + currentToken() + " at " + getTokenLocation());
}
