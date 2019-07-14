public static String desc(Method method){
  Class<?>[] types=method.getParameterTypes();
  StringBuilder buf=new StringBuilder((types.length + 1) << 4);
  buf.append('(');
  for (int i=0; i < types.length; ++i) {
    buf.append(desc(types[i]));
  }
  buf.append(')');
  buf.append(desc(method.getReturnType()));
  return buf.toString();
}
