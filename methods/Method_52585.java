public static boolean subclasses(TypeNode n,Class<?> clazz){
  Class<?> type=n.getType();
  if (type == null) {
    return n.hasImageEqualTo(clazz.getSimpleName()) || n.hasImageEqualTo(clazz.getName());
  }
  return clazz.isAssignableFrom(type);
}
