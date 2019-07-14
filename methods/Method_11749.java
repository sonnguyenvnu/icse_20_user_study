private static boolean equalsRegardingNull(Object expected,Object actual){
  if (expected == null) {
    return actual == null;
  }
  return isEquals(expected,actual);
}
