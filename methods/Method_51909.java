static Iterator<ASTVariableDeclaratorId> iterateIds(Node parent){
  final Iterator<ASTVariableDeclarator> declarators=new NodeChildrenIterator<>(parent,ASTVariableDeclarator.class);
  return new Iterator<ASTVariableDeclaratorId>(){
    @Override public boolean hasNext(){
      return declarators.hasNext();
    }
    @Override public ASTVariableDeclaratorId next(){
      return declarators.next().getVariableId();
    }
    @Override public void remove(){
      throw new UnsupportedOperationException();
    }
  }
;
}
