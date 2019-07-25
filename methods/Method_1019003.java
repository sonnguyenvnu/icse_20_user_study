@Override protected JsonValue convert(Class<?> type,Object value){
  if (type.isAssignableFrom(List.class)) {
    return Json.array(recent.toArray(new String[0]));
  }
  return null;
}
