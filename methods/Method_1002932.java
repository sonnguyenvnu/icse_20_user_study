private static long mask(BuiltInProperty... elems){
  long mask=0L;
  for (  BuiltInProperty e : elems) {
    mask|=mask(e);
  }
  return mask;
}
