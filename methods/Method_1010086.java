public Position before(final N node){
  return () -> getProgram().getStart(node);
}
