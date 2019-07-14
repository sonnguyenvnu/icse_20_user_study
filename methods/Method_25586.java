@Override public Void visitNewArray(NewArrayTree tree,Void unused){
  hasSideEffect=true;
  return null;
}
