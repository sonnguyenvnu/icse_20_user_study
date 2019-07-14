/** 
 * Unwinds parametrized type into plain list that contains all parameters for the given type including nested parameterized types, for example calling the method for the following type <code> GType<GType<GDoubleType<GType<GDoubleType<Parent, Parent>>, Parent>>> </code> will return list of 8 elements: <code> [GType, GType, GDoubleType, GType, GDoubleType, Parent, Parent, Parent] </code> if the given type is not parametrized then returns list with one element which is given type passed into method.
 * @param type the parameterized type
 * @return list of {@link Type}
 */
@ParametersAreNonnullByDefault public static List<Type> flattenTypeVariables(Type type){
  Validate.notNull(type,"type cannot be null");
  List<Type> types=new ArrayList<Type>();
  TreeTraverser<Type> typeTraverser=new TreeTraverser<Type>(){
    @Override public Iterable<Type> children(    Type root){
      if (root instanceof ParameterizedType) {
        ParameterizedType pType=(ParameterizedType)root;
        return Arrays.asList(pType.getActualTypeArguments());
      }
 else       if (root instanceof TypeVariable) {
        TypeVariable pType=(TypeVariable)root;
        return Arrays.asList(pType.getBounds());
      }
      return Collections.emptyList();
    }
  }
;
  for (  Type t : typeTraverser.breadthFirstTraversal(type)) {
    types.add(t);
  }
  return types;
}
