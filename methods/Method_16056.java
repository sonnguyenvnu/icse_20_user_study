public static boolean compare(Enum e,Object target){
  if (e == target) {
    return true;
  }
  if (e == null || target == null) {
    return false;
  }
  String stringValue=String.valueOf(target);
  if (e instanceof EnumDict) {
    EnumDict dict=((EnumDict)e);
    return e.name().equalsIgnoreCase(stringValue) || dict.eq(target);
  }
  return e.name().equalsIgnoreCase(stringValue);
}
