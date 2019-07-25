/** 
 * Recursively flattens nested operations.
 * @return a list of flattened operations.
 */
default List<Operation> flatten(){
  final List<Operation> tmp=new ArrayList<>(1);
  for (  final Operation operation : getOperations()) {
    if (operation instanceof Operations) {
      tmp.addAll(((Operations)operation).flatten());
    }
 else {
      tmp.add(operation);
    }
  }
  return tmp;
}
