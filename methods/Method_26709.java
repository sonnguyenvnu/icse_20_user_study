public static boolean isDeFactoPrimitive(TypeKind kind){
  return kind.isPrimitive() || HONORARY_PRIMITIVES.contains(kind);
}
