private Description buildErrorMessage(Tree tree,String explanation){
  Description.Builder description=buildDescription(tree);
  description.setMessage(MESSAGE_BASE + explanation + ".");
  return description.build();
}
