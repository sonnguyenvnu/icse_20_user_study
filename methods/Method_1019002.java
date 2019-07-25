@Override protected Object parse(Class<?> type,JsonValue value){
  if (type.equals(Level.class)) {
    return Level.valueOf(value.asString());
  }
  return null;
}
