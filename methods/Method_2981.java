public String relation(int dependent){
  if (!goldDependencies.containsKey(dependent))   return "_";
  return goldDependencies.get(dependent).relationId + "";
}
