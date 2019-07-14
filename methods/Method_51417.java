private static String shortestNameFor(Class<?> cls){
  String compactName=TYPE_SHORTCUTS.get(cls);
  return compactName == null ? cls.getName() : compactName;
}
