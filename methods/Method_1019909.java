/** 
 * Returns a type that is functionally equal but not necessarily equal according to  {@link Object#equals(Object) Object.equals()}.
 */
private static Type canonicalize(Type type){
  if (type instanceof Class) {
    Class<?> c=(Class<?>)type;
    return c.isArray() ? new GenericArrayTypeImpl(canonicalize(c.getComponentType())) : c;
  }
 else   if (type instanceof ParameterizedType) {
    if (type instanceof ParameterizedTypeImpl)     return type;
    ParameterizedType p=(ParameterizedType)type;
    return new ParameterizedTypeImpl(p.getOwnerType(),p.getRawType(),p.getActualTypeArguments());
  }
 else   if (type instanceof GenericArrayType) {
    if (type instanceof GenericArrayTypeImpl)     return type;
    GenericArrayType g=(GenericArrayType)type;
    return new GenericArrayTypeImpl(g.getGenericComponentType());
  }
 else   if (type instanceof WildcardType) {
    if (type instanceof WildcardTypeImpl)     return type;
    WildcardType w=(WildcardType)type;
    return new WildcardTypeImpl(w.getUpperBounds(),w.getLowerBounds());
  }
 else {
    return type;
  }
}
