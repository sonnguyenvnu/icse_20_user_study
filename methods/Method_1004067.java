private static boolean matches(final String annotation){
  final String name=annotation.substring(Math.max(annotation.lastIndexOf('/'),annotation.lastIndexOf('$')) + 1);
  return name.contains("Generated");
}
