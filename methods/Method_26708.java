public static UPrimitiveType create(TypeKind typeKind){
  checkArgument(isDeFactoPrimitive(typeKind),"Non-primitive type %s passed to UPrimitiveType",typeKind);
  return new AutoValue_UPrimitiveType(typeKind);
}
