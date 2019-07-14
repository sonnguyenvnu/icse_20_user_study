public boolean contains(Type from){
  for (  Arrow a : arrows) {
    if (Type.subtypeOf(a.from,from)) {
      return true;
    }
  }
  return false;
}
