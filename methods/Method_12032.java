private void validateNoTypeParameterOnType(Type type,List<Throwable> errors){
  if (type instanceof TypeVariable<?>) {
    errors.add(new Exception("Method " + method.getName() + "() contains unresolved type variable " + type));
  }
 else   if (type instanceof ParameterizedType) {
    validateNoTypeParameterOnParameterizedType((ParameterizedType)type,errors);
  }
 else   if (type instanceof WildcardType) {
    validateNoTypeParameterOnWildcardType((WildcardType)type,errors);
  }
 else   if (type instanceof GenericArrayType) {
    validateNoTypeParameterOnGenericArrayType((GenericArrayType)type,errors);
  }
}
