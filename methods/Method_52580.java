public static boolean isExactlyAny(TypedNameDeclaration vnd,Class<?>... clazzes){
  Class<?> type=vnd.getType();
  for (  final Class<?> clazz : clazzes) {
    if (type != null && type.equals(clazz) || type == null && (clazz.getSimpleName().equals(vnd.getTypeImage()) || clazz.getName().equals(vnd.getTypeImage()))) {
      return true;
    }
  }
  return false;
}
