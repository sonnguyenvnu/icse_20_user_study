public int head(int dependent){
  if (!goldDependencies.containsKey(dependent))   return -1;
  return goldDependencies.get(dependent).headIndex;
}
