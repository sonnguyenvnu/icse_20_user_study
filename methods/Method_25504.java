public static Supplier<Type> arrayOf(final Supplier<Type> elementType){
  return new Supplier<Type>(){
    @Override public Type get(    VisitorState state){
      return new Type.ArrayType(elementType.get(state),state.getSymtab().arraysType.tsym);
    }
  }
;
}
