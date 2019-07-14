private static Class normalizeDataType(Class datatype){
  Class superClass=datatype.getSuperclass();
  if (null != superClass && superClass.isEnum())   return superClass;
  if (Instant.class.equals(datatype))   return Instant.class;
  return datatype;
}
