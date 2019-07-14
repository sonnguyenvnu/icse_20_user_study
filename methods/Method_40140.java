@Nullable FunType newFunc(@Nullable Type type){
  if (type == null) {
    type=Type.UNKNOWN;
  }
  return new FunType(Type.UNKNOWN,type);
}
