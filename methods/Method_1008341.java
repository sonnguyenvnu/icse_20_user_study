/** 
 * Returns a type that is functionally equal but not necessarily equal according to  {@link Object#equals(Object) Object.equals()}.
 */
public static Type canonicalize(Type type){
  if (type instanceof ParameterizedTypeImpl || type instanceof GenericArrayTypeImpl || type instanceof WildcardTypeImpl) {
    return type;
  }
 else   if (type instanceof ParameterizedType) {
    ParameterizedType p=(ParameterizedType)type;
    return new ParameterizedTypeImpl(p.getOwnerType(),p.getRawType(),p.getActualTypeArguments());
  }
 else   if (type instanceof GenericArrayType) {
    GenericArrayType g=(GenericArrayType)type;
    return new GenericArrayTypeImpl(g.getGenericComponentType());
  }
 else   if (type instanceof Class && ((Class<?>)type).isArray()) {
    Class<?> c=(Class<?>)type;
    return new GenericArrayTypeImpl(c.getComponentType());
  }
 else   if (type instanceof WildcardType) {
    WildcardType w=(WildcardType)type;
    return new WildcardTypeImpl(w.getUpperBounds(),w.getLowerBounds());
  }
 else {
    return type;
  }
}
