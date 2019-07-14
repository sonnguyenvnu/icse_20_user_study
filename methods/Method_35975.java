private boolean arrayOrderIgnoredAndIsArrayMove(String operation,List<String> path){
  return operation.equals("move") && isNumber(getLast(path)) && shouldIgnoreArrayOrder();
}
