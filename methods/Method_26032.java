/** 
 * Removes the message argument if it is present. 
 */
private void removeMessageArgumentIfPresent(VisitorState state,List<Type> argumentTypes){
  if (argumentTypes.size() == 2) {
    return;
  }
  Types types=state.getTypes();
  Type firstType=argumentTypes.get(0);
  if (types.isSameType(firstType,state.getSymtab().stringType)) {
    argumentTypes.remove(0);
  }
}
