@Override public Iterator<ASTMemberValuePair> iterator(){
  return new NodeChildrenIterator<>(this,ASTMemberValuePair.class);
}
