private static Object neg(final Object unary){
  if (unary instanceof Double) {
    return -(double)unary;
  }
 else   if (unary instanceof Long) {
    return -(long)unary;
  }
 else   if (unary instanceof Integer) {
    return -(int)unary;
  }
 else   if (unary instanceof Float) {
    return -(float)unary;
  }
 else   if (unary instanceof Short) {
    return -(short)unary;
  }
 else   if (unary instanceof Character) {
    return -(char)unary;
  }
 else   if (unary instanceof Byte) {
    return -(byte)unary;
  }
  throw new ClassCastException("Cannot apply [-] operation to type " + "[" + unary.getClass().getCanonicalName() + "].");
}
