public static boolean isExactlyNone(TypedNameDeclaration vnd,Class<?>... clazzes){
  return !isExactlyAny(vnd,clazzes);
}
