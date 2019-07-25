private Token peek(){
  if (position >= tokens.size()) {
    return new NullToken(tokens.get(tokens.size() - 1).getPosition() + 1);
  }
  return tokens.get(position);
}
