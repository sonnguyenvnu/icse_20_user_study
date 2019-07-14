private static <E extends Enum<E>>E toEnum(String propName,String propValue,Class<E> enumType,E... values) throws IllegalArgumentException {
  try {
    return Enum.valueOf(enumType,propValue);
  }
 catch (  NullPointerException npe) {
    throw createBadEnumError(propName,propValue,values);
  }
catch (  IllegalArgumentException e) {
    throw createBadEnumError(propName,propValue,values);
  }
}
