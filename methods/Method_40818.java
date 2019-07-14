@Override public Value interp(Scope s){
  return new Vector(interpList(elements,s));
}
