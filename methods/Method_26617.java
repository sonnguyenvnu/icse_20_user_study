@Override @Nullable public Choice<Unifier> visitArrayType(ArrayType arrayType,@Nullable Unifier unifier){
  return componentType().unify(arrayType.getComponentType(),unifier);
}
