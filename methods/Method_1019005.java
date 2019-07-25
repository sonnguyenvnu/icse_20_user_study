@Override protected JsonValue convert(Class<?> type,Object value){
  if (type.equals(Frequency.class)) {
    return Json.value(((Frequency)value).name());
  }
  return null;
}
