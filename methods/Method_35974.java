private boolean extraElementsIgnoredAndIsAddition(String operation){
  return operation.equals("add") && shouldIgnoreExtraElements();
}
