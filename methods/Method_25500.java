/** 
 * Supplies the n'th generic type of the given expression. For example, in  {@code Map<A,B> c;} forthe expression c and n=1, the result is the type of  {@code B}. If there are an insufficient number of type arguments, this method will return the  {@code java.lang.Object} type from symboltable.
 * @param expressionSupplier a supplier of the expression which has a generic type
 * @param n the position of the generic argument
 */
public static Supplier<Type> genericTypeOf(final Supplier<ExpressionTree> expressionSupplier,final int n){
  return new Supplier<Type>(){
    @Override public Type get(    VisitorState state){
      JCExpression jcExpression=(JCExpression)expressionSupplier.get(state);
      if (jcExpression.type.getTypeArguments().size() <= n) {
        return state.getSymtab().objectType;
      }
      return jcExpression.type.getTypeArguments().get(n);
    }
  }
;
}
