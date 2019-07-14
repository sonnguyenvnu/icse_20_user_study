private Token createMatch(Emit emit,String text){
  return new MatchToken(text.substring(emit.getStart(),emit.getEnd() + 1),emit);
}
