@Override public Iterator<ASTSwitchLabel> iterator(){
  return new NodeChildrenIterator<>(this,ASTSwitchLabel.class);
}
