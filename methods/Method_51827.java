@Override public Iterator<ASTClassOrInterfaceType> iterator(){
  return new NodeChildrenIterator<>(this,ASTClassOrInterfaceType.class);
}
