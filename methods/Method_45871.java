private String asArgument(Class<?> cl,String name){
  if (cl.isPrimitive()) {
    if (Boolean.TYPE == cl) {
      return name + "==null?false:((Boolean)" + name + ").booleanValue()";
    }
    if (Byte.TYPE == cl) {
      return name + "==null?(byte)0:((Byte)" + name + ").byteValue()";
    }
    if (Character.TYPE == cl) {
      return name + "==null?(char)0:((Character)" + name + ").charValue()";
    }
    if (Double.TYPE == cl) {
      return name + "==null?(double)0:((Double)" + name + ").doubleValue()";
    }
    if (Float.TYPE == cl) {
      return name + "==null?(float)0:((Float)" + name + ").floatValue()";
    }
    if (Integer.TYPE == cl) {
      return name + "==null?(int)0:((Integer)" + name + ").intValue()";
    }
    if (Long.TYPE == cl) {
      return name + "==null?(long)0:((Long)" + name + ").longValue()";
    }
    if (Short.TYPE == cl) {
      return name + "==null?(short)0:((Short)" + name + ").shortValue()";
    }
    throw new RuntimeException(name + " is unknown primitive type.");
  }
  return "(" + ClassTypeUtils.getTypeStr(cl) + ")" + name;
}
