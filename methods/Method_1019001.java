@Override protected JsonValue convert(Class<?> type,Object value){
  if (type.equals(Level.class)) {
    return Json.value(((Level)value).name());
  }
  return null;
}
