public static boolean compare(String string,Object target){
  if (string == target) {
    return true;
  }
  if (string == null || target == null) {
    return false;
  }
  if (string.equals(String.valueOf(target))) {
    return true;
  }
  if (target instanceof Enum) {
    return compare(((Enum)target),string);
  }
  if (target instanceof Date) {
    return compare(((Date)target),string);
  }
  if (target instanceof Number) {
    return compare(((Number)target),string);
  }
  if (target instanceof Collection) {
    return compare(((Collection)target),string);
  }
  return false;
}
