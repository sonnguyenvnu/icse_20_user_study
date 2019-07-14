@Nullable private static Object getInstance(Type type,VisitorState state){
  Types types=state.getTypes();
  if (type.getKind() == TypeKind.NULL) {
    return null;
  }
  Type unboxedType=types.unboxedTypeOrType(types.erasure(type));
  if (unboxedType.isPrimitive()) {
    type=unboxedType;
switch (type.getKind()) {
case BOOLEAN:
      return false;
case BYTE:
    return Byte.valueOf((byte)1);
case SHORT:
  return Short.valueOf((short)2);
case INT:
return Integer.valueOf(3);
case LONG:
return Long.valueOf(4);
case CHAR:
return Character.valueOf('c');
case FLOAT:
return Float.valueOf(5.0f);
case DOUBLE:
return Double.valueOf(6.0d);
case VOID:
case NONE:
case NULL:
case ERROR:
return null;
case ARRAY:
return new Object[0];
default :
throw new AssertionError(type.getKind());
}
}
if (isSubtype(types,type,state.getSymtab().stringType)) {
return String.valueOf("string");
}
if (isSubtype(types,type,state.getTypeFromString(BigDecimal.class.getName()))) {
return BigDecimal.valueOf(42.0d);
}
if (isSubtype(types,type,state.getTypeFromString(BigInteger.class.getName()))) {
return BigInteger.valueOf(43L);
}
if (isSubtype(types,type,state.getTypeFromString(Date.class.getName()))) {
return new Date();
}
if (isSubtype(types,type,state.getTypeFromString(Calendar.class.getName()))) {
return new GregorianCalendar();
}
if (isSubtype(types,type,state.getTypeFromString(Instant.class.getName()))) {
return Instant.now();
}
if (isSubtype(types,type,state.getTypeFromString(TemporalAccessor.class.getName()))) {
return ZonedDateTime.ofInstant(Instant.now(),ZoneId.systemDefault());
}
return new Object();
}
