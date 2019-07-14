@Override public String toString(){
  if (this == EOF) {
    return "EOF";
  }
  for (  Map.Entry<String,Integer> e : TOKENS.get().entrySet()) {
    if (e.getValue().intValue() == identifier) {
      return e.getKey();
    }
  }
  return "--unkown--";
}
