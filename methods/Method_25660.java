private ClassTree getTopLevelClass(VisitorState state){
  return (ClassTree)Streams.findLast(Streams.stream(state.getPath().iterator()).filter((  Tree t) -> t.getKind() == Kind.CLASS)).orElseThrow(() -> new IllegalArgumentException("No enclosing class found"));
}
