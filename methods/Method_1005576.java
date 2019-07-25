@NonNull @Override public <T>T create(@NonNull Class<T> clazz) throws Exception {
  return clazz.newInstance();
}
