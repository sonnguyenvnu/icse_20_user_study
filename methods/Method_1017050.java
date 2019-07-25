public Optional<Expression> keyword(final String key){
  return Optional.ofNullable(keywords.get(key));
}
