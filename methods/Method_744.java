public static String getPrimitiveLetter(Class<?> type){
  if (Integer.TYPE == type) {
    return "I";
  }
 else   if (Void.TYPE == type) {
    return "V";
  }
 else   if (Boolean.TYPE == type) {
    return "Z";
  }
 else   if (Character.TYPE == type) {
    return "C";
  }
 else   if (Byte.TYPE == type) {
    return "B";
  }
 else   if (Short.TYPE == type) {
    return "S";
  }
 else   if (Float.TYPE == type) {
    return "F";
  }
 else   if (Long.TYPE == type) {
    return "J";
  }
 else   if (Double.TYPE == type) {
    return "D";
  }
  throw new IllegalStateException("Type: " + type.getCanonicalName() + " is not a primitive type");
}
