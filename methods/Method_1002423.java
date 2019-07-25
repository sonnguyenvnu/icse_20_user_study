private void validate(Token token){
  if ((_expectedTokens & token.bitPattern) == 0) {
    handleException(new Exception("Expecting " + joinTokens(_expectedTokens) + " but got " + token + " at " + _jsonParser.getTokenLocation()));
  }
}
