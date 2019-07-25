Class<?>[] types(Object[] arguments){
  return Arrays.stream(arguments).map(Object::getClass).toArray(Class[]::new);
}
