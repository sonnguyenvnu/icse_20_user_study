public Position after(final N node){
  return () -> getProgram().getEnd(node);
}
