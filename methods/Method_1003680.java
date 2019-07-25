public PathBinderBuilder token(String token){
  if (addedOptional) {
    throw new IllegalArgumentException(String.format("Cannot add mandatory parameter %s after optional parameters",token));
  }
  addedToken=true;
  tokensBuilder.add(token);
  pattern.append("(?:(?:^|/)([^/?&#]+))");
  appendDescriptionSegment(":").append(token);
  return this;
}
