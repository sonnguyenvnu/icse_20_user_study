@Override public Schedule create(Class<? extends Schedule> clazz){
  return getThreadLocalObject().create(clazz);
}
