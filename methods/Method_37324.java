@Override public String convert(final Object value){
  if (value == null) {
    return null;
  }
  if (value instanceof CharSequence) {
    return value.toString();
  }
  Class type=value.getClass();
  if (type == Class.class) {
    return ((Class)value).getName();
  }
  if (type.isArray()) {
    if (type == char[].class) {
      char[] charArray=(char[])value;
      return new String(charArray);
    }
    if (type == int[].class) {
      return ArraysUtil.toString((int[])value);
    }
    if (type == long[].class) {
      return ArraysUtil.toString((long[])value);
    }
    if (type == byte[].class) {
      return ArraysUtil.toString((byte[])value);
    }
    if (type == float[].class) {
      return ArraysUtil.toString((float[])value);
    }
    if (type == double[].class) {
      return ArraysUtil.toString((double[])value);
    }
    if (type == short[].class) {
      return ArraysUtil.toString((short[])value);
    }
    if (type == boolean[].class) {
      return ArraysUtil.toString((boolean[])value);
    }
    return ArraysUtil.toString((Object[])value);
  }
  if (value instanceof Clob) {
    Clob clob=(Clob)value;
    try {
      long length=clob.length();
      if (length > Integer.MAX_VALUE) {
        throw new TypeConversionException("Clob is too big.");
      }
      return clob.getSubString(1,(int)length);
    }
 catch (    SQLException sex) {
      throw new TypeConversionException(value,sex);
    }
  }
  return value.toString();
}
