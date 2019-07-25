@Override protected Object parse(Class<?> type,JsonValue value){
  if (type.equals(Frequency.class)) {
    return Frequency.valueOf(value.asString());
  }
  return null;
}
