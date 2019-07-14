static Type wildBound(Type type){
  return type.hasTag(TypeTag.WILDCARD) ? ((WildcardType)type).type : type;
}
