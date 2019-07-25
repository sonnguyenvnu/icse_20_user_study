private static CodePoints load(Charset c){
  if (!c.canEncode())   throw new IllegalArgumentException("Charset " + c.name() + " does not support encoding");
  return encodableCodePoints(c.newEncoder());
}
