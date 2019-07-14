/** 
 * Supplies the n'th generic type of the given expression. For example, in  {@code Map<A,B> c;} forthe type of c and n=1, the result is the type of  {@code B}. If there are an insufficient number of type arguments, this method will return the  {@code java.lang.Object} type from symbol table.
 * @param typeSupplier a supplier of the expression which has a generic type
 * @param n the position of the generic argument
 */
public static Supplier<Type> genericTypeOfType(final Supplier<Type> typeSupplier,final int n){
  return new Supplier<Type>(){
    @Override public Type get(    VisitorState state){
      Type type=typeSupplier.get(state);
      if (type.getTypeArguments().size() <= n) {
        return state.getSymtab().objectType;
      }
      return type.getTypeArguments().get(n);
    }
  }
;
}
