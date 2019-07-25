public SNodeId leaf(){
  if (myPath.isEmpty())   throw new IllegalStateException("empty NodePath");
  return myPath.get(myPath.size() - 1);
}
