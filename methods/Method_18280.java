private static boolean isSameComponentType(Component a,Component b){
  if (a == b) {
    return true;
  }
 else   if (a == null || b == null) {
    return false;
  }
  return a.getClass().equals(b.getClass());
}
