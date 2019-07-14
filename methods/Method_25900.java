/** 
 * A string representation of a method descriptor, where all parameters whose type is a functional interface are "erased" to the interface's function type. For example, `foo(Supplier<String>)` is represented as `foo(()->Ljava/lang/String;)`.
 */
private static String functionalInterfaceSignature(VisitorState state,MethodSymbol msym){
  return String.format("%s(%s)",msym.getSimpleName(),msym.getParameters().stream().map(p -> functionalInterfaceSignature(state,p.type)).collect(joining(",")));
}
