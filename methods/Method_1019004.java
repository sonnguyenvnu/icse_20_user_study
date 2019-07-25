@Override protected Object parse(Class<?> type,JsonValue value){
  if (type.isAssignableFrom(List.class)) {
    if (value.isArray()) {
      recent.clear();
      value.asArray().forEach(v -> {
        recent.add(v.asString());
      }
);
      cleanRecent();
    }
  }
  return null;
}
