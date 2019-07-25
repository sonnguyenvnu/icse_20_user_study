public static TypeBindings create(Class<?> erasedType,JavaType typeArg1,JavaType typeArg2){
  TypeVariable<?>[] vars=TypeParamStash.paramsFor2(erasedType);
  int varLen=(vars == null) ? 0 : vars.length;
  if (varLen != 2) {
    throw new IllegalArgumentException("Cannot create TypeBindings for class " + erasedType.getName() + " with 2 type parameters: class expects " + varLen);
  }
  return new TypeBindings(new String[]{vars[0].getName(),vars[1].getName()},new JavaType[]{typeArg1,typeArg2},null);
}
