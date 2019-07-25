private static boolean gte(Object left,Object right){
  if (left instanceof Number) {
    if (right instanceof Number) {
      if (left instanceof Double || right instanceof Double) {
        return ((Number)left).doubleValue() >= ((Number)right).doubleValue();
      }
 else       if (left instanceof Float || right instanceof Float) {
        return ((Number)left).floatValue() >= ((Number)right).floatValue();
      }
 else       if (left instanceof Long || right instanceof Long) {
        return ((Number)left).longValue() >= ((Number)right).longValue();
      }
 else {
        return ((Number)left).intValue() >= ((Number)right).intValue();
      }
    }
 else     if (right instanceof Character) {
      if (left instanceof Double) {
        return ((Number)left).doubleValue() >= (char)right;
      }
 else       if (left instanceof Long) {
        return ((Number)left).longValue() >= (char)right;
      }
 else       if (left instanceof Float) {
        return ((Number)left).floatValue() >= (char)right;
      }
 else {
        return ((Number)left).intValue() >= (char)right;
      }
    }
  }
 else   if (left instanceof Character) {
    if (right instanceof Number) {
      if (right instanceof Double) {
        return (char)left >= ((Number)right).doubleValue();
      }
 else       if (right instanceof Long) {
        return (char)left >= ((Number)right).longValue();
      }
 else       if (right instanceof Float) {
        return (char)left >= ((Number)right).floatValue();
      }
 else {
        return (char)left >= ((Number)right).intValue();
      }
    }
 else     if (right instanceof Character) {
      return (char)left >= (char)right;
    }
  }
  throw new ClassCastException("Cannot apply [>] operation to types " + "[" + left.getClass().getCanonicalName() + "] and [" + right.getClass().getCanonicalName() + "].");
}
