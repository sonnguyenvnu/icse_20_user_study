/** 
 * Extracts the appropriate type argument from a specific supertype of the given  {@code type}. This handles the case when a subtype has different type arguments than the expected type. For example,  {@code ClassToInstanceMap<T>} implements {@code Map<Class<? extends T>, T>}.
 * @param type the (sub)type from which to extract the type argument
 * @param superTypeSym the symbol of the supertype on which the type parameter is defined
 * @param typeArgIndex the index of the type argument to extract from the supertype
 * @param types the {@link Types} utility class from the {@link VisitorState}
 * @return the type argument, if defined, or null otherwise
 */
@Nullable protected static final Type extractTypeArgAsMemberOfSupertype(Type type,Symbol superTypeSym,int typeArgIndex,Types types){
  Type collectionType=types.asSuper(type,superTypeSym);
  if (collectionType == null) {
    return null;
  }
  com.sun.tools.javac.util.List<Type> tyargs=collectionType.getTypeArguments();
  if (tyargs.size() <= typeArgIndex) {
    return null;
  }
  return tyargs.get(typeArgIndex);
}
