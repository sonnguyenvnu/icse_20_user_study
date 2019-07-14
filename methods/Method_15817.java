@Override public String asString(){
  if (asBytes() == null) {
    return null;
  }
  return new String(asBytes());
}
