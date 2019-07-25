@Override protected Object parse(Class<?> type,JsonValue value){
  if (type.equals(ASMVersion.class)) {
    return ASMVersion.valueOf(value.asString());
  }
  return null;
}
