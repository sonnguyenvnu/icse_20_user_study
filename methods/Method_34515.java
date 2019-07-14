public static String getMethodInfo(Method m){
  StringBuilder info=new StringBuilder();
  info.append("Method signature:").append("\n");
  info.append(m.toGenericString()).append("\n");
  info.append("Declaring class:\n");
  info.append(m.getDeclaringClass().getCanonicalName()).append("\n");
  info.append("\nFlags:").append("\n");
  info.append("Bridge=").append(m.isBridge()).append("\n");
  info.append("Synthetic=").append(m.isSynthetic()).append("\n");
  info.append("Final=").append(Modifier.isFinal(m.getModifiers())).append("\n");
  info.append("Native=").append(Modifier.isNative(m.getModifiers())).append("\n");
  info.append("Synchronized=").append(Modifier.isSynchronized(m.getModifiers())).append("\n");
  info.append("Abstract=").append(Modifier.isAbstract(m.getModifiers())).append("\n");
  info.append("AccessLevel=").append(getAccessLevel(m.getModifiers())).append("\n");
  info.append("\nReturn Type: \n");
  info.append("ReturnType=").append(m.getReturnType()).append("\n");
  info.append("GenericReturnType=").append(m.getGenericReturnType()).append("\n");
  info.append("\nParameters:");
  Class<?>[] pType=m.getParameterTypes();
  Type[] gpType=m.getGenericParameterTypes();
  if (pType.length != 0) {
    info.append("\n");
  }
 else {
    info.append("empty\n");
  }
  for (int i=0; i < pType.length; i++) {
    info.append("parameter [").append(i).append("]:\n");
    info.append("ParameterType=").append(pType[i]).append("\n");
    info.append("GenericParameterType=").append(gpType[i]).append("\n");
  }
  info.append("\nExceptions:");
  Class<?>[] xType=m.getExceptionTypes();
  Type[] gxType=m.getGenericExceptionTypes();
  if (xType.length != 0) {
    info.append("\n");
  }
 else {
    info.append("empty\n");
  }
  for (int i=0; i < xType.length; i++) {
    info.append("exception [").append(i).append("]:\n");
    info.append("ExceptionType=").append(xType[i]).append("\n");
    info.append("GenericExceptionType=").append(gxType[i]).append("\n");
  }
  info.append("\nAnnotations:");
  if (m.getAnnotations().length != 0) {
    info.append("\n");
  }
 else {
    info.append("empty\n");
  }
  for (int i=0; i < m.getAnnotations().length; i++) {
    info.append("annotation[").append(i).append("]=").append(m.getAnnotations()[i]).append("\n");
  }
  return info.toString();
}
