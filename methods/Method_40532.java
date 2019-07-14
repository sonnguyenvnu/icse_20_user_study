public Type getMapping(Type from){
  for (  Arrow a : arrows) {
    if (from.equals(a.from)) {
      return a.to;
    }
  }
  return null;
}
