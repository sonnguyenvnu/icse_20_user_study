private boolean judgeName(String name,String variableName){
  return name != null && (name.equals(variableName + ADD) || name.equals(variableName + REMOVE) || name.equals(variableName + CLEAR));
}
