@Override public Iterator<ASTMemberValue> iterator(){
  return new NodeChildrenIterator<>(this,ASTMemberValue.class);
}
