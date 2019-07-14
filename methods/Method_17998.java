private static boolean areArraysEquals(Class<?> classType,Object val1,Object val2){
  final Class<?> innerClassType=classType.getComponentType();
  if (Byte.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((byte[])val1,(byte[])val2)) {
      return false;
    }
  }
 else   if (Short.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((short[])val1,(short[])val2)) {
      return false;
    }
  }
 else   if (Character.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((char[])val1,(char[])val2)) {
      return false;
    }
  }
 else   if (Integer.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((int[])val1,(int[])val2)) {
      return false;
    }
  }
 else   if (Long.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((long[])val1,(long[])val2)) {
      return false;
    }
  }
 else   if (Float.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((float[])val1,(float[])val2)) {
      return false;
    }
  }
 else   if (Double.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((double[])val1,(double[])val2)) {
      return false;
    }
  }
 else   if (Boolean.TYPE.isAssignableFrom(innerClassType)) {
    if (!Arrays.equals((boolean[])val1,(boolean[])val2)) {
      return false;
    }
  }
 else   if (!Arrays.equals((Object[])val1,(Object[])val2)) {
    return false;
  }
  return true;
}
