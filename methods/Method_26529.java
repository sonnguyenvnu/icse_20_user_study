public Bindings unmodifiable(){
  return new Bindings(Collections.unmodifiableMap(contents));
}
