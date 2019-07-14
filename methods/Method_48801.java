@Override public GraphComputer program(final VertexProgram vertexProgram){
  Preconditions.checkState(this.vertexProgram == null,"A vertex program has already been set");
  this.vertexProgram=vertexProgram;
  return this;
}
