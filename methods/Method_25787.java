private static Map<String,Type> indexTypeByName(List<? extends VariableTree> parameters){
  Map<String,Type> result=newHashMap();
  for (  VariableTree parameter : parameters) {
    result.put(parameter.getName().toString(),getType(parameter.getType()));
  }
  return result;
}
