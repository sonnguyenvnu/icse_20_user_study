private static void typeToString(final StringBuilder sb,final Type type,final Set<Type> visited){
  if (type instanceof ParameterizedType) {
    ParameterizedType parameterizedType=(ParameterizedType)type;
    final Class<?> rawType=(Class<?>)parameterizedType.getRawType();
    sb.append(rawType.getName());
    boolean first=true;
    for (    Type typeArg : parameterizedType.getActualTypeArguments()) {
      if (first) {
        first=false;
      }
 else {
        sb.append(", ");
      }
      sb.append('<');
      typeToString(sb,typeArg,visited);
      sb.append('>');
    }
  }
 else   if (type instanceof WildcardType) {
    WildcardType wildcardType=(WildcardType)type;
    sb.append('?');
    final Type bound;
    if (wildcardType.getLowerBounds().length != 0) {
      sb.append(" super ");
      bound=wildcardType.getLowerBounds()[0];
    }
 else {
      sb.append(" extends ");
      bound=wildcardType.getUpperBounds()[0];
    }
    typeToString(sb,bound,visited);
  }
 else   if (type instanceof TypeVariable<?>) {
    TypeVariable<?> typeVariable=(TypeVariable<?>)type;
    sb.append(typeVariable.getName());
    if (!visited.contains(type)) {
      visited.add(type);
      sb.append(" extends ");
      boolean first=true;
      for (      Type bound : typeVariable.getBounds()) {
        if (first) {
          first=false;
        }
 else {
          sb.append(" & ");
        }
        typeToString(sb,bound,visited);
      }
      visited.remove(type);
    }
  }
 else   if (type instanceof GenericArrayType) {
    GenericArrayType genericArrayType=(GenericArrayType)type;
    typeToString(genericArrayType.getGenericComponentType());
    sb.append(genericArrayType.getGenericComponentType());
    sb.append("[]");
  }
 else   if (type instanceof Class) {
    Class<?> typeClass=(Class<?>)type;
    sb.append(typeClass.getName());
  }
 else {
    throw new IllegalArgumentException("Unsupported type: " + type);
  }
}
